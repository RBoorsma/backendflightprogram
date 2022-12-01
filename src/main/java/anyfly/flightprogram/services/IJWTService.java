package anyfly.flightprogram.services;

import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import anyfly.flightprogram.objects.JwsData;
import org.jetbrains.annotations.NotNull;

public interface IJWTService
{
     UserDTO generateJWS(@NotNull UserDTO user);
     JwsData verifyJWS(String token);

}
