package anyfly.flightprogram;

import anyfly.flightprogram.Objects.*;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
public class PasswordHasher {
    private Argon2PasswordEncoder _encoder;
    public PasswordHasher()
    {
        _encoder = new Argon2PasswordEncoder();
    }
    public String Hash(String Password)
    {
        return _encoder.encode(Password);
    }
    public boolean Authenticate(String Email, String Password)
    {
        return _encoder.matches(Password, "put method here"); //TODO Fill Metho
    }
}
