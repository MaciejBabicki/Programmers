package pl.programmers.service.importservice;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
public class Config {
    @Value("${github.api.url}")
    private String githubApiUrl;
    @Value("${github.login}")
    private String login;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
