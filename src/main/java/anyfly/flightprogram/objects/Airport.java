package anyfly.flightprogram.objects;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
public class Airport {
    private String icao;
    private String name;
    private String country;
    private String city;
}
