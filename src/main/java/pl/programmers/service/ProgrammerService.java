package pl.programmers.service;

import pl.programmers.entity.Programmer;

import java.util.List;

public interface ProgrammerService {

    Programmer createProgrammer();

    List<Programmer> getProgrammers();

    Programmer getProgrammerById(Long id);

}
