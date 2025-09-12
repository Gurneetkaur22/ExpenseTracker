package com.Tracker.ExpenseTracker.repo;

import com.Tracker.ExpenseTracker.model.Expense;
import com.Tracker.ExpenseTracker.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
    List<Expense> findByCategory(String category);
    List<Expense> findByUser(User user);
}
