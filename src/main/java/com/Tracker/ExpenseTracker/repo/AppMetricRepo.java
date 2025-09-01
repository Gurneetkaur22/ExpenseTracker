package com.Tracker.ExpenseTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tracker.ExpenseTracker.model.AppMetric;

import java.util.Optional;

public interface AppMetricRepo extends JpaRepository<AppMetric, Long> {
    Optional<AppMetric> findByName(String name);
}

