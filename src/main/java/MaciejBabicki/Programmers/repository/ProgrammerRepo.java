package MaciejBabicki.Programmers.repository;

import MaciejBabicki.Programmers.entity.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammerRepo extends JpaRepository<Programmer, Long> {
}
