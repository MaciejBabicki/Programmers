package pl.programmers.programmer.service;

import org.junit.Test;
import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.exception.ResourceNotFoundException;
import pl.programmers.programmer.pojo.ProgrammerDto;
import pl.programmers.programmer.repository.ProgrammerRepo;
import pl.programmers.programmer.service.importservice.ProgrammerImportService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProgrammerServiceImplTests {
    private final ProgrammerRepo programmerRepo = mock(ProgrammerRepo.class);
    private final ProgrammerImportService programmerImportService = mock(ProgrammerImportService.class);
    private final ProgrammerServiceImpl programmerServiceImpl = new ProgrammerServiceImpl(programmerRepo, programmerImportService);
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
        ProgrammerDto dto = programmerServiceImpl.createProgrammer(programmer);
        //then
        assertNotNull(dto);
        assertEquals(programmer.getId(), dto.id());
        assertEquals(programmer.getFirstName(), dto.firstName());
        assertEquals(programmer.getLastName(), dto.lastName());
        assertEquals(programmer.getRepoName(), dto.repoName());
        verify(programmerRepo, times(1)).save(programmer);
    }

    @Test
    public void test_CreateProgrammer_NullReturned() {
        //given
        when(programmerRepo.save(programmer)).thenReturn(null);
        //when
        ProgrammerDto dto = programmerServiceImpl.createProgrammer();
        //then
        assertNull(dto);
        verify(programmerRepo, times(0)).save(programmer);
    }

    @Test
    public void test_CreateProgrammer_ExceptionThrown() {
        //given
        when(programmerServiceImpl.createProgrammer()).thenReturn(programmerDto);
        //when & then
        assertThrows(ClassCastException.class, programmerServiceImpl::createProgrammer);
    }

    @Test
    public void test_GetProgrammers_CompleteList() {
        //given
        List<Programmer> programmers = Arrays.asList(
                new Programmer(1L, "a", "b", "c"),
                new Programmer(1L, "a", "b", "c")
        );
        when(programmerRepo.findAll()).thenReturn(programmers);
        //when
        List<ProgrammerDto> result = programmerServiceImpl.getProgrammers();
        //then
        assertNotNull(result);
        assertEquals(programmers.size(), result.size());
        for (int i = 0; i < programmers.size(); i++) {
            ProgrammerDto dto = result.get(i);
            Programmer programmer1 = programmers.get(i);
            assertEquals(programmer1.getId(), dto.id());
            assertEquals(programmer1.getFirstName(), dto.lastName());
            verify(programmerRepo, times(1)).findAll();
        }
    }

    @Test
    public void test_GetProgrammers_EmptyList() {
        //given
        when(programmerRepo.findAll()).thenReturn(new ArrayList<>());
        //when
        List<ProgrammerDto> result = programmerServiceImpl.getProgrammers();
        //then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(programmerRepo, times(1)).findAll();
    }

    @Test
    public void test_GetProgrammerById_ProgrammerFound() {
        //given
        programmer.setId(1L);
        when(programmerRepo.findById(programmerDto.id())).thenReturn(Optional.of(programmer));
        //when
        ProgrammerDto result = programmerServiceImpl.getProgrammerById(existingProgrammerId);
        //then
        assertEquals(programmerDto.id(), result.id());
        verify(programmerRepo, times(1)).findById(existingProgrammerId);
    }

    @Test
    public void test_GetProgrammerById_ProgrammerNotFound() {
        //given
        programmer.setId(1L);
        when(programmerRepo.findById(nonExistingProgrammerId)).thenThrow(ResourceNotFoundException.class);
        //when & then
        assertThrows(ResourceNotFoundException.class, () -> {
            programmerServiceImpl.getProgrammerById(nonExistingProgrammerId);
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
        ProgrammerDto result = programmerServiceImpl.updateProgrammer(programmer.getId());
        //then
        assertNotNull(result);
        assertEquals("Maciej", result.firstName());
        verify(programmerRepo, times(1)).findById(programmer.getId());
    }

    @Test
    public void test_UpdateProgrammer_ProgrammerNotFound() {
        //given
        when(programmerRepo.findById(nonExistingProgrammerId)).thenThrow(ResourceNotFoundException.class);
        //when & then
        assertThrows(ResourceNotFoundException.class, () -> programmerServiceImpl.updateProgrammer(nonExistingProgrammerId));
        verify(programmerRepo, times(1)).findById(nonExistingProgrammerId);
    }

    @Test
    public void test_DeleteProgrammer_ProgrammerDeleted() {
        //given
        Programmer programmerToDelete = new Programmer();
        programmerToDelete.setId(1L);
        when(programmerRepo.findById(existingProgrammerId)).thenReturn(Optional.of(programmerToDelete));
        //when
        programmerServiceImpl.deleteProgrammer(programmerToDelete.getId());
        //then
        verify(programmerRepo, times(1)).findById(existingProgrammerId);
        verify(programmerRepo, times(1)).deleteById(existingProgrammerId);
    }

    @Test
    public void test_DeleteProgrammer_ProgrammerNotFound() {
        //given
        when(programmerRepo.findById(nonExistingProgrammerId)).thenThrow(ResourceNotFoundException.class);
        //when & then
        assertThrows(ResourceNotFoundException.class, () -> {
            programmerServiceImpl.deleteProgrammer(nonExistingProgrammerId);
            verify(programmerRepo, times(1)).findById(nonExistingProgrammerId);
        });
    }
}
