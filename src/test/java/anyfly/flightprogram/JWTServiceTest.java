package anyfly.flightprogram;

import anyfly.flightprogram.objects.DTO.Account.JwsDTO;
import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import anyfly.flightprogram.objects.Main.JwsData;
import anyfly.flightprogram.services.JWTService;
import io.jsonwebtoken.JwtException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN) // Instead of throwing an error for "Unused code" Give me a warning!
public class JWTServiceTest {


    @InjectMocks
    JWTService jwtService;


    @Test
    public void WhenGivingClaims_JWSIsAdded() {
        //ARRANGE
        UserDTO dto = new UserDTO();
        dto.setId(1);
        dto.setFirstName("Bert");
        dto.setEmail("bert@gmail.com");
        dto.setLastName("Koningen");
        //ACT
        UserDTO newdto = jwtService.generateJWS(dto);
        //ASSERT
        assertThat(newdto.getJwsString()).isNotNull();

    }

    @Test
    public void WhenInvalidJWS_ThrowsException() {

        JwsDTO dto = new JwsDTO();
        dto.setJwsString("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBbnlGbGlnaHQiLCJzdWIiOiI3IiwiZXhwIjoxNjczNDQ3ODE4LCJmaXJzdE5hbWUiOiJSb2IiLCJsYXN0TmF" +
                "tZSI6IkJvb3JzbWEifQ.0UU5nFm2-XePjIHFTYSYjq9q3UNYZOlye4whZZxlRpo");

        try {
            JwsData data = jwtService.verifyJWS(dto);
        } catch (JwtException e) {
            assertThat(e).isInstanceOf(JwtException.class);
        }

    }

    @Test
    public void WhenValidJWS_GiveJwsData() {
        //ARRANGE
        String firstname = "Bert";
        String lastname = "Koningen";
        int id = 1;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setFirstName(firstname);
        userDTO.setLastName(lastname);
        userDTO = jwtService.generateJWS(userDTO);
        //ACT
        JwsData data = jwtService.verifyJWS(userDTO.getJwsString());
        //ASSERT
        assertThat(data.getId()).isEqualTo(String.valueOf(id));
        assertThat(data.getFirstName()).isEqualTo(firstname);
        assertThat(data.getLastName()).isEqualTo(lastname);
    }
}
