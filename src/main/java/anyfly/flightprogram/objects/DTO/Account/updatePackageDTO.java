package anyfly.flightprogram.objects.DTO.Account;

import anyfly.flightprogram.objects.DTO.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class updatePackageDTO extends JwsDTO implements DTO {
    private List<PackageDTO> packageList;

}
