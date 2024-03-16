package MaciejBabicki.Programmers.github.client;

import MaciejBabicki.Programmers.github.pojo.GithubRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Client1Tests {

    @Test
    public void testGetGithubRepositories() {
        //given
        String githubApiUrl = "https://api.github.com";
        String login = "MaciejBabicki";
        String url = githubApiUrl + "/users/" + login + "/repos";
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        Client1 client1 = new Client1(restTemplateMock);
        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(url, HttpStatus.OK);
        //when
        when(restTemplateMock.getForEntity(url, String.class, login))
                .thenReturn(mockResponseEntity);
        List<GithubRepository> result = client1.getGithubRepositories();
        //then
        assertEquals(url, result);
    }
}
