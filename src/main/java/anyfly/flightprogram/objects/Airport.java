package anyfly.flightprogram.objects;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
@Getter
@Setter
@NoArgsConstructor

public class Airport {
    private String ICAO;
    private String Name;
    private String Country;
    private String City;
}
