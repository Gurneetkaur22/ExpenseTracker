
package com.Tracker.ExpenseTracker.service;

import com.Tracker.ExpenseTracker.model.Expense;
import com.Tracker.ExpenseTracker.model.Report;
import com.Tracker.ExpenseTracker.model.User;
import com.Tracker.ExpenseTracker.repo.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepo;

    @Transactional
    public Expense addExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    @Transactional
    public List<Expense> getExpensesBetween(LocalDate start, LocalDate end) {
        return expenseRepo.findByDateBetween(start, end);
    }
    
    public List<Expense> getExpensesByUser(User user) {
        return expenseRepo.findByUser(user); 
    }
//    public List<Expense> getExpensesByCategory(User user, String category) {
//        return expenseRepo.findByUserAndCategory(user, category);
//    }

    
    public List<Report> getCategoryWiseExpense(Long userId) {
        List<Expense> expenses = expenseRepo.findByUser_UserId(userId);

        return expenses.stream()
                .collect(Collectors.groupingBy(Expense::getCategory,
                        Collectors.summingDouble(exp -> 
                            exp.getAmount() != null ? exp.getAmount().doubleValue() : 0.0)))
                .entrySet()
                .stream()
                .map(entry -> new Report(entry.getKey(), entry.getValue()))  // category is String
                .collect(Collectors.toList());
    }

    // ✅ Weekly Report
    public List<Report> getWeeklyExpense(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        List<Expense> expenses = expenseRepo.findByUser_UserIdAndDateBetween(userId, startOfWeek, endOfWeek);

        double total = expenses.stream().mapToDouble(e -> e.getAmount().doubleValue()).sum();

        return List.of(new Report("This Week", total));
    }

    // ✅ Monthly Report
    public List<Report> getMonthlyExpense(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        List<Expense> expenses = expenseRepo.findByUser_UserIdAndDateBetween(userId, startOfMonth, endOfMonth);

        double total = expenses.stream().mapToDouble(e -> e.getAmount().doubleValue()).sum();

        return List.of(new Report("This Month", total));
    }
    public void deleteExpense(Long expenseId) {
        expenseRepo.deleteById(expenseId);
    }

    public Expense getExpenseById(Long expenseId) {
        return expenseRepo.findById(expenseId).orElse(null);
    }
}
