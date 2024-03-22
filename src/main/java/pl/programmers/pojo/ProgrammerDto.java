package pl.programmers.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProgrammerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String repoName;
}
