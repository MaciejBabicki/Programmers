package pl.programmers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.programmers.programmer.service.importservice.TechnologiesInRepo;

@SpringBootApplication
public class ProgrammersApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProgrammersApplication.class, args);
    }
}
