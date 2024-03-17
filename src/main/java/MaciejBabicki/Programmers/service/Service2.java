package MaciejBabicki.Programmers.service;

import MaciejBabicki.Programmers.entity.GithubRepository;
import MaciejBabicki.Programmers.entity.Programmer;
import MaciejBabicki.Programmers.repository.ProgrammerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class Service2 {

    private final ProgrammerRepo programmerRepo;

    Programmer programmer = new Programmer(1L, "repo", new ArrayList<>(), "maciej", "babicki");

    @Value("${github.api.url}")
    private String githubApiUrl;
    String login = "MaciejBabicki";

    public Programmer createProgrammmer() {

        WebClient.Builder builder = WebClient.builder();
        if (githubApiUrl == null) {


            throw new IllegalStateException("GithubApiUrl can't be null");
        }
        String url = githubApiUrl + "/users/" + login + "/repos";

        List<GithubRepository> allRepositories = new ArrayList<>();
        List<GithubRepository> githubResponse = builder.build().get().uri(url).retrieve().bodyToFlux(GithubRepository.class).collectList().block();
        log.info(githubResponse.toString());

        allRepositories.addAll(githubResponse);

        log.info(allRepositories.toString());

        String name = githubResponse.getLast().getName();

        allRepositories = githubResponse.stream().collect(Collectors.toList());


        log.info("Name: " + name);
        programmer.setRepoName(name);

        return programmerRepo.save(programmer);
    }
}
