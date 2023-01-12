package anyfly.flightprogram.services.Interface;

import anyfly.flightprogram.objects.DTO.Login.RegisterDTO;
import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface IAuthService {
    PasswordEncoder encoder = new Argon2PasswordEncoder();
    String hash(String password);
    UserDTO authenticate(String email, String password);
    boolean register(RegisterDTO dto);

}
