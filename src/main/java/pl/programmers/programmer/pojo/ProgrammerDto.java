package pl.programmers.programmer.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.programmers.programmer.entity.GithubRepository;

@Getter
@AllArgsConstructor
public class ProgrammerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String repoName;
    private GithubRepository githubRepository;
}
