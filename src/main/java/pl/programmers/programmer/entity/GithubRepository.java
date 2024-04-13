package pl.programmers.programmer.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class GithubRepository {

    private String name;
    private boolean fork;
    private Owner owner;
    private List<Branch> branches;
    private String url;


}
