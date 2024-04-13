package pl.programmers.programmer.mapper;

import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.pojo.ProgrammerDto;

public class ProgrammerMapper {
    public static ProgrammerDto mapToProgrammerDto(Programmer programmer) {
        if (programmer == null) {
            throw new NullPointerException("programmer to map is null");
        }
        return new ProgrammerDto(
                programmer.getId(),
                programmer.getFirstName(),
                programmer.getLastName(),
                programmer.getRepoName()
        );
    }
}
