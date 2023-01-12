package anyfly.flightprogram.objects.External.FlightTrack;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CurrentFlight {
    private String status;
    private String departureIcao;
    private String flightIcao;
    private String arrivalIcao;

}



