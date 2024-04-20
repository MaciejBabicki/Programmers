package pl.programmers.user;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }


    @Override
    public User mapToEntity(RegisteredUserDto registeredUserDto) {
        return new User(
                registeredUserDto.id(),
                registeredUserDto.firstName(),
                registeredUserDto.lastName()
        );
    }
}
