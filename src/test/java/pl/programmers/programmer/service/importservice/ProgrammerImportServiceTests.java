package pl.programmers.programmer.service.importservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import pl.programmers.programmer.entity.GithubRepository;
import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.repository.ProgrammerRepo;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProgrammerImportServiceTests {
    @Mock
    ProgrammerRepo programmerRepo;
    @Mock
    WebClient.Builder webClientBuilder;
    @InjectMocks
    private ProgrammerImportService programmerImportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProgrammer() {
        //given
        List<GithubRepository> mockGithubResponse = new ArrayList<>();
        mockGithubResponse.add(new GithubRepository());
        when(programmerImportService.fetchGithubRepositories(any())).thenReturn(mockGithubResponse);
        //when
        Programmer programmer = programmerImportService.getProgrammers("https://api.github.com/MaciejBabicki");
    }
}
