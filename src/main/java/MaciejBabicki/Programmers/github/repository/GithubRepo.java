package MaciejBabicki.Programmers.github.repository;

import MaciejBabicki.Programmers.github.entity.Github;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubRepo extends JpaRepository<Github, Long> {
}
