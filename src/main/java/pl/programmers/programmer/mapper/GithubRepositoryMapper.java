package pl.programmers.programmer.mapper;

import pl.programmers.programmer.entity.GithubRepository;
import pl.programmers.programmer.pojo.GithubRepositoryDto;
public class GithubRepositoryMapper {
    public static GithubRepositoryDto mapToGithubRepositoryDto(GithubRepository githubRepository) {
        if (githubRepository == null) {
            throw new NullPointerException("programmer to map is null");
        }
        return new GithubRepositoryDto(
                githubRepository.getId(),
                githubRepository.getName(),
                githubRepository.isFork(),
                githubRepository.getOwner(),
                githubRepository.getBranches(),
                githubRepository.getUrl()
        );
    }
}
