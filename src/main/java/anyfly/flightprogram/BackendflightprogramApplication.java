package anyfly.flightprogram;
import anyfly.flightprogram.Objects.Airport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;


@SpringBootApplication
public class BackendflightprogramApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendflightprogramApplication.class, args);
	}

}
