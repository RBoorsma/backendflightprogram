package anyfly.flightprogram.objects.Main;

import anyfly.flightprogram.objects.Main.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
@Entity
public class TaxiCompany {
    @OneToOne
    private User manager;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


}
