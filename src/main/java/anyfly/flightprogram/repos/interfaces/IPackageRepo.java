package anyfly.flightprogram.repos.interfaces;

import anyfly.flightprogram.objects.Main.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPackageRepo extends JpaRepository<Packages, Integer> {
    Packages findById(int id);
}
