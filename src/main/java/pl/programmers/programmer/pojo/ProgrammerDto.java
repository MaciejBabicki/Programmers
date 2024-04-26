package pl.programmers.programmer.pojo;

import lombok.Getter;

public record ProgrammerDto(
        @Getter
        Long id,
        @Getter
        String firstName,
        @Getter
        String lastName,
        @Getter
        String repoName
) {
}
