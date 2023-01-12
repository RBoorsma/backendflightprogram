package anyfly.flightprogram;


import anyfly.flightprogram.controllers.AuthController;
import anyfly.flightprogram.objects.DTO.Login.CredentialDTO;
import anyfly.flightprogram.objects.DTO.Login.RegisterDTO;
import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import anyfly.flightprogram.services.AuthService;
import anyfly.flightprogram.services.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AuthService authService;

    @MockBean
    JWTService jwtService;

    @BeforeEach
    public void Setup()
    {
        UserDTO dto = new UserDTO();
        UserDTO dto2;
        dto.setEmail("Mockedmail@mock.nl");
        dto.setFirstName("Mock");
        dto.setLastName("Ito");
        Mockito.when(authService.authenticate(eq(dto.getEmail()), any(String.class))).thenReturn(dto);
        dto2 = dto;
        dto.setJwsString("A Mocked JWS");
        Mockito.when(jwtService.generateJWS(any(UserDTO.class))).thenReturn(dto2);



    }


    @Test
    public void WhenValidLoginInput_Return200() throws Exception {
        CredentialDTO dto = new CredentialDTO();
        dto.setEmail("Mockedmail@mock.nl");
        dto.setPassword("Password");

        mockMvc.perform(post("/API/Auth/Login").contentType("application/json").content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk());
    }

    @Test
    public void WhenInvalidLoginInput_Return401() throws Exception {
        CredentialDTO dto = new CredentialDTO();
        dto.setEmail("NonExistant@mymail.nl");
        dto.setPassword("Password");

        mockMvc.perform(post("/API/Auth/Login").contentType("application/json").content(objectMapper.writeValueAsString(dto))).andExpect(status().isUnauthorized());
    }

    @Test
    public void WhenRegistering_Return201() throws Exception {
        RegisterDTO dto = new RegisterDTO();
        Mockito.when(authService.register(any(RegisterDTO.class))).thenReturn(true);
        dto.setEmail("Mockedmail@mock.nl");


        mockMvc.perform(post("/API/Auth/Register").contentType("application/json").content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated());
    }
    @Test
    public void WhenInvalidRegistering_Return200() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();
        Mockito.when(authService.register(any(RegisterDTO.class))).thenReturn(false);


        mockMvc.perform(post("/API/Auth/Register").contentType("application/json").content(objectMapper.writeValueAsString(registerDTO))).andExpect(status().isOk());
    }
}
