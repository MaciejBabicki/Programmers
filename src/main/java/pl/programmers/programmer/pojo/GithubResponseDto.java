package pl.programmers.programmer.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GithubResponseDto {
    private List<GithubRepositoryDto> items;
}
