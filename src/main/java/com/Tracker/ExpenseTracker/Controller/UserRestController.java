package com.Tracker.ExpenseTracker.Controller;

import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserRepo userRepo;

    // Register a new user
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // check if username already exists
        User existing = userRepo.findByUsername(user.getUsername());
        if (existing != null) {
            return ResponseEntity.badRequest().build(); // username taken
        }
        User saved = userRepo.save(user);
        return ResponseEntity.ok(saved);
    }

    // Get all users (for testing)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get single user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepo.findById(id)
                       .map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
}
