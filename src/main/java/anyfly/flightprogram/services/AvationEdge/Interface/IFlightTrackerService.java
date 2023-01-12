package anyfly.flightprogram.services.AvationEdge.Interface;

import anyfly.flightprogram.objects.DTO.APICalls.Responses.CurrentFlightDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Responses.FlightLocationDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IFlightTrackerService extends AviationEdgeData {
    CompletableFuture<List<CurrentFlightDTO>> aSyncFlightByIcaoDEP(String icao) throws InterruptedException;

    List<CurrentFlightDTO> flightByIcaoDEP(String icao);

    FlightLocationDTO getFlightLocationDemo();
    void UpdateFlightData();
}
