package pl.programmers.programmer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.exception.ResourceNotFoundException;
import pl.programmers.programmer.mapper.ProgrammerMapper;
import pl.programmers.programmer.pojo.ProgrammerDto;
import pl.programmers.programmer.repository.ProgrammerRepo;
import pl.programmers.programmer.service.importservice.ProgrammerImportService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgrammerServiceImpl implements ProgrammerService {
    private final ProgrammerRepo programmerRepo;
    private final ProgrammerImportService service;

    @Override
    public ProgrammerDto createProgrammer(Programmer programmerRequest) {
        Programmer programmer = new Programmer();
        programmer.setId(programmerRequest.getId());
        programmer.setFirstName(programmerRequest.getFirstName());
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
