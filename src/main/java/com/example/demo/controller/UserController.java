package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.payload.UserSummary;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getAllUsers")
    public ArrayList<UserSummary> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        ArrayList<UserSummary> allUsersResponse = new ArrayList<>();
        for (User user : allUsers) {
            UserSummary userSummary = new UserSummary(user.getId(), user.getUsername());
            allUsersResponse.add(userSummary);
        }
        return allUsersResponse;
    }

    // for test only
    @GetMapping("/addUser")
    public void addUser(String username) {
        User user = new User(username);
        userRepository.save(user);
    }

    // for test only
    @GetMapping("/echo")
    public String echo(String text) {
        return text;
    }
}
