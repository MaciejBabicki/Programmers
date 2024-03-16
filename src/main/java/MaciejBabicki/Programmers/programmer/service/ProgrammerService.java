package MaciejBabicki.Programmers.programmer.service;

import MaciejBabicki.Programmers.programmer.dto.ProgrammerDto;
import MaciejBabicki.Programmers.programmer.entity.Programmer;
import MaciejBabicki.Programmers.programmer.exception.ResourceNotFoundException;
import MaciejBabicki.Programmers.programmer.mapper.ProgrammerMapper;
import MaciejBabicki.Programmers.programmer.repository.ProgrammerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgrammerService implements ProgrammerPreService{

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
