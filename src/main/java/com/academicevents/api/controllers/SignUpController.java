package com.academicevents.api.controllers;

import com.academicevents.api.handlers.CreateUser;
import com.academicevents.api.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class SignUpController{

    @GetMapping("/")
    public String SignUp() throws SQLException {
        User user = new User("DadoFrontEnd");
        ResultSet rs = CreateUser.saveUser(user);
        return "fim";
    }
}
