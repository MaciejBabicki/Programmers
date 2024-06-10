package pl.programmers.programmer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class GithubRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean fork;
    @OneToOne
    private Owner owner;
    @OneToMany
    private List<Branch> branches;
    private String url;
}
