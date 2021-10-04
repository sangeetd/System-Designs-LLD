package com.sangeet.logisticsystem.project.controller;

import com.sangeet.logisticsystem.project.model.User;
import com.sangeet.logisticsystem.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public User createUser(String email, String mobileNumber, String username){
        return this.userService.createUser(email, mobileNumber, username);
    }

}
