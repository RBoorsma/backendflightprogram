package anyfly.flightprogram.objects.Main;

import anyfly.flightprogram.objects.Main.Packages;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany
    private List<Packages> packages;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}

