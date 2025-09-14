package com.Tracker.ExpenseTracker.Controller;

import com.Tracker.ExpenseTracker.model.Report;
import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.service.ExpenseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportRestController {

    @Autowired
    private ExpenseService expenseService;

    // ✅ Category-wise report
    @GetMapping("/category")
    public List<Report> getCategoryReport(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            throw new RuntimeException("User not logged in");
        }
        return expenseService.getCategoryWiseExpense(user.getUserId());
    }

    // ✅ Weekly report
    @GetMapping("/weekly")
    public List<Report> getWeeklyReport(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            throw new RuntimeException("User not logged in");
        }
        return expenseService.getWeeklyExpense(user.getUserId());
    }

    // ✅ Monthly report
    @GetMapping("/monthly")
    public List<Report> getMonthlyReport(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            throw new RuntimeException("User not logged in");
        }
        return expenseService.getMonthlyExpense(user.getUserId());
    }
}
