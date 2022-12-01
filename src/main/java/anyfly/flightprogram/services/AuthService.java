package anyfly.flightprogram.services;

import anyfly.flightprogram.objects.DTO.Login.RegisterDTO;
import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import anyfly.flightprogram.objects.User;
import anyfly.flightprogram.repos.interfaces.IUserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IUserRepo repo;
    /**
     * Mapper is responsible for mapping between {@code DTOs} and {@code Entities}
     * @see ModelMapper
     */
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private IJWTService service;

    public String hash(String password) {
        return encoder.encode(password);
    }

    public UserDTO authenticate(String email, String password) {
            User user = repo.findByEmail(email);
            if(user!= null) {
                if (encoder.matches(password, user.getPassword())) {
                    UserDTO dto = new UserDTO();
                    mapper.map(user, dto);
                    return dto;
                }
            }
            return null;
    }

    public boolean register(RegisterDTO dto) {
        User user = new User();
        if (Objects.equals(dto.getPassword(), dto.getConfirmpassword())) {
            mapper.map(dto, user);
            user.setPassword(hash(user.getPassword()));
            repo.save(user);
            return true;
        }


        return false;
    }

}