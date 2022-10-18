package anyfly.flightprogram.objects;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Airplane {
    private int seats;
    private String fromIcao;
    private String toIcao;
    private String fromCity;
    private String toCity;
    private String manufacture;
    private boolean isDelayed;
    private LocalDateTime eta;
    private LocalDateTime etd;

}
