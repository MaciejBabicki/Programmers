package pl.programmers.programmer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.programmers.programmer.entity.GithubRepository;
@Repository
public interface GithubRepositoryRepo extends JpaRepository<GithubRepository, Long> {
}
