package pl.programmers.programmer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.programmers.programmer.entity.GithubRepository;
import pl.programmers.programmer.pojo.GithubRepositoryDto;
import pl.programmers.programmer.service.GithubRepositoryService;
import pl.programmers.programmer.service.importservice.GithubImportService;
import pl.programmers.programmer.service.importservice.TechnologiesInRepo;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repos")
public class GithubController {
    private final GithubImportService githubImportService;
    private final GithubRepositoryService githubRepositoryService;
    private final TechnologiesInRepo technologiesInRepo;

    @PostMapping
    public GithubRepositoryDto createGithubRepository() {
        GithubRepository githubRepositoryRequest = new GithubRepository();
        return githubRepositoryService.createRepository(githubRepositoryRequest);
    }

    @PostMapping("/import-save")
    public GithubRepositoryDto saveGithubRepository() {
        String name = "programmers";
        GithubRepository githubRepository = githubImportService.getGithubRepository(name);
        return githubRepositoryService.createRepository(githubRepository);
    }

    @GetMapping
    public List<GithubRepository> getGithubRepositories() {
        return githubImportService.getGithubRepositories();
    }

    @GetMapping("/dbrepositories")
    public List<GithubRepositoryDto> githubRepositoriesFromDB() {
        return githubRepositoryService.getGithubRepositories();
    }

    @GetMapping("/one")
    public GithubRepository getOneRepository() {
        String aa = "programmers";
        return githubImportService.getGithubRepository(aa);
    }

    @GetMapping("/technologies")
    public void getTechnologies() {
        technologiesInRepo.showTechnologies();
    }
}
