package anyfly.flightprogram.controllers;

import anyfly.flightprogram.objects.DTO.Account.PackageDTO;
import anyfly.flightprogram.objects.DTO.Account.JwsDTO;
import anyfly.flightprogram.objects.DTO.Account.updatePackageDTO;
import anyfly.flightprogram.objects.Main.JwsData;
import anyfly.flightprogram.objects.Main.User;
import anyfly.flightprogram.repos.interfaces.IUserRepo;
import anyfly.flightprogram.services.Interface.IJWTService;
import anyfly.flightprogram.services.Interface.IPackageService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/Account")
public class AccountController {

    IJWTService jwt;
    IPackageService packageService;
    IUserRepo userRepo;

    @Autowired
    public AccountController(IJWTService jwt, IPackageService packageService, IUserRepo userRepo) {
        this.jwt = jwt;
        this.packageService = packageService;
        this.userRepo = userRepo;
    }


    @PostMapping("UserPackages")
    @CrossOrigin
    public ResponseEntity<List<PackageDTO>> getPackages(@RequestBody JwsDTO jws) {
        JwsData user;
        try {
            user = jwt.verifyJWS(jws);
        } catch (JwtException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
       List<PackageDTO> dto = packageService.getUserPackages(user);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("AllPackages")
    @CrossOrigin
    public ResponseEntity<List<PackageDTO>> getAllPackages(@RequestBody JwsDTO jws)
    {
        try {
            jwt.verifyJWS(jws);
        } catch (JwtException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        List<PackageDTO> dto = packageService.getAllPackages();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("updatePackages")
    @CrossOrigin
    public ResponseEntity<Void> updatePackage(@RequestBody updatePackageDTO dto)
    {
        JwsData jwtCheck;
        try {
            jwtCheck = jwt.verifyJWS(dto.getJwsString());
        } catch (JwtException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        User user = userRepo.findById(Integer.parseInt(jwtCheck.getId())) ;
        packageService.updateUserPackage(user, dto.getPackageList());

        return  ResponseEntity.ok(null);
    }
}

