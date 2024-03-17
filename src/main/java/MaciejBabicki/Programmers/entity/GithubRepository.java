package MaciejBabicki.Programmers.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GithubRepository {

    @Id
    private String name;
    private String url;

}
