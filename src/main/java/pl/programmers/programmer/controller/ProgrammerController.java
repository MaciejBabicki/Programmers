package pl.programmers.programmer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.pojo.ProgrammerDto;
import pl.programmers.programmer.service.ProgrammerServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/programmers")
public class ProgrammerController {
    private final ProgrammerServiceImpl programmerServiceImpl;

    @PostMapping
    public ProgrammerDto createProgrammer(@RequestBody ProgrammerDto programmerDto) {
        Programmer programmerRequest = new Programmer();
        programmerRequest.setId(programmerDto.getId());
        programmerRequest.setFirstName(programmerDto.getFirstName());
        programmerRequest.setLastName(programmerDto.getLastName());
        programmerRequest.setRepoName(programmerDto.getRepoName());
        return programmerServiceImpl.createProgrammer(programmerRequest);
    }

    @GetMapping("/{id}")
    public ProgrammerDto getProgrammerById(@PathVariable("id") long id) {
        return programmerServiceImpl.getProgrammerById(id);
    }

    @PutMapping("/{id}")
    public ProgrammerDto updateProgrammer(@PathVariable("id") long id, @RequestBody ProgrammerDto updatedProgrammer) {
        return programmerServiceImpl.updateProgrammer(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProgrammer(@PathVariable("id") long id) {
        programmerServiceImpl.deleteProgrammer(id);
    }

    //Get programmers from DB
    @GetMapping
    public List<ProgrammerDto> getProgrammers() {
        return programmerServiceImpl.getProgrammers();
    }
}
