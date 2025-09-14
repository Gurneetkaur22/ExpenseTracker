package com.Tracker.ExpenseTracker.model;

public class Report {
    private String category;   // e.g. "Food", "Transport", "This Week", "This Month"
    private Double total;   // total amount spent

    public Report() {}

    public Report(String category, Double total) {
        this.category = category;
        this.total = total;
    }

    // Getters and Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
