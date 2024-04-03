package pl.programmers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.programmers.entity.Programmer;
import pl.programmers.exception.ResourceNotFoundException;
import pl.programmers.mapper.ProgrammerMapper;
import pl.programmers.pojo.ProgrammerDto;
import pl.programmers.repository.ProgrammerRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgrammerServiceImpl implements ProgrammerService {
    private final ProgrammerRepo programmerRepo;

    @Override
    public ProgrammerDto createProgrammer() {
        Programmer programmer = new Programmer();
        programmerRepo.save(programmer);
        return ProgrammerMapper.mapToProgrammerDto(programmer);
    }

    @Override
    public List<ProgrammerDto> getProgrammers() {
        List<Programmer> programmers = programmerRepo.findAll();
        return programmers.stream().map(ProgrammerMapper::mapToProgrammerDto).toList();
    }

    @Override
    public ProgrammerDto getProgrammerById(Long id) {
        Programmer programmer = programmerRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ProgrammerMapper.mapToProgrammerDto(programmer);
    }

    @Override
    public ProgrammerDto updateProgrammer(Long id) {
        Programmer programmer = programmerRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        programmer.setFirstName(programmer.getFirstName());
        programmer.setLastName(programmer.getLastName());
        programmer.setRepoName(programmer.getRepoName());
        return ProgrammerMapper.mapToProgrammerDto(programmer);
    }

    @Override
    public void deleteProgrammer(Long id) {
        programmerRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        programmerRepo.deleteById(id);
    }
}
