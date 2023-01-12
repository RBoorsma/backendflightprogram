package anyfly.flightprogram.objects.DTO.Account;

import anyfly.flightprogram.objects.DTO.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
public class PackageDTO implements DTO {
    String name;
    String description;
    int id;
}
