package pl.programmers.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgrammerDto {
    private Long id;
    private String repoName;
    private String firstName;
    private String lastName;
}
