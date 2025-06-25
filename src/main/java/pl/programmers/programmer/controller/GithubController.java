package pl.programmers.programmer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.programmers.programmer.entity.GithubRepository;
import pl.programmers.programmer.pojo.GithubRepositoryDto;
import pl.programmers.programmer.service.importservice.GithubImportService;
import pl.programmers.programmer.service.importservice.TechnologiesInRepo;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/repos")
public class GithubController {
    //Object which imports data from GitHub
    private final GithubImportService githubImportService;
    //Object which imports information about technologies
    private final TechnologiesInRepo technologiesInRepo;

    //Fething a repository from GitHub with exact title and technology
    @GetMapping("/{title}+{language}")
    public List<GithubRepositoryDto> getRepositoriesWithTitleAndLanguage() {
        return githubImportService.searchRepositoriesWithTitleAndLanguage();
    }

    //Show all repositories from GitHub for exact programmer
    @GetMapping
    public List<GithubRepository> getGithubRepositories() {
        return githubImportService.getGithubRepositories();
    }

    //Show technologies used in repository
    @GetMapping("/technologies")
    public void getTechnologies() {
        technologiesInRepo.showTechnologies();
    }
}
