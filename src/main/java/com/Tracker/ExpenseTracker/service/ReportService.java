package com.Tracker.ExpenseTracker.service;

import com.Tracker.ExpenseTracker.dto.CategorySummary;
import com.Tracker.ExpenseTracker.dto.Report;
import com.Tracker.ExpenseTracker.model.Category;
import com.Tracker.ExpenseTracker.repo.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

	@Autowired
    private final ExpenseRepository repo;

    public ReportService(ExpenseRepository repo) {
        this.repo = repo;
    }
    
    @ReportView
    @Transactional(readOnly = true)
    public Report generateWeeklyReport(LocalDate anyDateInWeek) {
        // ISO week: Monday..Sunday
        LocalDate start = anyDateInWeek.with(java.time.DayOfWeek.MONDAY);
        LocalDate end   = start.plusDays(6);
        return buildReport(start, end);
    }

    @ReportView
    @Transactional(readOnly = true)
    public Report generateLast7DaysReport(LocalDate today) {
        LocalDate start = today.minusDays(6);
        return buildReport(start, today);
    }

    @Transactional(readOnly = true)
    public Report generateMonthlyReport(YearMonth ym) {
        LocalDate start = ym.atDay(1);
        LocalDate end   = ym.atEndOfMonth();
        return buildReport(start, end);
    }
    
    @Transactional(readOnly = true)
    public Report generateRangeReport(LocalDate start, LocalDate end) {
        return buildReport(start, end);
    }

    private Report buildReport(LocalDate start, LocalDate end) {
        BigDecimal total = repo.totalBetween(start, end);
        List<Object[]> rows = repo.findCategoryWiseExpense(start, end);
        List<CategorySummary> categories = new ArrayList<>();
        for (Object[] row : rows) {
            Category cat = (Category) row[0];
            BigDecimal sum = (BigDecimal) row[1];
            categories.add(new CategorySummary(cat, sum));
        }
        return new Report(start, end, total == null ? BigDecimal.ZERO : total, categories);
    }
}
