package pl.programmers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.programmers.entity.Programmer;
import pl.programmers.exception.ResourceNotFoundException;
import pl.programmers.repository.ProgrammerRepo;

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
