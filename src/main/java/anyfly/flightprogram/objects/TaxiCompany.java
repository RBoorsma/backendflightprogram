package anyfly.flightprogram.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@Entity
public class TaxiCompany {
    @OneToMany
    private List<User> drivers;
    @OneToOne
    private User manager;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
