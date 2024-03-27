package pl.programmers.service;

import org.junit.Test;
import pl.programmers.entity.Programmer;
import pl.programmers.exception.ResourceNotFoundException;
import pl.programmers.pojo.ProgrammerDto;
import pl.programmers.repository.ProgrammerRepo;
import pl.programmers.service.importservice.ProgrammerImportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgrammerServiceTests {
    private final ProgrammerRepo programmerRepo = mock(ProgrammerRepo.class);
    private final ProgrammerImportService programmerImportService = mock(ProgrammerImportService.class);
    private final ProgrammerService programmerService = new ProgrammerService(programmerRepo, programmerImportService);
    Programmer programmer = new Programmer();
    ProgrammerDto programmerDto = new ProgrammerDto(1L, "a", "b", "c");
    Long existingProgrammerId = 1L;
    Long nonExistingProgrammerId = 2L;

    @Test
    public void test_CreateProgrammer_ProgrammerCreated() {
        //given
        when(programmerRepo.save(any(Programmer.class))).thenReturn(programmer);
        when(programmerImportService.getProgrammers()).thenReturn(programmer);
        //when
        ProgrammerDto dto = programmerService.createProgrammer();
        //then
        assertNotNull(dto);
        assertEquals(programmer.getId(), dto.getId());
        assertEquals(programmer.getFirstName(), dto.getFirstName());
    }

    @Test
    public void test_CreateProgrammer_NullReturned() {
        //given
        when(programmerService.createProgrammer()).thenReturn(null);
        //when
        ProgrammerDto dto = programmerService.createProgrammer();
        //then
        assertNull(dto);
    }

    @Test
    public void test_CreateProgrammer_ExceptionThrown() {
        //given
        when(programmerService.createProgrammer()).thenReturn(programmerDto);
        //when & then
        assertThrows(ClassCastException.class, programmerService::createProgrammer);
    }

    @Test
    public void test_GetProgrammers_CompleteList() {
        //given
        List<Programmer> programmers = new ArrayList<>();
        List<ProgrammerDto> programmerDtos = new ArrayList<>();
        programmerDtos.add(programmerDto);
        //when
        when(programmerRepo.findAll()).thenReturn(programmers);
        List<ProgrammerDto> result = programmerService.getProgrammers();
        //then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void test_GetProgrammers_EmptyList() {
        //given
        when(programmerRepo.findAll()).thenReturn(new ArrayList<>());
        //when
        List<ProgrammerDto> result = programmerService.getProgrammers();
        //then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void test_GetProgrammerById_ProgrammerFound() {
        //given
        programmerDto.setId(1L);
        programmer.setId(1L);
        when(programmerRepo.findById(programmerDto.getId())).thenReturn(Optional.of(programmer));
        //when
        ProgrammerDto result = programmerService.getProgrammerById(existingProgrammerId);
        //then
        assertEquals(programmerDto.getId(), result.getId());
    }

    @Test
    public void test_GetProgrammerById_ProgrammerNotFound() {
        //given
        programmerDto.setId(1L);
        programmer.setId(1L);
        when(programmerRepo.findById(nonExistingProgrammerId)).thenThrow(ResourceNotFoundException.class);
        //when & then
        assertThrows(ResourceNotFoundException.class, () -> {
            programmerService.getProgrammerById(nonExistingProgrammerId);
        });
    }
}
