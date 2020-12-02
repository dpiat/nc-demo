package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.payload.OrderSummary;
import com.example.demo.payload.UserRequest;
import com.example.demo.payload.UserSummary;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public ArrayList<UserSummary> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        ArrayList<UserSummary> allUsersResponse = new ArrayList<>();
        for (User user : allUsers) {
           /* List<OrderSummary> orderSummaries = new ArrayList<>();
            List<Order> orders = user.getOrders();
            for (Order order : orders) {
                OrderSummary orderSummary = new OrderSummary(order.getId(), order.getTitle());
                orderSummaries.add(orderSummary);
            }*/
            UserSummary userSummary = new UserSummary(user.getId(), user.getUsername());
            allUsersResponse.add(userSummary);
        }
        return allUsersResponse;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable long id, @RequestBody UserRequest userRequest) {
        User user = userRepository.getOne(id);
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        return "User has been updated";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));;

        userRepository.delete(user);

        return "User has been deleted";
    }
}
