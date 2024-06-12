package pl.programmers.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.programmers.user.UserMapperImpl;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Config {
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserMapperImpl userMapperImpl() {
        return new UserMapperImpl();
    }

    @Bean
    RestAuthenticationSuccesHandler restAuthenticationSuccesHandler() {
        return new RestAuthenticationSuccesHandler();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .and()
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("Access-Control-Allow-Headers",
                "Acces_Control-Allow-Origin",
                "Access-Control-Request-Method",
                "Access-Controll-Request_Headers",
                "Origin",
                "Cache-Control",
                "Content-Type",
                "Authorization"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        httpSecurity.csrf().disable()
                .cors();
        httpSecurity.authorizeRequests()
                .requestMatchers("/user/register").permitAll()
                .requestMatchers("/user/*").authenticated()
                .requestMatchers("/user").authenticated()
                .requestMatchers("/programmer/user").authenticated()
                .anyRequest().permitAll();
        return httpSecurity.build();
    }

    private JsonObjectAuthenticationFilter authenticationFilter(AuthenticationManager authenticationManager) {
        JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
        authenticationFilter.setSecurityContextRepository(new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        ));
        authenticationFilter.setAuthenticationSuccessHandler(restAuthenticationSuccesHandler());
        authenticationFilter.setAuthenticationManager(authenticationManager);
        return authenticationFilter;
    }
}
