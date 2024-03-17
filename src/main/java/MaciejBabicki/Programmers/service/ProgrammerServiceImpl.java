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
    private final Service2 service2;

    @Override
    public Programmer createProgrammer() {
        Programmer programmer = service2.createProgrammmer();
        return programmerRepo.save(programmer);
    }

    @Override
    public List<Programmer> getProgrammers() {
        return programmerRepo.findAll();
    }

    @Override
    public Programmer getProgrammerById(Long id) {
        return programmerRepo
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);

    }


}
