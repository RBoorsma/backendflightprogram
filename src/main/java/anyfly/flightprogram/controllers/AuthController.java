package anyfly.flightprogram.controllers;

import anyfly.flightprogram.objects.DTO.Login.CredentialDTO;
import anyfly.flightprogram.objects.DTO.Login.RegisterDTO;
import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import anyfly.flightprogram.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API/Auth")
public class AuthController {

    @Autowired
    public IAuthService auth;

    @PostMapping("Login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialDTO creds) {
            UserDTO dto = auth.authenticate(creds.getEmail(), creds.getPassword());

            if (dto != null)
                return ResponseEntity.ok(dto);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("Register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterDTO creds) {
        if(auth.register(creds))
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);


    }
}
