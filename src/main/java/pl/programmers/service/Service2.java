package pl.programmers.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class Service2 {

    private final ProgrammerRepo programmerRepo;
    @Value("&{github.api.login}")
    String login;
    @Value("${github.api.url}")
    private String githubApiUrl;

    public Programmer createProgrammmer() {

        WebClient.Builder builder = WebClient.builder();
        if (githubApiUrl == null) {
            throw new IllegalStateException("GithubApiUrl can't be null");
        }
        String url = githubApiUrl + "/users/" + login + "/repos";
        List<GithubRepository> githubResponse = builder
                .build().get().uri(url).retrieve()
                .bodyToFlux(GithubRepository.class)
                .collectList()
                .block();

        if (githubResponse == null) {
            throw new EmptyRepositoryException();
        }
        String name = githubResponse.get(1).getName();
        log.info("Name: " + name);
        Programmer programmer = new Programmer();
        programmer.setRepoName(name);
        return programmerRepo.save(programmer);
    }
}
