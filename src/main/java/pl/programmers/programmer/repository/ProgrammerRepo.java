package pl.programmers.programmer.repository;

import pl.programmers.programmer.entity.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammerRepo extends JpaRepository<Programmer, Long> {
}
