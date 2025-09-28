package com.uiService.controller;

import com.uiService.model.Expense;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class DashboardController {

    private final RestTemplate restTemplate;

    public DashboardController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        String userId = "1"; // replace with actual logged-in user id

        List<Expense> expenses = Arrays.asList(
                restTemplate.getForObject("http://expenseService/api/expenses/user/" + userId, Expense[].class)
        );

        model.addAttribute("userName", "User");
        model.addAttribute("expenses", expenses);

        return "dashboard";
    }
}
