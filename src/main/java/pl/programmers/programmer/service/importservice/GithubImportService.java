package pl.programmers.programmer.service.importservice;

import pl.programmers.programmer.entity.Branch;
import pl.programmers.programmer.entity.GithubRepository;
import pl.programmers.programmer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubImportService {
    private final RestTemplate restTemplate;
    private final String githubApiUrl;
    private final String login;

    public GithubImportService(RestTemplate restTemplate, @Value("${github.api.url}")String githubApiUrl, @Value("${github.login}")String login) {
        this.restTemplate = restTemplate;
        this.githubApiUrl = githubApiUrl;
        this.login = login;
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
