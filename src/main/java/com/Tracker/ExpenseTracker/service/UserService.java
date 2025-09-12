package com.Tracker.ExpenseTracker.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public String register(User user) {
    	if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User already exists!"; 
        }
        userRepository.save(user);
        return "Registered Successfully!";
    }

    public User login(String username, String password) {
        User u = userRepository.findByUsername(username);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}
