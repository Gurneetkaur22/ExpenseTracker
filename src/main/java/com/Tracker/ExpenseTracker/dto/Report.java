package com.Tracker.ExpenseTracker.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Report {
    private final LocalDate start;
    private final LocalDate end;
    private final BigDecimal total;
    private final List<CategorySummary> categories;

    public Report(LocalDate start, LocalDate end, BigDecimal total, List<CategorySummary> categories) {
        this.start = start;
        this.end = end;
        this.total = total;
        this.categories = categories;
    }

    public LocalDate getStart() { return start; }
    public LocalDate getEnd() { return end; }
    public BigDecimal getTotal() { return total; }
    public List<CategorySummary> getCategories() { return categories; }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report from ").append(start).append(" to ").append(end).append("\n");
        sb.append("Total: ").append(total).append("\n");
        sb.append("Categories:\n");
        for (CategorySummary cs : categories) {
            sb.append("  ").append(cs.getCategory()).append(" → ").append(cs.getTotal()).append("\n");
        }
        return sb.toString();
    }

}

