package anyfly.flightprogram.objects.DTO.APICalls.Responses;

import anyfly.flightprogram.objects.DTO.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountryDTO implements DTO {
    private String capital;
    private String currency;
    private String iso2Code;
    private String iso3Code;
    private String name;
    private String phonePrefix;
}
