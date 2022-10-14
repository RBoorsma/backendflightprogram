package anyfly.flightprogram.Objects;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Airplane {
    private int Seats;
    private String FromICAO;
    private String ToICAO;
    private String FromCity;
    private String ToCity;
    private String Manufacture;
    private boolean IsDelayed;
    private LocalDateTime ETA;
    private LocalDateTime ETD;
}
