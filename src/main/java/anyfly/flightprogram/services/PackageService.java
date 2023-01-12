package anyfly.flightprogram.services;


import anyfly.flightprogram.objects.Exceptions.UnexpectedException;
import anyfly.flightprogram.objects.DTO.Account.PackageDTO;
import anyfly.flightprogram.objects.Main.JwsData;
import anyfly.flightprogram.objects.Main.Packages;
import anyfly.flightprogram.objects.Main.User;
import anyfly.flightprogram.repos.interfaces.IPackageRepo;
import anyfly.flightprogram.repos.interfaces.IUserRepo;
import anyfly.flightprogram.repos.interfaces.ProjectionInterfaces.PackageView;
import anyfly.flightprogram.services.Interface.IPackageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PackageService implements IPackageService {

    IPackageRepo packageRepo;
    IUserRepo userRepo;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public PackageService(IPackageRepo packageRepo, IUserRepo userRepo) {
        this.packageRepo = packageRepo;
        this.userRepo = userRepo;
    }

    public List<PackageDTO> getUserPackages(JwsData userData) throws UnexpectedException {
        int id = Integer.parseInt(userData.getId());
        PackageView packages = userRepo.findAllById(id);

        List<Packages> packagesList = packages.getPackages();
        return mapper.map(packagesList, new TypeToken<List<PackageDTO>>() {
        }.getType());
    }

    public List<PackageDTO> getAllPackages() throws UnexpectedException {
        List<Packages> packageList = packageRepo.findAll();
        return mapper.map(packageList, new TypeToken<List<PackageDTO>>() {
        }.getType());
    }

    public void updateUserPackage(User data, List<PackageDTO> dtoList) throws UnexpectedException {
        User user = new User();
        mapper.map(data, user);
        List<Packages> packageList = mapper.map(dtoList, new TypeToken<List<Packages>>() {
        }.getType());
        user.setPackages(packageList);
        userRepo.save(user);
    }


}
