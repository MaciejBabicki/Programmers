package MaciejBabicki.Programmers.github.client;

import MaciejBabicki.Programmers.programmer.entity.Programmer;
import MaciejBabicki.Programmers.github.pojo.GithubRepository;
import MaciejBabicki.Programmers.github.repository.GithubRepo;
import MaciejBabicki.Programmers.programmer.repository.ProgrammerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class Client3 {

    private final GithubRepo githubRepo;
    private final ProgrammerRepo programmerRepo;

    Programmer programmer = new Programmer(1l, "repo", "maciej");
    String githubApiUrl = "https://api.github.com";
    String login = "MaciejBabicki";
    String url = githubApiUrl + "/users/" + login + "/repos";

    public Programmer getProgrammmer() {

        WebClient.Builder builder = WebClient.builder();

        List <GithubRepository> githubResponse = builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(GithubRepository.class)
                .collectList()
                .block();

        log.info(githubResponse.toString());

        String name = githubResponse.getFirst().getName();
        String repoUrl = githubResponse.getLast().getUrl();
        log.info("Name: " + name);

        programmer.setRepoName(name);
        Programmer programmer1 = programmerRepo.save(programmer);
        log.info(programmer1.toString());

        return programmer1;
    }
}
