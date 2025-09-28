package com.uiService.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

    private Long id;
    private String userId;
    private String category;
    private BigDecimal amount;
    private LocalDate date;

    // Default constructor
    public Expense() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
