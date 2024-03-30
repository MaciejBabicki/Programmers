package pl.programmers.pojo;

public record ProgrammerDto(
        Long id,
        String firstName,
        String lastName,
        String repoName
) {
}
