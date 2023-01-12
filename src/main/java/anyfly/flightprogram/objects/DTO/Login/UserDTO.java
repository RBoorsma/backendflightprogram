package anyfly.flightprogram.objects.DTO.Login;

import anyfly.flightprogram.objects.DTO.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements DTO {
    int id;
    String email;
    String jwsString;
    String firstName;
    String lastName;
}
