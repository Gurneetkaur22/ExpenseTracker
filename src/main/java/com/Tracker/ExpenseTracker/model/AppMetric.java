package com.Tracker.ExpenseTracker.model;

import jakarta.persistence.*;
@Entity
@Table(name = "app_metric")
public class AppMetric {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // e.g. "report.views"

    private long countValue;

    public AppMetric() {}
    public AppMetric(String name, long countValue) { this.name = name; this.countValue = countValue; }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCountValue() {
		return countValue;
	}
	public void setCountValue(long countValue) {
		this.countValue = countValue;
	}
    

}
