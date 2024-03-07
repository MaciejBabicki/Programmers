package MaciejBabicki.Programmers.github.client;

import MaciejBabicki.Programmers.github.entity.Github;
import MaciejBabicki.Programmers.github.repository.GithubRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
@Slf4j
public class Client2 {

    private final GithubRepo githubRepo;
    Github github = new Github();

    private String githubApiUrl = "https://api.github.com";
    private String login = "MaciejBabicki";
    String url = githubApiUrl + "/users/" + login + "/repos?affiliation=owner";

    public Github getGithub(){

        WebClient.Builder builder = WebClient.builder();

        Github githubResponse = builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Github.class)
                .block();

        log.info(githubResponse.toString());

        String name = githubResponse.getName();
        log.info("Name: " + name);

        github.setName(name);
        Github github1 = githubRepo.save(github);
        log.info(github1.toString());

        return null;
    }
}
