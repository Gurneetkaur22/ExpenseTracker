package com.Tracker.ExpenseTracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

//import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private String category;
    private Double amount;
    private LocalDate date;
    private String description;
  
	
//	@Column(name = "username")
//	private String username;
    @JoinColumn(name = "user_id")
	 @ManyToOne(fetch = FetchType.EAGER)
//    @JsonIgnoreProperties
	 private User user;
   
   

    // Getters & Setters
    public Long getExpenseId() { return expenseId; }
    public void setExpenseId(Long expenseId) { this.expenseId = expenseId; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
 
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
}
