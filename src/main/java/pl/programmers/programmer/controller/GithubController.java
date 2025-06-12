package pl.programmers.programmer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    //Object which imports data from Github
    private final GithubImportService githubImportService;
    //Object which save records to DB
    private final GithubRepositoryService githubRepositoryService;
    //Object which imports information about technologies
    private final TechnologiesInRepo technologiesInRepo;
    String code;

    //Fething a repository from GitHub with exact title and technology
    @GetMapping("/{title}+{language}")
    public List<GithubRepository> getRepositoriesWithTitleAndLanguage() {
        return githubImportService.searchRepositoriesWithTitleAndLanguage();
    }
    //Fething and saving to DB repository with exact title and technology
    @PostMapping
    public GithubRepositoryDto createGithubRepository() {
        GithubRepository githubRepositoryRequest = githubImportService
                .searchRepositoriesWithTitleAndLanguage().getFirst();
        return githubRepositoryService.createRepository(githubRepositoryRequest);
    }

    //Show all repositories from GitHub for exact programmer
    @GetMapping
    public List<GithubRepository> getGithubRepositories() {
        return githubImportService.getGithubRepositories();
    }

    //Show repositories from DB
    @GetMapping("/dbrepositories")
    public List<GithubRepositoryDto> githubRepositoriesFromDB() {
        return githubRepositoryService.getGithubRepositories();
    }

    //Show technologies used in repository
    @GetMapping("/technologies")
    public void getTechnologies() {
        technologiesInRepo.showTechnologies(code);
    }
}
