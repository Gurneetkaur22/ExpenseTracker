package com.Tracker.ExpenseTracker.service;

import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.repo.UserRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public boolean existsByUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    public void saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); 
        userRepo.save(user);
    }

    public boolean validateUser(String username, String password) {
        return userRepo.findByUsername(username)
                .map(u -> u.getPassword().equals(password))
                .orElse(false);
    }
    
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
