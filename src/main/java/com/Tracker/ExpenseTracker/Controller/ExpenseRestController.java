package com.Tracker.ExpenseTracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Tracker.ExpenseTracker.model.Expense;
import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.repo.ExpenseRepository;
import com.Tracker.ExpenseTracker.repo.UserRepo;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseRestController {

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<Expense> saveExpense(
            @RequestBody Expense expense,
            @RequestParam Long userId) {

        User user = userRepo.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));

        expense.setUser(user);
        Expense saved = expenseRepo.save(expense);

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Expense> getExpenses() {
        return expenseRepo.findAll();
    }
}
