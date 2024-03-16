package MaciejBabicki.Programmers.programmer.mapper;

import MaciejBabicki.Programmers.programmer.dto.ProgrammerDto;
import MaciejBabicki.Programmers.programmer.entity.Programmer;


public class ProgrammerMapper {

    public static Programmer mapToProgrammer(ProgrammerDto programmerDto) {
        return new Programmer(
                programmerDto.getId(),
                programmerDto.getRepoName(),
                programmerDto.getRepos(),
                programmerDto.getFirstName(),
                programmerDto.getLastName(),
                programmerDto.getOwner()

        );
    }

    public static ProgrammerDto mapToProgrammerDto(Programmer programmer) {
        return new ProgrammerDto(
                programmer.getId(),
                programmer.getRepoName(),
                programmer.getRepos(),
                programmer.getFirstName(),
                programmer.getLastName(),
                programmer.getOwner()
        );
    }
}
