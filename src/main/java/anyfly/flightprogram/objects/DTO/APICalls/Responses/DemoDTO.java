package anyfly.flightprogram.objects.DTO.APICalls.Responses;

import anyfly.flightprogram.objects.DTO.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DemoDTO implements DTO {
    private CurrentFlightDTO flight;
    private CountryDTO country;
    private AirportDTO airport;
}
