package anyfly.flightprogram.objects.DTO.APICalls.Responses;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FlightLocationDTO {
    private String aircraftIcao;
    private double altitude;
    private double direction;
    private double latitude;
    private double longitude;
    private double horizontal;
    private double vspeed;
    private String status;
}
