package anyfly.flightprogram.objects.DTO.APICalls.Responses;

import anyfly.flightprogram.objects.DTO.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirportDTO implements DTO {

    private String icao;

    private String name;

    private String country;
    private String timezone;
}