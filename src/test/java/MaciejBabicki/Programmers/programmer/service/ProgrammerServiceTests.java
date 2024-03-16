package MaciejBabicki.Programmers.programmer.service;

import MaciejBabicki.Programmers.programmer.dto.ProgrammerDto;
import MaciejBabicki.Programmers.programmer.entity.Programmer;
import MaciejBabicki.Programmers.programmer.mapper.ProgrammerMapper;
import MaciejBabicki.Programmers.programmer.repository.ProgrammerRepo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgrammerServiceTests {


    private ProgrammerRepo programmerRepo = mock(ProgrammerRepo.class);
    private ProgrammerService programmerService = new ProgrammerService(programmerRepo);
    private final Programmer programmer = mock(Programmer.class);
    private final ProgrammerDto programmerDto =mock(ProgrammerDto.class);

    Long existingProgrammerId =2L;
    Long nonExistingProgrammerId =4L;

    @Test
    public void testCreateProgrammer(){
        Programmer createdProgrammer = ProgrammerMapper.mapToProgrammer(programmerDto);
        when(programmerRepo.save(any(Programmer.class))).thenReturn(createdProgrammer);

        ProgrammerDto createdProgrammerDto = programmerService.createProgrammer(programmerDto);
        Assertions.assertNotNull(createdProgrammerDto);
        Assertions.assertEquals(programmerDto.getFirstName(), createdProgrammerDto.getFirstName());
    }

    @Test
    public void test_findProgrammerById_Exists(){
        programmer.setId(existingProgrammerId);

        when(programmerRepo.findById(existingProgrammerId)).thenReturn(Optional.of(programmer));
        ProgrammerDto createdProgrammerDto = programmerService.getProgrammerById(existingProgrammerId);

        Assertions.assertEquals(programmer.getId(), createdProgrammerDto.getId());



    }

    @Test
    public void getProgrammers(){
        List<Programmer> programmers = new ArrayList<>();
        Assertions.assertEquals(programmerService.getProgrammers(), programmers);
    }





}
