package com.Tracker.ExpenseTracker.Controller;

import com.Tracker.ExpenseTracker.model.Expense;
import com.Tracker.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

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
    public String saveExpense(@ModelAttribute Expense expense) {
        expenseService.addExpense(expense);
        return "redirect:/viewExpenses";
    }

    @GetMapping("/viewExpenses")
    public String viewExpenses(Model model) {
        model.addAttribute("expenses", expenseService.getExpensesBetween(
                LocalDate.now().minusMonths(1), LocalDate.now()));
        return "viewExpenses";
    }
}
