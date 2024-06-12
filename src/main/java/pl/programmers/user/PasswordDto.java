package pl.programmers.user;

public record PasswordDto(
        String actualPassword,
        String newPassword
) {
}
