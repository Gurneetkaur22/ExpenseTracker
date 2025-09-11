package com.Tracker.ExpenseTracker.service;

import com.Tracker.ExpenseTracker.model.Expense;
import com.Tracker.ExpenseTracker.repo.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

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
}
