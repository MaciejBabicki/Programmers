package pl.programmers.programmer.service;

import org.springframework.stereotype.Service;
import pl.programmers.programmer.entity.GithubRepository;
import pl.programmers.programmer.mapper.GithubRepositoryMapper;
import pl.programmers.programmer.pojo.GithubRepositoryDto;
import pl.programmers.programmer.repository.GithubRepositoryRepo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GithubRepositoryService {
    GithubRepositoryRepo repo;
    public GithubRepositoryDto createRepository(GithubRepository githubrepositoryRequest){
        GithubRepository githubRepository = new GithubRepository();
        githubRepository.setId(githubrepositoryRequest.getId());
        githubRepository.setName(githubrepositoryRequest.getName());
        githubRepository.setFork(false);
        githubRepository.setOwner(githubrepositoryRequest.getOwner());
        githubRepository.setBranches(githubrepositoryRequest.getBranches());
        if (repo !=null) {
            repo.save(githubRepository);
        }
        return GithubRepositoryMapper.mapToGithubRepositoryDto(githubRepository);
    }
    public List<GithubRepositoryDto> getGithubRepositories() {
        List<GithubRepository> githubRepositories = repo.findAll();
        if (githubRepositories != null && !githubRepositories.isEmpty()) {
            return githubRepositories.stream()
                    .map(GithubRepositoryMapper::mapToGithubRepositoryDto)
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }
}
