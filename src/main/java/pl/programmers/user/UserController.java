package pl.programmers.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDto findByEmail(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody @Valid RegisteredUserDto registeredUserDto) {
        return userService.register(registeredUserDto);
    }

}
