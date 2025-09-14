package com.Tracker.ExpenseTracker.Controller;

import com.Tracker.ExpenseTracker.model.Report;
import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.service.ExpenseService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ExpenseService expenseService;

    // ✅ Category-wise Analysis
    @GetMapping("/category")
    public String categoryReport(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<Report> reports = expenseService.getCategoryWiseExpense(user.getUserId());
        model.addAttribute("results", reports);
        model.addAttribute("reportTitle", "Category Wise Expense Report");
        return "categoryReport";   // JSP page
    }

    // ✅ Weekly Report
    @GetMapping("/weekly")
    public String weeklyReport(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<Report> reports = expenseService.getWeeklyExpense(user.getUserId());
        model.addAttribute("results", reports);
        model.addAttribute("reportTitle", "Weekly Expense Report");
        return "weeklyReport";   // JSP page
    }

    // ✅ Monthly Report
    @GetMapping("/monthly")
    public String monthlyReport(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<Report> reports = expenseService.getMonthlyExpense(user.getUserId());
        model.addAttribute("results", reports);
        model.addAttribute("reportTitle", "Monthly Expense Report");
        return "monthlyReport";   // JSP page
    }
}
