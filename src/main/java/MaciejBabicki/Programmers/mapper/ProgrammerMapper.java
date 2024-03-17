package MaciejBabicki.Programmers.mapper;

import MaciejBabicki.Programmers.dto.ProgrammerDto;
import MaciejBabicki.Programmers.entity.Programmer;


public class ProgrammerMapper {

    public static Programmer mapToProgrammer(ProgrammerDto programmerDto) {
        return new Programmer(
                programmerDto.getId(),
                programmerDto.getRepoName(),
                programmerDto.getRepos(),
                programmerDto.getFirstName(),
                programmerDto.getLastName()

        );
    }

    public static ProgrammerDto mapToProgrammerDto(Programmer programmer) {
        return new ProgrammerDto(
                programmer.getId(),
                programmer.getRepoName(),
                programmer.getRepos(),
                programmer.getFirstName(),
                programmer.getLastName()
        );
    }
}
