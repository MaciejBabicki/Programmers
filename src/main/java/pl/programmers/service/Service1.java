package pl.programmers.service;

import pl.programmers.entity.Branch;
import pl.programmers.entity.GithubRepository;
import pl.programmers.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class Service1 {

    private final RestTemplate restTemplate;

    @Value("${github.api.url}")
    private String githubApiUrl;
    @Value("${github.login}")
    private String login;

    public Service1(RestTemplate restTemplate)  {
        this.restTemplate = restTemplate;
    }
    public List<GithubRepository> getGithubRepositories() {
        String url = githubApiUrl + "/users/" + login + "/repos";

        ResponseEntity<GithubRepository[]> responseEntity = restTemplate.getForEntity(url, GithubRepository[].class);
        if (responseEntity.getBody() == null) {
            throw new ResourceNotFoundException();
        }
        GithubRepository[] allRepositories = responseEntity.getBody();
        return Arrays.stream(allRepositories)
                .peek(this::fetchBranches)
                .toList();
    }

    void fetchBranches(GithubRepository githubRepository) {
        String branchesUrl = githubRepository.getUrl() + "/branches";
        ResponseEntity<Branch[]> branchesResponseEntity = restTemplate.getForEntity(branchesUrl, Branch[].class);
        Branch[] branches = branchesResponseEntity.getBody();
    }
}
