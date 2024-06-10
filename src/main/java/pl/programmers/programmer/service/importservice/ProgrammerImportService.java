package pl.programmers.programmer.service.importservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.programmers.programmer.entity.GithubRepository;
import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.exception.EmptyRepositoryException;
import pl.programmers.programmer.repository.ProgrammerRepo;

import java.util.List;

@Service
@Slf4j
public class ProgrammerImportService {
    private final ProgrammerRepo programmerRepo;
    private final String githubApiUrl;
    private final String login;
    private String url;
    private List<GithubRepository> githubResponse;

    public ProgrammerImportService(ProgrammerRepo programmerRepo, @Value("${github.api.url}") String githubApiUrl, @Value("${github.login}") String login) {
        this.programmerRepo = programmerRepo;
        this.githubApiUrl = githubApiUrl;
        this.login = login;
    }

    public Programmer getProgrammers(String string) {
        WebClient.Builder builder = prepareWebClientBuilder();
        String repositoryName = extractRepositoryName(githubResponse);
        log.info("Name: " + repositoryName);
        return createProgrammer();
    }

    private Programmer createProgrammer() {
        Programmer programmer = new Programmer();
        return programmerRepo.save(programmer);
    }

    private String extractRepositoryName(List<GithubRepository> githubResponse) {
        if (githubResponse == null) {
            throw new EmptyRepositoryException();
        }
        return githubResponse.get(2).getName();
    }

    public List<GithubRepository> fetchGithubRepositories(WebClient.Builder builder) {
        url = githubApiUrl + "/users/" + login + "/repos";
        githubResponse = builder.build().get()
                .uri(url)
                .retrieve()
                .bodyToFlux(GithubRepository.class)
                .collectList()
                .block();
        return githubResponse;
    }

    private WebClient.Builder prepareWebClientBuilder() {
        WebClient.Builder builder = WebClient.builder();
        if (url == null) {
            throw new EmptyRepositoryException();
        }
        return builder;
    }
}
