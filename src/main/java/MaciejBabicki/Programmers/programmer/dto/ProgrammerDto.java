package MaciejBabicki.Programmers.programmer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProgrammerDto {
    private Long id;
    private String repoName;
    private String firstName;
}
