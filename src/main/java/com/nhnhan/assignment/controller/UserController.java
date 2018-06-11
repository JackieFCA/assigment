package com.nhnhan.assignment.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhnhan.assignment.UserRepository;
import com.nhnhan.assignment.model.User;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception());
    }
    
    @PostMapping("/users")
    public User createNote(@Valid @RequestBody User user) {
        long startDate = user.getStartDate().getTime();
        long currentDate = new Date().getTime();
        
        long diff = (currentDate - startDate)/ (1000 * 60 * 60 * 24);
        user.setLoyalPoint((diff + 1) * 5);
        return userRepository.save(user);
    }
}
