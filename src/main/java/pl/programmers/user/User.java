package pl.programmers.user;

import jakarta.persistence.*;
import lombok.Data;
import pl.programmers.programmer.entity.Programmer;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(mappedBy = "ownerUsers")
    private List<Programmer>programmers;

    public User(Long id, String s, String s1) {
    }
}
