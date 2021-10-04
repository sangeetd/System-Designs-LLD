package com.sangeet.logisticsystem.project.repo;

import com.sangeet.logisticsystem.project.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepo {

    private List<User> userList;

    public UserRepo() {
        this.userList = new ArrayList<>();
    }

    public void save(User user){
        this.userList.add(user);
    }

}
