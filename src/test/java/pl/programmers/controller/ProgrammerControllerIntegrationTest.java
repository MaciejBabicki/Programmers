package pl.programmers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.programmers.exception.ResourceNotFoundException;
import pl.programmers.pojo.ProgrammerDto;
import pl.programmers.service.ProgrammerServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles
@RunWith(SpringRunner.class)
public class ProgrammerControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ProgrammerServiceImpl programmerServiceImpl;

    @Test
    public void createProgrammer_shouldReturnCreatedProgrammer() throws Exception {
        //given
        ProgrammerDto programmerDto = new ProgrammerDto(1L, "Maciej", "Babicki", "RepoName1");
        //when
        when(programmerServiceImpl.createProgrammer()).thenReturn(programmerDto);
        //then
        mockMvc.perform(post("/programmers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(programmerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(programmerDto.id()))
                .andExpect(jsonPath("$.firstName").value(programmerDto.firstName()))
                .andExpect(jsonPath("$.lastName").value(programmerDto.lastName()))
                .andExpect(jsonPath("$.repoName").value(programmerDto.repoName()));
    }

    @Test
    public void createProgrammer_withInvalidRequestBody_shouldReturnBadRequest() throws Exception {
        //given
        String invalidRequestBody = "{ \"id\": \"invalid\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"repoName\": \"johndoe\" }";
        //then
        mockMvc.perform(post("/programmers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProgrammers_shouldReturnListOfProgrammers() throws Exception {
        //given
        List<ProgrammerDto> programmers = Arrays.asList(
                new ProgrammerDto(1L, "Maciej", "Babicki", "RepoName1"),
                new ProgrammerDto(2L, "Maciej", "Babicki", "RepoName2")
        );
        //when
        when(programmerServiceImpl.getProgrammers()).thenReturn(programmers);
        //then
        mockMvc.perform(get("/programmers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(programmers.get(0).id()))
                .andExpect(jsonPath("$[0].firstName").value(programmers.get(0).firstName()))
                .andExpect(jsonPath("$[0].lastName").value(programmers.get(0).lastName()))
                .andExpect(jsonPath("$[0].repoName").value(programmers.get(0).repoName()))
                .andExpect(jsonPath("$[1].id").value(programmers.get(1).id()))
                .andExpect(jsonPath("$[1].firstName").value(programmers.get(1).firstName()))
                .andExpect(jsonPath("$[1].lastName").value(programmers.get(1).lastName()))
                .andExpect(jsonPath("$[1].repoName").value(programmers.get(1).repoName()));
    }

    @Test
    public void getProgrammerById_shouldReturnProgrammerById() throws Exception {
        //given
        Long programmerId = 1L;
        ProgrammerDto programmerDto = new ProgrammerDto(programmerId, "Maciej", "Babicki", "RepoName1");
        //when
        when(programmerServiceImpl.getProgrammerById(programmerId)).thenReturn(programmerDto);
        //then
        mockMvc.perform(get("/programmers/{id}", programmerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(programmerDto.id()))
                .andExpect(jsonPath("$.firstName").value(programmerDto.firstName()))
                .andExpect(jsonPath("$.lastName").value(programmerDto.lastName()))
                .andExpect(jsonPath("$.repoName").value(programmerDto.repoName()));
    }
    @Test
    public void getProgrammerById_withNonExistingId_shouldReturnNotFound() throws Exception {
        //given
        Long nonExistingId = 15L;
        //when
        when(programmerServiceImpl.getProgrammerById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        //then
        mockMvc.perform(get("/programmers/{15}", nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateProgrammerById_shouldReturnUpdatedProgrammer() throws Exception {
        //given
        Long updatedProgrammerId = 3L;
        ProgrammerDto updatedProgrammerDto = new ProgrammerDto(updatedProgrammerId, "Maciej", "Babicki", "RepoName1");
        //when
        when(programmerServiceImpl.updateProgrammer(updatedProgrammerId)).thenReturn(updatedProgrammerDto);
        //then
        mockMvc.perform(put("/programmers/{id}", updatedProgrammerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedProgrammerDto.id()))
                .andExpect(jsonPath("$.firstName").value(updatedProgrammerDto.firstName()))
                .andExpect(jsonPath("$.lastName").value(updatedProgrammerDto.lastName()))
                .andExpect(jsonPath("$.repoName").value(updatedProgrammerDto.repoName()));
    }
}
