package anyfly.flightprogram.controllers.AvationEdge;

import anyfly.flightprogram.objects.DTO.APICalls.Responses.AirportDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Responses.CountryDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Responses.CurrentFlightDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Responses.DemoDTO;
import anyfly.flightprogram.services.AvationEdge.Interface.IAvationEdgeDBService;
import anyfly.flightprogram.services.AvationEdge.Interface.IFlightTrackerService;
import anyfly.flightprogram.services.Interface.IJWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@EnableAsync
@RequestMapping("/API/Aviation/Demo")
public class DemoController {


    final IAvationEdgeDBService avationEdgeDBService;
    final IFlightTrackerService flightTrackerService;
    final IJWTService ijwtService;

    @Autowired
    public DemoController(IAvationEdgeDBService avationEdgeDBService, IFlightTrackerService flightTrackerService, IJWTService ijwtService) {
        this.avationEdgeDBService = avationEdgeDBService;
        this.flightTrackerService = flightTrackerService;
        this.ijwtService = ijwtService;
    }

    @PostMapping("/Run")
    @CrossOrigin
    public ResponseEntity<DemoDTO> RunDemo() {
        CompletableFuture<CountryDTO> dutchCountry;
        CompletableFuture<List<AirportDTO>> airport;
        CompletableFuture<List<CurrentFlightDTO>> currentFlights;
        try {
            dutchCountry = avationEdgeDBService.aSyncSearchCountryByName("Netherlands");
            airport = avationEdgeDBService.aSyncSearchAirportsByCountryCode("NL");
            currentFlights = flightTrackerService.aSyncFlightByIcaoDEP("EHAM");
            CompletableFuture.allOf(dutchCountry, airport, currentFlights).join();
            DemoDTO dto = new DemoDTO();
            dto.setCountry(dutchCountry.get());
            dto.setFlight(currentFlights.get().get(0));
            dto.setAirport(airport.get().get(0));
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }


    }


}
