package pl.programmers.mapper;

import pl.programmers.entity.Programmer;
import pl.programmers.pojo.ProgrammerDto;

public class ProgrammerMapper {
    public static ProgrammerDto mapToProgrammerDto(Programmer programmer) {
        if (programmer != null) {
            return new ProgrammerDto(
                    programmer.getId(),
                    programmer.getFirstName(),
                    programmer.getFirstName(),
                    programmer.getRepoName()
            );
        } else {
            return null;
        }
    }
}
