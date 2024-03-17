package MaciejBabicki.Programmers.programmer.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quotation {
    long id;
    private String content;
    private String author;
}
