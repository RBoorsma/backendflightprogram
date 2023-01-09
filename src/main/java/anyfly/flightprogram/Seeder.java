package anyfly.flightprogram;

import anyfly.flightprogram.objects.Main.Packages;
import anyfly.flightprogram.objects.Main.User;
import anyfly.flightprogram.repos.interfaces.IPackageRepo;
import anyfly.flightprogram.repos.interfaces.IUserRepo;
import anyfly.flightprogram.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Seeder {
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedPackageTable();
        seedTestUser();
    }

    @Autowired
    IPackageRepo packageRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthService authService;

    private void seedPackageTable() {
        Packages flighttracker = new Packages();
        Packages database = new Packages();
        Packages schedules = new Packages();
        flighttracker.setDescription("With our special Flight Tracker Package you get access to the following:\n" +
                "- Track Real Time Aviation\n" +
                "- Track Airline Routes");
        flighttracker.setName("FLIGHTTRACKER");
        flighttracker.setId(1);
        database.setDescription("With our database package you gain access to the following:\n" +
                "- Look up any airline data\n" +
                "- Look up Airport data\n" +
                "- Look up City & Country Data");
        database.setName("DATABASE");
        database.setId(2);
        schedules.setDescription("With our schedules package you gain access to the following:\n" +
                "- Look up current schedules of flights. \n" +
                "- Be able to see if there are delays expected\n" +
                "- See future/historical schedules.");
        schedules.setName("SCHEDULES");
        schedules.setId(3);
        packageRepo.save(flighttracker);
        packageRepo.save(schedules);
        packageRepo.save(database);
    }

    private void seedTestUser() {
        String mail = "supersecret@mymail.nl";
        User user = userRepo.findByEmail(mail);

        if(user == null)
        {
            user = new User();
        }
        user.setPackages(packageRepo.findAll());
        user.setEmail(mail);
        user.setPassword(authService.hash("Password"));
        user.setFirstName("Cypress");
        user.setLastName("EndToEnd");
        userRepo.save(user);


    }
}
