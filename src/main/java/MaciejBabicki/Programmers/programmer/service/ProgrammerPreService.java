package MaciejBabicki.Programmers.programmer.service;

import MaciejBabicki.Programmers.programmer.dto.ProgrammerDto;
import MaciejBabicki.Programmers.programmer.entity.Programmer;

import java.util.List;
import java.util.Optional;

public interface ProgrammerPreService {

    ProgrammerDto createProgrammer(ProgrammerDto programmerDto);

    List<ProgrammerDto> getProgrammers();

    ProgrammerDto getProgrammerById(Long id);
}
