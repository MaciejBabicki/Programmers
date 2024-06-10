package pl.programmers.programmer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.exception.ResourceNotFoundException;
import pl.programmers.programmer.mapper.ProgrammerMapper;
import pl.programmers.programmer.pojo.ProgrammerDto;
import pl.programmers.programmer.repository.ProgrammerRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgrammerServiceImpl implements ProgrammerService {
    private final ProgrammerRepo repo;

    @Override
    public ProgrammerDto createProgrammer(Programmer programmerRequest) {
        Programmer programmer = new Programmer();
        programmer.setId(programmerRequest.getId());
        programmer.setFirstName(programmerRequest.getFirstName());
        programmer.setLastName(programmerRequest.getLastName());
        programmer.setRepoName(programmerRequest.getRepoName());

        repo.save(programmer);
        return ProgrammerMapper.mapToProgrammerDto(programmer);
    }

    @Override
    public List<ProgrammerDto> getProgrammers() {
        List<Programmer> programmers = repo.findAll();
        return programmers.stream().map(ProgrammerMapper::mapToProgrammerDto).toList();
    }

    @Override
    public ProgrammerDto getProgrammerById(long id) {
        Programmer programmer = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ProgrammerMapper.mapToProgrammerDto(programmer);
    }


    public ProgrammerDto updateProgrammer(long id, ProgrammerDto updatedProgrammerDto) {
        Programmer programmer = repo.findById(id).orElseThrow(ResourceNotFoundException::new);

        programmer.setFirstName(updatedProgrammerDto.getFirstName());
        programmer.setLastName(updatedProgrammerDto.getLastName());
        programmer.setRepoName(updatedProgrammerDto.getRepoName());

        Programmer updatedProgrammerObj = repo.save(programmer);

        return ProgrammerMapper.mapToProgrammerDto(programmer);
    }

    @Override
    public void deleteProgrammer(long id) {
        repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        repo.deleteById(id);
    }
}
