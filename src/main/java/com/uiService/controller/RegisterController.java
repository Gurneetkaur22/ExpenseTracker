package com.uiService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {

    private final RestTemplate restTemplate;

    public RegisterController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        try {
            Map<String, String> request = new HashMap<>();
            request.put("username", username);
            request.put("email", email);
            request.put("password", password);

            // Call UserService via Eureka
            restTemplate.postForObject("http://userService/api/users", request, String.class);

            model.addAttribute("success", "Registration successful. Please login.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed!");
            return "register";
        }
    }
}
