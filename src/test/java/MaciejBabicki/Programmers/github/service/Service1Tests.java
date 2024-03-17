package MaciejBabicki.Programmers.github.service;

import MaciejBabicki.Programmers.entity.GithubRepository;
import MaciejBabicki.Programmers.service.Service1;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Service1Tests {

    @Test
    public void testGetGithubRepositories() {
        //given
        String githubApiUrl = "https://api.github.com";
        String login = "MaciejBabicki";
        String url = githubApiUrl + "/users/" + login + "/repos";
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        Service1 service1 = new Service1(restTemplateMock);
        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(url, HttpStatus.OK);
        //when
        when(restTemplateMock.getForEntity(url, String.class, login))
                .thenReturn(mockResponseEntity);
        List<GithubRepository> result = service1.getGithubRepositories();
        //then
        assertEquals(url, result);
    }
}
