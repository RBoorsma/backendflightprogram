package anyfly.flightprogram.objects.External;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Country {
    private String capital;
    @JsonProperty("codeCurrency")
    private String currency;
    @JsonProperty("codeIso2Country")
    private String iso2Code;
    @JsonProperty("codeIso3Country")
    private String iso3Code;
    @JsonProperty("nameCountry")
    private String name;
    private String phonePrefix;


}
