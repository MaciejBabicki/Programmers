package pl.programmers.programmer.service;

import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.pojo.ProgrammerDto;

import java.util.List;

public interface ProgrammerService {
    ProgrammerDto createProgrammer(Programmer programmer);
    List<ProgrammerDto> getProgrammers();
    ProgrammerDto getProgrammerById(Long id);
    ProgrammerDto updateProgrammer(Long id);
    void deleteProgrammer(Long id);
}
