package MaciejBabicki.Programmers.controller;

import MaciejBabicki.Programmers.entity.Programmer;
import MaciejBabicki.Programmers.entity.GithubRepository;
import MaciejBabicki.Programmers.service.ProgrammerServiceImpl;
import MaciejBabicki.Programmers.service.Service1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/programmer")
public class ProgrammerController {

    private final ProgrammerServiceImpl programmerServiceImpl;
    private final Service1 service1;

    @GetMapping("/github")
    public List<GithubRepository> getGithub1() {
        return service1.getGithubRepositories();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Programmer> createProgrammer(@RequestBody Programmer programmer) {
        Programmer savedProgrammer = programmerServiceImpl.createProgrammer(programmer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProgrammer);
    }

    @GetMapping
    public ResponseEntity<List<Programmer>> getProgrammers() {
        List<Programmer> programmers = programmerServiceImpl.getProgrammers();
        return ResponseEntity.ok(programmers);
    }

    @GetMapping("/{id}")
    public Programmer getProgrammerById(@PathVariable("id") Long id) {
        return programmerServiceImpl.getProgrammerById(id);
    }


}
