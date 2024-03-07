package MaciejBabicki.Programmers.programmer.controller;

import MaciejBabicki.Programmers.github.client.Client3;
import MaciejBabicki.Programmers.programmer.dto.ProgrammerDto;
import MaciejBabicki.Programmers.programmer.repository.ProgrammerRepo;
import MaciejBabicki.Programmers.programmer.service.ProgrammerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/programmer")
public class ProgrammerController {

    private final ProgrammerService programmerService;
    private final ProgrammerRepo programmerRepo;

    @GetMapping("/")
    public ResponseEntity<List<ProgrammerDto>> getProgrammers(){
        List<ProgrammerDto> programmerDtos = programmerService.getProgrammers();
        return ResponseEntity.ok(programmerDtos);
    }
}
