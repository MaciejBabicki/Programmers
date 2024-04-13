package pl.programmers.programmer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.programmers.programmer.entity.GithubRepository;
import pl.programmers.programmer.service.importservice.GithubImportService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/githubrepositories")
public class GithubController {
    private final GithubImportService githubImportService;

    @GetMapping
    public List<GithubRepository> getGithub() {
        return githubImportService.getGithubRepositories();
    }
}