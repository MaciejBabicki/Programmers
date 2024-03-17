package MaciejBabicki.Programmers.service;

import MaciejBabicki.Programmers.entity.Programmer;

import java.util.List;

public interface ProgrammerService {

    Programmer createProgrammer();

    List<Programmer> getProgrammers();

    Programmer getProgrammerById(Long id);
}
