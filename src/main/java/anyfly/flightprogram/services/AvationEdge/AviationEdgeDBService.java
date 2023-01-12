package anyfly.flightprogram.services.AvationEdge;

import anyfly.flightprogram.objects.DTO.APICalls.Responses.AirportDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Responses.CountryDTO;
import anyfly.flightprogram.objects.External.Airport;
import anyfly.flightprogram.objects.External.Country;
import anyfly.flightprogram.services.AvationEdge.Interface.IAvationEdgeDBService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AviationEdgeDBService implements IAvationEdgeDBService {

    ModelMapper mapper = new ModelMapper();

    public List<AirportDTO> searchAirportsByCountryCode(String countrycode) {
        String URL = "%sairportDatabase?key=%s&codeIso2Country=%s".formatted(MainURL, KEY, countrycode);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Airport[]> response = restTemplate.getForEntity(URL, Airport[].class);
        List<Airport> airports = List.of(response.getBody());


        return mapper.map(airports, new TypeToken<List<AirportDTO>>() {
        }.getType());


    }

    @Async
    public CompletableFuture<List<AirportDTO>> aSyncSearchAirportsByCountryCode(String countrycode) throws InterruptedException {
        System.out.println("Search Airport By Country Demo Started!");
        Thread.sleep(2000L);
        List<AirportDTO> dtoList = searchAirportsByCountryCode(countrycode);
        System.out.println("Completed Airport Search");
        return CompletableFuture.completedFuture(dtoList);


    }


    public CountryDTO searchCountryByName(String countryName) {
        String URL = "%scountryDatabase?key=%s&nameCountry=%s".formatted(MainURL, KEY, countryName);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Country[]> response =
                restTemplate.getForEntity(
                        URL,
                        Country[].class);
        CountryDTO dto = new CountryDTO();
        Country[] resultArray = response.getBody();
        Country result = resultArray != null ? resultArray[0] : null;
        mapper.map(result, dto);
        return dto;

    }

    @Async
    public CompletableFuture<CountryDTO> aSyncSearchCountryByName(String countryName) throws InterruptedException {
        System.out.println("Search Country By Name Demo Started!");
        Thread.sleep(5000L);
        CountryDTO dto = searchCountryByName(countryName);
        System.out.println("Completed Country Search");
        return CompletableFuture.completedFuture(dto);
    }
}
