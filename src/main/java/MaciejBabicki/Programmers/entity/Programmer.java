package MaciejBabicki.Programmers.entity;

import MaciejBabicki.Programmers.pojo.GithubRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
