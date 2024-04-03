package pl.programmers.controller;

import pl.programmers.pojo.ProgrammerDto;
import pl.programmers.service.ProgrammerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/programmers")
public class ProgrammerController {
    private final ProgrammerServiceImpl programmerServiceImpl;

    @PostMapping
    public ProgrammerDto createProgrammer(@RequestBody ProgrammerDto programmerDto) {
        return programmerServiceImpl.createProgrammer();
    }

    @GetMapping
    public List<ProgrammerDto> getProgrammers() {
        return programmerServiceImpl.getProgrammers();
    }

    @GetMapping("/{id}")
    public ProgrammerDto getProgrammerById(@PathVariable("id") Long id) {
        return programmerServiceImpl.getProgrammerById(id);
    }

    @PutMapping("/{id}")
    public ProgrammerDto updateProgrammer(@PathVariable("id") Long id) {
        return programmerServiceImpl.updateProgrammer(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProgrammer(@PathVariable("id") Long id) {
        programmerServiceImpl.deleteProgrammer(id);
    }
}
