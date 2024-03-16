package MaciejBabicki.Programmers.programmer.dto;

import MaciejBabicki.Programmers.github.pojo.Owner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDto {
    private String login;

    public static OwnerDto mapFromOwner(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setLogin(ownerDto.getLogin());
        return ownerDto;
    }
}
