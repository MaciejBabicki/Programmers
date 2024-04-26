package pl.programmers.programmer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.pojo.ProgrammerDto;
import pl.programmers.programmer.service.ProgrammerServiceImpl;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/programmers")
public class ProgrammerController {
    private final ProgrammerServiceImpl programmerServiceImpl;

    @PostMapping("/add-programmer ")
    public ProgrammerDto createProgrammer(@RequestBody ProgrammerDto programmerDto) {
        Programmer programmerRequest = new Programmer();
        programmerRequest.setId(programmerDto.getId());
        programmerRequest.setFirstName(programmerDto.getFirstName());
        programmerRequest.setLastName(programmerDto.getLastName());
        programmerRequest.setRepoName(programmerDto.getRepoName());
        return programmerServiceImpl.createProgrammer(programmerRequest);
    }

    @GetMapping
    public List<ProgrammerDto> getProgrammers(@RequestParam Integer page, @RequestParam Sort.Direction sort, @RequestParam Integer limit) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
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
