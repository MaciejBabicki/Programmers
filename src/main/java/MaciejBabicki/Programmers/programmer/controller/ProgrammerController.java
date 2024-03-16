package MaciejBabicki.Programmers.programmer.controller;

import MaciejBabicki.Programmers.github.client.Client3;
import MaciejBabicki.Programmers.programmer.dto.ProgrammerDto;
import MaciejBabicki.Programmers.programmer.entity.Programmer;
import MaciejBabicki.Programmers.programmer.repository.ProgrammerRepo;
import MaciejBabicki.Programmers.programmer.service.ProgrammerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/programmer")
public class ProgrammerController {

    private final ProgrammerService programmerService;
    private final ProgrammerRepo programmerRepo;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProgrammerDto> createProgrammer(@RequestBody ProgrammerDto programmerDto){
        ProgrammerDto savedProgrammerDto = programmerService.createProgrammer(programmerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProgrammerDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProgrammerDto>> getProgrammers(){
        List<ProgrammerDto> programmerDtos = programmerService.getProgrammers();
        return ResponseEntity.ok(programmerDtos);
    }

    @GetMapping("/{id}")
    public ProgrammerDto getProgrammerById(@PathVariable("id") Long id ){
        return programmerService.getProgrammerById(id);
    }


}
