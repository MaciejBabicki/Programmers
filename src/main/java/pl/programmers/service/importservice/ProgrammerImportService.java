package pl.programmers.service.importservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.programmers.entity.GithubRepository;
import pl.programmers.entity.Programmer;
import pl.programmers.exception.EmptyRepositoryException;
import pl.programmers.repository.ProgrammerRepo;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProgrammerImportService {
    private final ProgrammerRepo programmerRepo;
    private String url;
    private List<GithubRepository> githubResponse;

    public Programmer getProgrammers() {
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

    private List<GithubRepository> fetchGithubRepositories(WebClient.Builder builder) {
        url = "${github.api.url}" + "/users/" + "${github.login}" + "/repos";
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
