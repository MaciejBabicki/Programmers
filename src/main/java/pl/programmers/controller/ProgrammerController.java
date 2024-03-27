package pl.programmers.controller;

import pl.programmers.pojo.ProgrammerDto;
import pl.programmers.service.ProgrammerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/programmers")
public class ProgrammerController {
    private final ProgrammerService programmerService;

    @PostMapping
    public ProgrammerDto createProgrammer(@RequestBody ProgrammerDto programmerDto) {
        return programmerService.createProgrammer();
    }

    @GetMapping
    public List<ProgrammerDto> getProgrammers() {
        return programmerService.getProgrammers();
    }

    @GetMapping("/{id}")
    public ProgrammerDto getProgrammerById(@PathVariable("id") Long id) {
        return programmerService.getProgrammerById(id);
    }

    @PutMapping("/{id}")
    public ProgrammerDto updateProgrammer(@PathVariable("id") Long id) {
        return programmerService.updateProgrammer(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProgrammer(@PathVariable("id") Long id) {
        programmerService.deleteProgrammer(id);
    }
}
