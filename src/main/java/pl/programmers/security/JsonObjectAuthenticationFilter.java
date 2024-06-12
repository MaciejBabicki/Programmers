package pl.programmers.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.unauthenticated;

public class JsonObjectAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login", "POST");
    private final ObjectMapper objectMapper;

    public JsonObjectAuthenticationFilter(ObjectMapper objectMapper) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        String username;
        String password;
        try {
            LoginDto loginDto = objectMapper.readValue(request.getReader(), LoginDto.class);
            username = loginDto.email();
            password = loginDto.password();
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        username = (username != null) ? username.trim() : "";
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = unauthenticated(username, password);
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
