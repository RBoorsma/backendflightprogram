package anyfly.flightprogram.services.AvationEdge;


import anyfly.flightprogram.objects.DTO.APICalls.Responses.CurrentFlightDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Responses.FlightLocationDTO;
import anyfly.flightprogram.objects.External.FlightTrack.CurrentFlight;
import anyfly.flightprogram.services.AvationEdge.Interface.IFlightTrackerService;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@EnableScheduling
public class FlightTrackerService implements IFlightTrackerService {

    private String icaoCode = "icaoCode";
    private ModelMapper mapper = new ModelMapper();
    private FlightLocationDTO locationResponse = new FlightLocationDTO();


    public List<CurrentFlightDTO> flightByIcaoDEP(String icao) {
        String URL = "%sflights?key=%s&limit=1&depIcao=%s".formatted(MainURL, KEY, icao);
        RestTemplate restTemplate = new RestTemplate();
        ArrayNode dtoNode = restTemplate.getForObject(URL, ArrayNode.class);
        List<CurrentFlight> flightList = new ArrayList<>();
        if(dtoNode != null) {
            for (int i = 0; i < dtoNode.size(); i++) {

                CurrentFlight object = new CurrentFlight();
                object.setFlightIcao(dtoNode.get(i).get("flight").get("icaoNumber").textValue());
                object.setArrivalIcao(dtoNode.get(i).get("arrival").get(icaoCode).textValue());
                object.setStatus(dtoNode.get(i).get("status").textValue());
                object.setDepartureIcao(dtoNode.get(i).get("departure").get(icaoCode).textValue());
                flightList.add(object);

            }
        }

        return mapper.map(flightList, new TypeToken<List<CurrentFlightDTO>>() {
        }.getType());


    }

    @Async
    public CompletableFuture<List<CurrentFlightDTO>> aSyncFlightByIcaoDEP(String icao) throws InterruptedException {
        System.out.println("Search Flight DEMO Started!");
        Thread.sleep(1000L);
        List<CurrentFlightDTO> dtoList = flightByIcaoDEP(icao);
        System.out.println("Completed Flight Search");
        return CompletableFuture.completedFuture(dtoList);


    }

    @Scheduled(fixedRate = 60000, initialDelay = 30000)
    public void UpdateFlightData()
    {
        System.out.print("Updated Flight Data");
        String URL = "%sflights?key=%s&limit=1".formatted(MainURL, KEY);
        RestTemplate template = new RestTemplate();
        ArrayNode locationArray = template.getForObject(URL, ArrayNode.class);
        FlightLocationDTO dto = new FlightLocationDTO();
        if(locationArray != null) {
            dto.setDirection(locationArray.get(0).get("geography").get("direction").intValue());
            dto.setAltitude(locationArray.get(0).get("geography").get("altitude").intValue());
            dto.setLongitude(locationArray.get(0).get("geography").get("longitude").intValue());
            dto.setLatitude(locationArray.get(0).get("geography").get("latitude").intValue());
            dto.setHorizontal(locationArray.get(0).get("speed").get("horizontal").intValue());
            dto.setVspeed(locationArray.get(0).get("speed").get("vspeed").intValue());
            dto.setAircraftIcao(locationArray.get(0).get("aircraft").get("icao24").textValue());
            dto.setStatus(locationArray.get(0).get("status").textValue());


        }
        if(locationArray.size() != 0)
            locationResponse = dto;



    }
    public FlightLocationDTO getFlightLocationDemo()
    {
        return locationResponse;
    }



}
