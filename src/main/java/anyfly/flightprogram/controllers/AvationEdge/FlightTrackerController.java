package anyfly.flightprogram.controllers.AvationEdge;

import anyfly.flightprogram.objects.DTO.APICalls.Responses.FlightLocationDTO;
import anyfly.flightprogram.objects.DTO.Account.JwsDTO;
import anyfly.flightprogram.objects.DTO.Account.PackageDTO;
import anyfly.flightprogram.objects.Main.JwsData;
import anyfly.flightprogram.services.AvationEdge.Interface.IFlightTrackerService;
import anyfly.flightprogram.services.Interface.IAuthService;
import anyfly.flightprogram.services.Interface.IJWTService;
import anyfly.flightprogram.services.Interface.IPackageService;
import io.jsonwebtoken.JwtException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/Aviation/FlightTracker")
public class FlightTrackerController {


    IFlightTrackerService flightTrackerService;
    IPackageService packageService;
    IJWTService jwtService;

    private final String FlightPackageName = "FLIGHTTRACKER";

    @Autowired
    public FlightTrackerController(IFlightTrackerService flightTrackerService, IPackageService packageService, IJWTService ijwtService) {
        this.flightTrackerService = flightTrackerService;
        this.packageService = packageService;
        this.jwtService = ijwtService;
    }

    @PostMapping("Demo")
    @CrossOrigin
    public ResponseEntity<FlightLocationDTO> RequestDemo(@RequestBody JwsDTO dto)
    {
        JwsData data = new JwsData();
        try{
            data = jwtService.verifyJWS(dto);
        }
        catch(JwtException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        for(PackageDTO packageDTO : packageService.getUserPackages(data))
        {
            if(packageDTO.getName().equals(FlightPackageName)) {
                return ResponseEntity.ok(flightTrackerService.getFlightLocationDemo());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);


    }


}
