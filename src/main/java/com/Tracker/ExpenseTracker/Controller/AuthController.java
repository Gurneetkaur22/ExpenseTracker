package com.Tracker.ExpenseTracker.Controller;

//import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.service.UserService;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
    	  System.out.println("Register page called");
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String username,
                                  @RequestParam String password,
                                  Model model) {
        if(userService.existsByUsername(username)) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        userService.saveUser(username, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model,
                               HttpSession session) {
        if(userService.validateUser(username, password)) {
            session.setAttribute("username", username); // simple session
            return "home"; // redirect to home.jsp after login
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
   
}
