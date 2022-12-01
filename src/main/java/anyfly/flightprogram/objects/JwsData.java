package anyfly.flightprogram.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class JwsData {
    private String iss;
    private String email;
    private String id;
    private String firstName;
    private String lastName;
    private Date exp;


}
