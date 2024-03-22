package pl.programmers.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.programmers.entity.Programmer;
import pl.programmers.pojo.ProgrammerDto;
import pl.programmers.repository.ProgrammerRepo;
import pl.programmers.service.importservice.ProgrammerImportService;
import pl.programmers.service.importservice.ProgrammerImportService2;


public class ProgrammerServiceTests {

    @Mock
    private ProgrammerRepo programmerRepo;
    @Mock
    private ProgrammerImportService programmerImportService = new ProgrammerImportService(programmerRepo);
    private final ProgrammerService programmerService = new ProgrammerService(programmerRepo, programmerImportService);


    @Test
    public void test_CreateProgrammer_ProgrammerCreated() {
        //given
        Programmer programmer = new Programmer();
        //when
        ProgrammerDto programmerDto = programmerService.createProgrammer();
        //then
        Assertions.assertNotNull(programmerDto);

    }

    @Test
    public void test_CreateProgrammer_BadRequest() {

    }
}
