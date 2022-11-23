package anyfly.flightprogram.repos.interfaces;

import anyfly.flightprogram.objects.TaxiCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepo extends JpaRepository<TaxiCompany, Integer> {
}
