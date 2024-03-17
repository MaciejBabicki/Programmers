package MaciejBabicki.Programmers.controller;

import MaciejBabicki.Programmers.dto.ProgrammerDto;
import MaciejBabicki.Programmers.pojo.GithubRepository;
import MaciejBabicki.Programmers.service.ProgrammerServiceImpl;
import MaciejBabicki.Programmers.service.Service1;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/programmers")
public class ProgrammerController {

    private final ProgrammerServiceImpl programmerServiceImpl;
    private final Service1 service1;
    @GetMapping("/github")
    public List<GithubRepository> getGithub1() {
        return service1.getGithubRepositories();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProgrammerDto> createProgrammer(@RequestBody ProgrammerDto programmerDto){
        ProgrammerDto savedProgrammerDto = programmerServiceImpl.createProgrammer(programmerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProgrammerDto);
    }

    @GetMapping
    public ResponseEntity<List<ProgrammerDto>> getProgrammers(){
        List<ProgrammerDto> programmerDtos = programmerServiceImpl.getProgrammers();
        return ResponseEntity.ok(programmerDtos);
    }

    @GetMapping("/{id}")
    public ProgrammerDto getProgrammerById(@PathVariable("id") Long id ){
        return programmerServiceImpl.getProgrammerById(id);
    }


}
