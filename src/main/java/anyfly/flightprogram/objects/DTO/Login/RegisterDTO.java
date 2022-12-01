package anyfly.flightprogram.objects.DTO.Login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDTO {
    String email;
    String password;
    String confirmpassword;
    String firstName;

    String lastName;
}
