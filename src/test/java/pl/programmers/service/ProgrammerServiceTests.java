package pl.programmers.service;

import org.junit.Test;
import pl.programmers.entity.Programmer;
import pl.programmers.exception.ResourceNotFoundException;
import pl.programmers.pojo.ProgrammerDto;
import pl.programmers.repository.ProgrammerRepo;
import pl.programmers.service.importservice.ProgrammerImportService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        assertEquals(programmer.getLastName(), dto.getLastName());
        assertEquals(programmer.getRepoName(), dto.getRepoName());
        verify(programmerRepo, times(1)).save(programmer);
    }

    @Test
    public void test_CreateProgrammer_NullReturned() {
        //given
        when(programmerService.createProgrammer()).thenReturn(null);
        //when
        ProgrammerDto dto = programmerService.createProgrammer();
        //then
        assertNull(dto);
        verify(programmerRepo, times(0)).save(programmer);
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
        List<Programmer> programmers = Arrays.asList(
                new Programmer(),
                new Programmer()
        );
        when(programmerRepo.findAll()).thenReturn(programmers);
        //when
        List<ProgrammerDto> result = programmerService.getProgrammers();
        //then
        assertNotNull(result);
        assertEquals(programmers.size(), result.size());
        for (int i = 0; i < programmers.size(); i++) {
            ProgrammerDto dto = result.get(i);
            Programmer programmer1 = programmers.get(i);
            assertEquals(programmer1.getId(), dto.getId());
            assertEquals(programmer1.getFirstName(), dto.getLastName());
            verify(programmerRepo, times(1)).findAll();
        }
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
        verify(programmerRepo, times(1)).findAll();
    }

    @Test
    public void test_GetProgrammerById_ProgrammerFound() {
        //given
        programmer.setId(1L);
        when(programmerRepo.findById(programmerDto.getId())).thenReturn(Optional.of(programmer));
        //when
        ProgrammerDto result = programmerService.getProgrammerById(existingProgrammerId);
        //then
        assertEquals(programmerDto.getId(), result.getId());
        verify(programmerRepo, times(1)).findById(existingProgrammerId);
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
            verify(programmerRepo, times(1)).findById(nonExistingProgrammerId);
        });
    }

    @Test
    public void test_UpdateProgrammer_ProgrammerUpdated() {
        //given
        programmer.setId(1L);
        programmer.setFirstName("Maciej");
        when(programmerRepo.findById(programmer.getId())).thenReturn(Optional.of(programmer));
        //when
        ProgrammerDto result = programmerService.updateProgrammer(programmer.getId());
        //then
        assertNotNull(result);
        assertEquals("Maciej", result.getFirstName());
        verify(programmerRepo, times(1)).findById(programmer.getId());
    }

    @Test
    public void test_UpdateProgrammer_Programmer_Not_Found() {
        //given
        when(programmerRepo.findById(nonExistingProgrammerId)).thenThrow(ResourceNotFoundException.class);
        //when & then
        assertThrows(ResourceNotFoundException.class, () -> programmerService.updateProgrammer(nonExistingProgrammerId));
        verify(programmerRepo, times(1)).findById(nonExistingProgrammerId);
    }

    @Test
    public void test_DeleteProgrammer_ProgrammerDeleted() {
        //given
        Programmer programmerToDelete = new Programmer();
        programmerToDelete.setId(1L);
        when(programmerRepo.findById(existingProgrammerId)).thenReturn(Optional.of(programmerToDelete));
        //when
        programmerService.deleteProgrammer(programmerToDelete.getId());
        //then
        verify(programmerRepo, times(1)).findById(existingProgrammerId);
        verify(programmerRepo, times(1)).deleteById(existingProgrammerId);
    }

    @Test
    public void test_DeleteProgrammer_Programmer_Not_Found() {
        //given
        when(programmerRepo.findById(nonExistingProgrammerId)).thenThrow(ResourceNotFoundException.class);
        //when & then
        assertThrows(ResourceNotFoundException.class, () -> {
            programmerService.deleteProgrammer(nonExistingProgrammerId);
            verify(programmerRepo, times(1)).findById(nonExistingProgrammerId);
        });
    }
}
