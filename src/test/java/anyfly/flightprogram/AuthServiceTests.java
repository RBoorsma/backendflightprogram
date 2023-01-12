package anyfly.flightprogram;


import anyfly.flightprogram.objects.DTO.Login.RegisterDTO;
import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import anyfly.flightprogram.objects.Main.User;
import anyfly.flightprogram.repos.interfaces.IUserRepo;
import anyfly.flightprogram.services.AuthService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN) // Instead of throwing an error for "Unused code" Give me a warning!
public class AuthServiceTests {

    @Mock
    IUserRepo userRepo;

    @InjectMocks
    private AuthService authService;


    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setEmail("unittest@anyflight.nl");
        user.setId(1);
        user.setFirstName("Unit");
        user.setLastName("Test");
        user.setPassword(authService.hash("Password"));
        Mockito.when(userRepo.findByEmail(any(String.class))).thenReturn(null);
        Mockito.when(userRepo.findByEmail(user.getEmail())).thenReturn(user);




    }

    @Test
    public void whenCorrectCredentials_Authenticate() {
        String email = "unittest@anyflight.nl";
        String password = "Password";
        UserDTO found = authService.authenticate(email, password);
        assertThat(found.getEmail()).isEqualTo(email);
    }

    @Test
    public void RegisterWhenEmailExists_ReturnFalse() {
        //ARRANGE
        RegisterDTO dto = new RegisterDTO();
        dto.setEmail("unittest@anyflight.nl");
        dto.setPassword("Password");
        dto.setConfirmpassword("Password");
        dto.setFirstName("Unit");
        dto.setLastName("Test");
        //ACT
        boolean answer = authService.register(dto);
        //ASSERT
        assertThat(answer).isFalse();
    }

    @Test
    public void RegisterWhenPasswordsAreNotEqual_ReturnFalse() {
        //ARRANGE
        RegisterDTO dto = new RegisterDTO();
        dto.setEmail("newmail@yolo.nl");
        dto.setPassword("Passworddd");
        dto.setConfirmpassword("Password");
        dto.setFirstName("Unit");
        dto.setLastName("Test");

        //ACT
        boolean answer = authService.register(dto);
        //ASSERT
        assertThat(answer).isFalse();
    }

    @Test
    public void WhenSuccesfullRegister_ReturnTrue() {
        //ARRANGE
        RegisterDTO dto = new RegisterDTO();
        dto.setEmail("newmail@yolo.nl");
        dto.setPassword("Password");
        dto.setConfirmpassword("Password");
        dto.setFirstName("Unit");
        dto.setLastName("Test");

        //ACT
        boolean answer = authService.register(dto);
        //ASSERT
        assertThat(answer).isTrue();
    }

    @Test
    public void Argon2HashesAreNeverEqual()
    {
        String password = "Password";
        String password1 = authService.hash(password);
        String password2 = authService.hash(password);
        assertThat(password1).isNotEqualTo(password2);
    }
}
