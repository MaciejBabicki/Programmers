package pl.programmers.programmer.pojo;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.programmers.programmer.entity.Branch;
import pl.programmers.programmer.entity.Owner;

import java.util.List;
@Getter
@AllArgsConstructor
public class GithubRepositoryDto {

    private Long id;
    private String name;
    private boolean fork;
    private Owner owner;
    private List<Branch> branches;
    private String url;
}
