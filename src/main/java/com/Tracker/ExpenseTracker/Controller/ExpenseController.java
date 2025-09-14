package com.Tracker.ExpenseTracker.Controller;

import com.Tracker.ExpenseTracker.model.Expense;
import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.service.ExpenseService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import java.time.LocalDate;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/addExpense")
    public String addExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "addExpense";
    }

    @PostMapping("/saveExpense")
    public String saveExpense(@ModelAttribute Expense expense, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");  
        if (user == null) {
            return "redirect:/login"; 
        }

        expense.setUser(user);   
        expenseService.addExpense(expense);

        return "redirect:/viewExpenses";
    }
    @GetMapping("/viewExpenses")
    public String viewExpenses(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("expenses", expenseService.getExpensesByUser(user));  // ✅ fetch user expenses
        return "viewExpenses";
    }
    
    @GetMapping("/deleteExpense/{expenseId}")
    public String deleteExpense(@PathVariable("expenseId") Long expenseId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Expense expense = expenseService.getExpenseById(expenseId);

        
        if (expense == null || !expense.getUser().getUserId().equals(user.getUserId())) {
            return "redirect:/viewExpenses"; // Expense not found or unauthorized
        }
        expenseService.deleteExpense(expenseId);
        return "redirect:/viewExpenses"; // go back to expense list
    }

}