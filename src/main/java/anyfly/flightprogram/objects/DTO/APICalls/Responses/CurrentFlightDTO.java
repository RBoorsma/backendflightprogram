package anyfly.flightprogram.objects.DTO.APICalls.Responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrentFlightDTO {
    private String status;
    private String departureIcao;
    private String flightIcao;
    private String arrivalIcao;
}
