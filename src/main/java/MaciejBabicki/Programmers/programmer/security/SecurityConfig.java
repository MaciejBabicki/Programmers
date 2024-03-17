package MaciejBabicki.Programmers.programmer.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig implements WebSecurityConfigurer {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails moderator = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("MODERATOR")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(moderator);
    }
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/api").permitAll()
                .requestMatchers(HttpMethod.POST, "api").hasRole("MODERATOR")
                .requestMatchers(HttpMethod.DELETE, "api").hasRole("ADMIN")
                .and();
    }
    @Override
    public void init(SecurityBuilder builder) throws Exception {

    }
    @Override
    public void configure(SecurityBuilder builder) throws Exception {

    }
}
