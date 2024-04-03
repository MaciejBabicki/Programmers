package pl.programmers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.programmers.entity.GithubRepository;
import pl.programmers.service.importservice.GithubImportService;

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
