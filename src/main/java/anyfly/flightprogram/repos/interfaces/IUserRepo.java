package anyfly.flightprogram.repos.interfaces;

import anyfly.flightprogram.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer>{
    User findByEmail(String email);


}
