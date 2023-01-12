package anyfly.flightprogram.objects.DTO.Account;

import anyfly.flightprogram.objects.DTO.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class JwsDTO implements DTO {
    String jwsString;
}
