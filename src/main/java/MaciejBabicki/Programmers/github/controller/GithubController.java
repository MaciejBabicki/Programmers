package MaciejBabicki.Programmers.github.controller;

import MaciejBabicki.Programmers.github.service.Service1;
import MaciejBabicki.Programmers.github.service.Service2;
import MaciejBabicki.Programmers.github.pojo.GithubRepository;
import MaciejBabicki.Programmers.programmer.entity.Programmer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class GithubController {

    @Autowired
    private final Service1 service1;
    @Autowired
    private final Service2 service2;

    @GetMapping("/github")
    public List<GithubRepository> getGithub1() {
        return service1.getGithubRepositories();
    }

    @GetMapping("/programmer")
    public Programmer getProgrammer() {
        return service2.getProgrammmer();
    }


}
