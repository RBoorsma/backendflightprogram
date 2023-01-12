package anyfly.flightprogram.repos.interfaces;

import anyfly.flightprogram.objects.Main.User;
import anyfly.flightprogram.repos.interfaces.ProjectionInterfaces.PackageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer>{
    User findByEmail(String email);
    User findById(int id);

    PackageView findAllById(int id);

}
