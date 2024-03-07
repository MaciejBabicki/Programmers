package MaciejBabicki.Programmers.github.controller;

import MaciejBabicki.Programmers.github.client.Client3;
import MaciejBabicki.Programmers.github.client.Client1;
import MaciejBabicki.Programmers.github.client.Client2;
import MaciejBabicki.Programmers.github.entity.Github;
import MaciejBabicki.Programmers.programmer.entity.Programmer;
import MaciejBabicki.Programmers.github.pojo.GithubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GithubController {

    @Autowired
    private final Client1 client1;
    @Autowired
    private final Client2 client2;
    @Autowired
    private final Client3 cLient3;

    @GetMapping("/api1")
    public List<GithubRepository> getGithub1(){
        return client1.getGithubRepositories();
    }

    @GetMapping("/api2")
    public  Github getGithub(){
        return client2.getGithub();
    }

    @GetMapping("/api3")
    public Programmer getGithub3(){
        return cLient3.getProgrammmer();
    }



}
