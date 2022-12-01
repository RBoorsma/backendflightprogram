package anyfly.flightprogram.controllers;

import anyfly.flightprogram.objects.DTO.Login.CredentialDTO;
import anyfly.flightprogram.objects.DTO.Login.RegisterDTO;
import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import anyfly.flightprogram.services.IAuthService;
import anyfly.flightprogram.services.IJWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API/Auth")
public class AuthController {

    @Autowired
    public IAuthService auth;

    @Autowired
    public IJWTService jwt;

    @CrossOrigin //PLACEHOLDER
    @PostMapping("Login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialDTO creds) {
            UserDTO userDTO = auth.authenticate(creds.getEmail(), creds.getPassword());

            if (userDTO != null) {
                userDTO = jwt.generateJWS(userDTO);
                return ResponseEntity.ok(userDTO);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @CrossOrigin //PLACEHOLDER
    @PostMapping("Register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterDTO creds) {
        if(auth.register(creds))
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);

    }
}
