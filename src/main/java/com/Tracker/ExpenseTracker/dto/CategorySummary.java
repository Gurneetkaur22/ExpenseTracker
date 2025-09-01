package com.Tracker.ExpenseTracker.dto;

import java.math.BigDecimal;

import com.Tracker.ExpenseTracker.model.Category;
public class CategorySummary {
	 private final Category category;
	    private final BigDecimal total;

	    public CategorySummary(Category category, BigDecimal total) {
	        this.category = category;
	        this.total = total;
	    }

	    public Category getCategory() { return category; }
	    public BigDecimal getTotal() { return total; }

	    @Override
	    public String toString() {
	        return category + " = " + total;
	    }
}
