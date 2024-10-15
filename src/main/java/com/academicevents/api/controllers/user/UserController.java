package com.academicevents.api.controllers.user;

import com.academicevents.api.enums.ROLES;
import com.academicevents.api.handlers.CreateUser;
import com.academicevents.api.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @PostMapping("/create/user")
    public String SignUp(@RequestBody User user) {
        CreateUser.saveUser(user);

        return "fim";
    }
}
