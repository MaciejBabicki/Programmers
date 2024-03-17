package MaciejBabicki.Programmers.programmer.dto;

import MaciejBabicki.Programmers.github.pojo.GithubRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProgrammerDto {
    private Long id;
    private String repoName;
    private List<GithubRepository> repos;
    private String firstName;
    private String lastName;

}
