package com.Tracker.ExpenseTracker.Controller;

import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<User> getUsersApi() {
        return userService.getAllUsers();
    }
}
