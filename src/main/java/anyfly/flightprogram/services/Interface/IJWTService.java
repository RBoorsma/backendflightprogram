package anyfly.flightprogram.services.Interface;

import anyfly.flightprogram.objects.DTO.Account.JwsDTO;
import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import anyfly.flightprogram.objects.Main.JwsData;
import org.jetbrains.annotations.NotNull;

public interface IJWTService
{
     UserDTO generateJWS(@NotNull UserDTO user);
     JwsData verifyJWS(JwsDTO token);
     JwsData verifyJWS(String token);

}
