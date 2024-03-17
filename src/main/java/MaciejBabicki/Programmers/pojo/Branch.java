package MaciejBabicki.Programmers.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {

    @JsonProperty("name")
    private String name;

    @JsonProperty("commit")
    private MaciejBabicki.Programmers.pojo.Commit Commit;

}
