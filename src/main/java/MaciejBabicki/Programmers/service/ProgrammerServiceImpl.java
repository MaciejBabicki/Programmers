package MaciejBabicki.Programmers.service;

import MaciejBabicki.Programmers.entity.Programmer;
import MaciejBabicki.Programmers.exception.ResourceNotFoundException;
import MaciejBabicki.Programmers.repository.ProgrammerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgrammerServiceImpl implements ProgrammerService {

    private final ProgrammerRepo programmerRepo;

    @Override
    public Programmer createProgrammer(Programmer programmer) {
        return programmerRepo.save(programmer);
    }

    @Override
    public List<Programmer> getProgrammers() {
        List<Programmer> programmers = programmerRepo.findAll();
        return programmers;
    }

    @Override
    public Programmer getProgrammerById(Long id) {
        Programmer programmer = programmerRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        return programmer;

    }


}
