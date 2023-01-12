package anyfly.flightprogram.services.Interface;

import anyfly.flightprogram.objects.DTO.Account.PackageDTO;
import anyfly.flightprogram.objects.Main.JwsData;
import anyfly.flightprogram.objects.Main.User;

import java.util.List;


public interface IPackageService {
    List<PackageDTO> getUserPackages(JwsData userData);
    List<PackageDTO> getAllPackages();
    void updateUserPackage(User data, List<PackageDTO> dtoList);
}
