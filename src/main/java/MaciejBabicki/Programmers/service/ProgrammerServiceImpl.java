package MaciejBabicki.Programmers.service;

import MaciejBabicki.Programmers.dto.ProgrammerDto;
import MaciejBabicki.Programmers.entity.Programmer;
import MaciejBabicki.Programmers.exception.ResourceNotFoundException;
import MaciejBabicki.Programmers.mapper.ProgrammerMapper;
import MaciejBabicki.Programmers.repository.ProgrammerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgrammerServiceImpl implements ProgrammerService {

    private final ProgrammerRepo programmerRepo;

    @Override
    public ProgrammerDto createProgrammer(ProgrammerDto programmerDto) {
        Programmer programmer = ProgrammerMapper.mapToProgrammer(programmerDto);
        Programmer savedProgrammer = programmerRepo.save(programmer);
        return ProgrammerMapper.mapToProgrammerDto(savedProgrammer);
    }

    @Override
    public List<ProgrammerDto> getProgrammers(){
        List<Programmer> programmers = programmerRepo.findAll();
        return programmers.stream()
                .map(ProgrammerMapper::mapToProgrammerDto)
                .toList();
    }

    @Override
    public ProgrammerDto getProgrammerById(Long id) {
        Programmer programmer=programmerRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        return ProgrammerMapper.mapToProgrammerDto(programmer);

    }


}
