package com.Tracker.ExpenseTracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

////mapped to table in db
@Entity   
public class Expense {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	@Column(precision = 12, scale = 2, nullable = false)
	private BigDecimal amount; 
	@Column(nullable = false)
	private LocalDate date; // spending date
	private LocalDateTime createdAt; 
	private LocalDateTime updatedAt;
	
	public Expense() {}
		public Expense(String description, Category category, BigDecimal amount, LocalDate date) { 
			this.description = description;
			this.category = category;
			this.amount = amount; 
			this.date = date;
			}
		
		@PrePersist
		public void prePersist() { createdAt = LocalDateTime.now(); updatedAt = createdAt; }
		@PreUpdate 
		public void preUpdate() { updatedAt = LocalDateTime.now(); }
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
	}
	

