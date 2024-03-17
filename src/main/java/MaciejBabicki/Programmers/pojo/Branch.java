package MaciejBabicki.Programmers.pojo;

import MaciejBabicki.Programmers.entity.GithubRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Branch {

    @Id
    private String name;

    @OneToOne
    private Commit Commit;

    @ManyToOne
    private GithubRepository githubRepository;

}
