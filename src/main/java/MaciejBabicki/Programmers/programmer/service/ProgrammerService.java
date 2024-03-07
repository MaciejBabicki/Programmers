package MaciejBabicki.Programmers.programmer.service;

import MaciejBabicki.Programmers.programmer.dto.ProgrammerDto;
import MaciejBabicki.Programmers.programmer.entity.Programmer;
import MaciejBabicki.Programmers.programmer.mapper.ProgrammerMapper;
import MaciejBabicki.Programmers.programmer.repository.ProgrammerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ProgrammerDto> getProgrammers(){
        List<Programmer> programmers = programmerRepo.findAll();
        return programmers.stream()
                .map(ProgrammerMapper::mapToProgrammerDto)
                .toList();
    }
}
