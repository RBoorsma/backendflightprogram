package anyfly.flightprogram.objects.DTO.Login;

import anyfly.flightprogram.objects.DTO.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO implements DTO {
    private String firstName;
    private String lastName;
    private int id;
}
