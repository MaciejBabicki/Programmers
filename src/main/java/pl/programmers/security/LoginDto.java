package pl.programmers.security;

public record LoginDto(
        String email,
        String password
) {
}
