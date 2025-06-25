package pl.programmers.programmer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Jeśli używasz Postmana, CSRF można wyłączyć
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/programmers").permitAll()
                        .requestMatchers(HttpMethod.POST, "/programmers").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/programmers/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/programmers/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/repos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/repos").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(); // lub .formLogin()

        return http.build();
    }
}
