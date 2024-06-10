package pl.programmers.programmer.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@Getter
public class AppConfig {
    @Value("${github.api.url}")
    private String githubApiUrl;
    @Value("${github.login}")
    private String login;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }




}
