package com.Tracker.ExpenseTracker.repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Tracker.ExpenseTracker.model.Category;
import com.Tracker.ExpenseTracker.model.Expense;


public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	 List<Expense> findByDateBetween(LocalDate start, LocalDate end);
	 @Query("SELECT e.category, SUM(e.amount) " +
	           "FROM Expense e " +
	           "WHERE e.date BETWEEN :startDate AND :endDate " +
	           "GROUP BY e.category ORDER BY e.category")
	 List<Object[]> findCategoryWiseExpense(@Param("startDate") LocalDate startDate,
             @Param("endDate") LocalDate endDate);

	    @Query("""
	           SELECT COALESCE(SUM(e.amount),0)
	           FROM Expense e
	           WHERE e.date BETWEEN :start AND :end
	           """)
	    BigDecimal totalBetween(LocalDate start, LocalDate end);

	    List<Expense> findByCategoryAndDateBetween(Category category, LocalDate start, LocalDate end);
}
