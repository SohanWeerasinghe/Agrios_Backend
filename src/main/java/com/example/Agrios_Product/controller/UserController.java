package com.example.Agrios_Product.controller;


import com.example.Agrios_Product.Service.UserService;
import com.example.Agrios_Product.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")  // Adjust your frontend URL if necessary
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "/users/{id}")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping(path = "/users", params = "email")
    public User findUserByEmail(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping(path = "/users", params = "accountType")
    public List<User> getUsersByAccountType(@RequestParam String accountType) {
        return userService.getUsersByAccountType(accountType);
    }



}

