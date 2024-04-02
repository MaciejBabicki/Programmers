package pl.programmers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.programmers.entity.Programmer;
import pl.programmers.pojo.ProgrammerDto;
import pl.programmers.service.ProgrammerService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    ProgrammerService programmerService;

    @Test
    public void shouldReturnProgrammers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/programmers"))
                .andExpect(status().is(200)).andReturn();
        Programmer[] programmers = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Programmer[].class);
        Assertions.assertNull(programmers[0].getFirstName());
    }

    @Test
    public void shouldReturn404WhenGetProgrammerById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/programmers/15"))
                .andExpect(status().is(404)).andReturn();
        String actual = mvcResult.getResolvedException().getMessage();
        Assertions.assertEquals("Programmer with this id doesn't exist", actual);
    }

    @Test
    public void createProgrammer_shouldReturnCreatedProgrammer() throws Exception {
        //given
        ProgrammerDto programmerDto = new ProgrammerDto(1L, "Maciej", "Babicki", "RepoName1");
        //when
        when(programmerService.createProgrammer()).thenReturn(programmerDto);
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
}
