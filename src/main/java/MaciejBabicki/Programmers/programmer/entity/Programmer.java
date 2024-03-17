package MaciejBabicki.Programmers.programmer.entity;

import MaciejBabicki.Programmers.github.pojo.GithubRepository;
import MaciejBabicki.Programmers.github.pojo.Owner;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String repoName;
    @OneToMany
    private List<GithubRepository> repos;
    private String firstName;
    private String lastName;

}
