package com.uiService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {

    private final RestTemplate restTemplate;

    public LoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        try {
            // Here you can call UserService to validate credentials
            restTemplate.getForObject("http://userService/api/users", String.class);

            model.addAttribute("userName", username);
            return "dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid credentials!");
            return "login";
        }
    }
}
