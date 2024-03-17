package MaciejBabicki.Programmers.entity;

import MaciejBabicki.Programmers.pojo.Branch;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GithubRepository {

    @Id
    private String name;
    private boolean fork;
    @OneToOne
    private Owner owner;
    @OneToMany
    private List<Branch> branches;
    private String url;


}
