package pl.programmers.service.importservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import pl.programmers.entity.GithubRepository;
import pl.programmers.entity.Programmer;
import pl.programmers.repository.ProgrammerRepo;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ProgrammerImportServiceTests {
    @Mock
    WebClient.Builder webClientBuilder;
    @InjectMocks
    private ProgrammerImportService programmerImportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProgrammer() {
        //
        GithubRepository repo1 = new GithubRepository();
        GithubRepository repo2 = new GithubRepository();
        List<GithubRepository> mockResponse = Arrays.asList(repo1, repo2);
        //WebClient mocking
        WebClient webClient = WebClient.builder().build();
        when(webClientBuilder.build()).thenReturn(webClient);
        WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock = Mockito.mock(WebClient.RequestBodyUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);
        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri("test_url")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(GithubRepository.class)).thenReturn(Flux.fromIterable(mockResponse));
        //calling tested method
        Programmer programmer = programmerImportService.getProgrammers();
        Assertions.assertNull(programmer.getRepoName());
    }
}
