package pl.programmers.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;

    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User with email" + email + "doesn't exist");
        }
        return userMapper.mapToDto(user);
    }

    public UserDto register(RegisteredUserDto registeredUserDto) {
        User user = userMapper.mapToEntity(registeredUserDto);
        user.setPassword(encoder.encode(registeredUserDto.password()));
        User savedUser = userRepository.save(user);
        return userMapper.mapToDto(savedUser);
    }

    public void resetPassword(PasswordDto passwordDto, String email) {
        User user = userRepository.findByEmail(email);
        if (encoder.matches(passwordDto.actualPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(passwordDto.newPassword()));
        } else {
            throw new RuntimeException();
        }
    }
}
