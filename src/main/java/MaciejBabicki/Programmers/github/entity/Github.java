package MaciejBabicki.Programmers.github.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Github {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
