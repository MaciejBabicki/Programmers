package MaciejBabicki.Programmers.github.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Owner {
    @Id
    private String login;

}
