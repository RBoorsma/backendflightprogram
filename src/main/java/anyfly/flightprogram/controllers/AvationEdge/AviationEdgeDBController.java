package anyfly.flightprogram.controllers.AvationEdge;

import anyfly.flightprogram.objects.DTO.APICalls.Incoming.AirportByCountryCodeDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Incoming.CountryByNameDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Responses.AirportDTO;
import anyfly.flightprogram.objects.DTO.APICalls.Responses.CountryDTO;
import anyfly.flightprogram.services.AvationEdge.Interface.IAvationEdgeDBService;
import anyfly.flightprogram.services.Interface.IJWTService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/API/Aviation/DB")
public class AviationEdgeDBController {

    IAvationEdgeDBService dbService;
    IJWTService jwtService;

    @Autowired
    public AviationEdgeDBController(IAvationEdgeDBService dbService, IJWTService jwtService) {
        this.dbService = dbService;
        this.jwtService = jwtService;
    }

    @PostMapping("Airport")
    public ResponseEntity<List<AirportDTO>> GetAirportData(@RequestBody AirportByCountryCodeDTO dto) {
        try {
            jwtService.verifyJWS(dto.getJwsString());
        } catch (JwtException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        List<AirportDTO> response = dbService.searchAirportsByCountryCode(dto.getCountryCode());
        return ResponseEntity.ok(response);
    }

    @PostMapping("Country")
    public ResponseEntity<CountryDTO> GetAirportData(@RequestBody CountryByNameDTO dto) {
        try {
            jwtService.verifyJWS(dto.getJwsString());
        } catch (JwtException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.ok(dbService.searchCountryByName(dto.getCountryName()));

    }
}
