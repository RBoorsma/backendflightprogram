package anyfly.flightprogram.objects.DTO.APICalls.Incoming;

import anyfly.flightprogram.objects.DTO.Account.JwsDTO;
import anyfly.flightprogram.objects.DTO.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirportByCountryCodeDTO extends JwsDTO implements DTO {
    String countryCode;
}
