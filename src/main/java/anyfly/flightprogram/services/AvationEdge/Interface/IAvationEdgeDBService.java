package anyfly.flightprogram.services.AvationEdge.Interface;

import anyfly.flightprogram.objects.DTO.APICalls.Responses.AirportDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Responses.CountryDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IAvationEdgeDBService extends AviationEdgeData{
    List<AirportDTO> searchAirportsByCountryCode(String countryCode);
    CountryDTO searchCountryByName(String countryName);

    CompletableFuture<CountryDTO> aSyncSearchCountryByName(String countryName) throws InterruptedException;
    CompletableFuture<List<AirportDTO>> aSyncSearchAirportsByCountryCode(String countryCode) throws InterruptedException;
}
