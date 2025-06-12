package pl.programmers.programmer.service.importservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.programmers.programmer.entity.Branch;
import pl.programmers.programmer.entity.GithubRepository;
import pl.programmers.programmer.exception.ResourceNotFoundException;
import pl.programmers.programmer.pojo.GithubRepositoryResponse;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubImportService {
    private final RestTemplate restTemplate;
    private final String githubApiUrl;
    private final String login;
    private final String githubSearchQuery;
    private final String language;
    private final String githubApiUrl2;

    public GithubImportService(RestTemplate restTemplate, @Value("${github.api.url}") String githubApiUrl,
                               @Value("${github.login}") String login, @Value("${github.search.query}") String githubSearchQuery,
                               @Value("${github.search.language}") String language,
                               @Value("${github.api.url2}") String githubApiUrl2) {
        this.restTemplate = restTemplate;
        this.githubApiUrl = githubApiUrl;
        this.login = login;
        this.githubSearchQuery = githubSearchQuery;
        this.language = language;
        this.githubApiUrl2 = githubApiUrl2;
    }

    public List<GithubRepository> searchRepositoriesWithTitleAndLanguage() {
        String url = githubApiUrl2 + "?q=" + githubSearchQuery + "+language:" + language;
        GithubRepositoryResponse responseEntity = restTemplate.
                getForObject(url, GithubRepositoryResponse.class);
        if (responseEntity != null) {
            return responseEntity.getItems();
        }
        return List.of();
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

    List<Branch> fetchBranches(GithubRepository githubRepository) {
        String branchesUrl = githubRepository.getUrl() + "/branches";
        ResponseEntity<Branch[]> branchesResponseEntity = restTemplate.getForEntity(branchesUrl, Branch[].class);
        return Arrays.asList(branchesResponseEntity.getBody());
    }
}
