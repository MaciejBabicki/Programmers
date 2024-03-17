package MaciejBabicki.Programmers.programmer.service;

import MaciejBabicki.Programmers.programmer.dto.ProgrammerDto;

import java.util.List;

public interface ProgrammerService {

    ProgrammerDto createProgrammer(ProgrammerDto programmerDto);

    List<ProgrammerDto> getProgrammers();

    ProgrammerDto getProgrammerById(Long id);
}
