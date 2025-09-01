package com.Tracker.ExpenseTracker.service;

import com.Tracker.ExpenseTracker.model.Category;
import com.Tracker.ExpenseTracker.model.Expense;
import com.Tracker.ExpenseTracker.repo.ExpenseRepository;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }
    
    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Object[]> getCategoryWiseExpense(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findCategoryWiseExpense(startDate, endDate);
    }

    @Transactional
    public Expense addExpense(String description, Category category, BigDecimal amount, LocalDate date) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        Expense e = new Expense(description, category, amount, date);
        return repo.save(e);
    }
}
