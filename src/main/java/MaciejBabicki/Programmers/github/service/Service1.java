package MaciejBabicki.Programmers.github.service;

import MaciejBabicki.Programmers.github.pojo.Branch;
import MaciejBabicki.Programmers.github.pojo.GithubRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Service1 {

    private String githubApiUrl = "https://api.github.com";
    private String login = "MaciejBabicki";
    private final RestTemplate restTemplate;

    public Service1(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GithubRepository> getGithubRepositories(){
        String url = githubApiUrl + "/users/" + login + "/repos";

        ResponseEntity<GithubRepository[]> responseEntity = restTemplate.getForEntity(url, GithubRepository[].class);
        if(responseEntity.getBody() != null) {
            GithubRepository[] allRepositories = responseEntity.getBody();

            assert allRepositories != null;
            return Arrays.stream(allRepositories)
                    .peek(this::fetchBranches)
                    .toList();
        } else {
            throw new RuntimeException("exception");
        }
    }

    void fetchBranches(GithubRepository githubRepository){

            String branchesUrl = githubRepository.getUrl() + "/branches";
            ResponseEntity<Branch[]> branchesResponseEntity = restTemplate.getForEntity(branchesUrl, Branch[].class);
            Branch[] branches = branchesResponseEntity .getBody();
    }
}
