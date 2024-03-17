package MaciejBabicki.Programmers.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Owner {
    @Id
    private String login;
}
