package com.sangeet.logisticsystem.project.service;

import com.sangeet.logisticsystem.project.model.User;
import com.sangeet.logisticsystem.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(String email, String mobileNumber, String username){
        User newUser = new User(email, mobileNumber, username);
        this.userRepo.save(newUser);
        return newUser;
    }


}
