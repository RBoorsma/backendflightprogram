package anyfly.flightprogram.components;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHasher {
    private final PasswordEncoder encoder;
    public PasswordHasher()
    {
        encoder = new Argon2PasswordEncoder();
    }
    public String hash(String password)
    {
        return encoder.encode(password);
    }
    public boolean authenticate(String email, String password)
    {
        return encoder.matches(password, "put method here"); //TODO Fill Method
    }
}
