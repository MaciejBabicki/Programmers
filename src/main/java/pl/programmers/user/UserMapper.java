package pl.programmers.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.programmers.programmer.entity.Programmer;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        imports = Programmer.class)
public interface UserMapper {
    UserDto mapToDto(User user);

    //    List<UserDto> mapToDtos(List<User> users);
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "programmers", expression = "java(newArrayList<Programmer>())")
    User mapToEntity(RegisteredUserDto registeredUserDto);
}
