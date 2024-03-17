package MaciejBabicki.Programmers.service;

import MaciejBabicki.Programmers.dto.ProgrammerDto;

import java.util.List;

public interface ProgrammerService {

    ProgrammerDto createProgrammer(ProgrammerDto programmerDto);

    List<ProgrammerDto> getProgrammers();

    ProgrammerDto getProgrammerById(Long id);
}
