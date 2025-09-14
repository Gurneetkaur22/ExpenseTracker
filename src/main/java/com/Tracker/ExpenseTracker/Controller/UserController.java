
package com.Tracker.ExpenseTracker.Controller;

import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            model.addAttribute("username", username);
            return "addExpense"; 
        } else {
            model.addAttribute("error", "Invalid credentials!");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

//    @PostMapping("/saveUser")
//    public String saveUser(@RequestParam String username,
//                           @RequestParam String password,
//                           Model model) {
//    	
//    	 if (userService.existsByUsername(username)) {
//    	        model.addAttribute("error", "Username already exists!");
//    	        return "register";   
//    	    }
//    	 
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        userService.register(user);
//        model.addAttribute("message", "User registered successfully!");
//        return "login";
//    }
    
    @PostMapping("/saveUser")
    public String saveUser(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

       
        String result = userService.register(user);  // get response from service
        model.addAttribute("message", result);

        if (result.equals("User already exists!")) {
            return "register"; // stay on register page
        }
        return "login"; // redirect to login on success
    }

}
