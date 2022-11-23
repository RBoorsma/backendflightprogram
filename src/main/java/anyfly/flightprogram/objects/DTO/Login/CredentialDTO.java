package anyfly.flightprogram.objects.DTO.Login;

import anyfly.flightprogram.objects.DTO.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CredentialDTO implements DTO {
    String email;
    String password;
}
