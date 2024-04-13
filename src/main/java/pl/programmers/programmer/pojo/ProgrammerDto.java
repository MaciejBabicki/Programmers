package pl.programmers.programmer.pojo;

public record ProgrammerDto(
        Long id,
        String firstName,
        String lastName,
        String repoName
) {
}
