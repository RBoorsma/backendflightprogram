package anyfly.flightprogram;

import anyfly.flightprogram.objects.DTO.Account.PackageDTO;
import anyfly.flightprogram.objects.Main.JwsData;

import anyfly.flightprogram.objects.Main.Packages;
import anyfly.flightprogram.repos.interfaces.IPackageRepo;
import anyfly.flightprogram.repos.interfaces.IUserRepo;
import anyfly.flightprogram.repos.interfaces.ProjectionInterfaces.PackageView;
import anyfly.flightprogram.services.PackageService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
public class PackageServiceTest {
    @Mock
    IPackageRepo packageRepo;
    @Mock
    IUserRepo userRepo;

    @InjectMocks
    PackageService packageService;

    @BeforeEach
    public void setup()
    {
        JwsData data = new JwsData();

        data.setFirstName("Bert");
        data.setLastName("Koningen");
        data.setId("1");
        List<Packages> Packages = new ArrayList<>() {{
            add(new Packages(1, "Package1", "Description1" ));
            add(new Packages(2, "Package2", "Description2" ));
            add(new Packages(3, "Package3", "Description3" ));
        }};
       List<Packages> userpackage = new ArrayList<>() { {
           add(new Packages(1, "Package1", "Description1" ));
           add(new Packages(2, "Package2", "Description2" ));
       }};
        PackageView view = () -> userpackage;

        Mockito.when(packageRepo.findAll()).thenReturn(Packages);
        Mockito.when(userRepo.findAllById(Integer.parseInt(data.getId()))).thenReturn(view);
    }
    @Test
    public void WhenRequestingPackages_AmountShouldbe3()
    {
        List<PackageDTO> packages = packageService.getAllPackages();
        assertThat(packages.size()).isEqualTo(3);
    }

    @Test
    public void WhenRequestingPackagesByJwsData_GetUserPackages()
    {
        JwsData data = new JwsData();
        data.setFirstName("Bert");
        data.setLastName("Koningen");
        data.setId("1");
        List<PackageDTO> packages = packageService.getUserPackages(data);

        assertThat(packages.size()).isEqualTo(2);
        assertThat(packages.get(0).getId()).isEqualTo(1);

    }
}
