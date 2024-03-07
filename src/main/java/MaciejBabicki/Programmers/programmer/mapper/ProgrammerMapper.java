package MaciejBabicki.Programmers.programmer.mapper;

import MaciejBabicki.Programmers.programmer.dto.ProgrammerDto;
import MaciejBabicki.Programmers.programmer.entity.Programmer;


public class ProgrammerMapper {

    public static Programmer mapToProgrammer(ProgrammerDto programmerDto) {
        return new Programmer(
                programmerDto.getId(),
                programmerDto.getRepoName(),
                programmerDto.getFirstName()
        );
    }

    public static ProgrammerDto mapToProgrammerDto(Programmer programmer) {
        return new ProgrammerDto(
                programmer.getId(),
                programmer.getRepoName(),
                programmer.getFirstName()
        );
    }
}
