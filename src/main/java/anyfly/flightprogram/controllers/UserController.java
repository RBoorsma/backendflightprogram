package anyfly.flightprogram.controllers;
import anyfly.flightprogram.objects.User;
import anyfly.flightprogram.services.IUserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Notification;

@RestController
@RequestMapping("/Login")
public class UserController {

    @Autowired
    public IUserService service;
    @GetMapping("/{id}")
    private User Login(@PathVariable int id)
    {
        throw new UnsupportedOperationException("Not Implemented yet");
    }
}
