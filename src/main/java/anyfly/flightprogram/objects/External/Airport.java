package anyfly.flightprogram.objects.External;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
public class Airport {
    @JsonProperty("codeIcaoAirport")
    private String icao;
    @JsonProperty("nameAirport")
    private String name;
    @JsonProperty("nameCountry")
    private String country;
    private String timezone;

}
