package MaciejBabicki.Programmers.github.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GithubRepository  {

    private String name;
    private String url;

    @Override
    public String toString() {
        return "GithubRepository{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
