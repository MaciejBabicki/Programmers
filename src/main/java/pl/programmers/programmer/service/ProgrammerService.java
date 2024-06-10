package pl.programmers.programmer.service;

import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.pojo.ProgrammerDto;

import java.util.List;

public interface ProgrammerService {
    ProgrammerDto createProgrammer(Programmer programmer);
    List<ProgrammerDto> getProgrammers();
    ProgrammerDto getProgrammerById(long id);

    void deleteProgrammer(long id);
}
