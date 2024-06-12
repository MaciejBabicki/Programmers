package pl.programmers.user;

import jakarta.validation.constraints.Email;

public record RegisteredUserDto(
        Long id,
        String firstName,
        String lastName,
        @Email(regexp = ".+[@].+[\\.]+")
        String email,
        String password
) {
}
