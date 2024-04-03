package pl.programmers.service;

import pl.programmers.pojo.ProgrammerDto;

import java.util.List;

public interface ProgrammerService {
    ProgrammerDto createProgrammer();
    List<ProgrammerDto> getProgrammers();
    ProgrammerDto getProgrammerById(Long id);
    ProgrammerDto updateProgrammer(Long id);
    void deleteProgrammer(Long id);
}
