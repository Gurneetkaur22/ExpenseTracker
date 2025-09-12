package com.Tracker.ExpenseTracker.repo;

import com.Tracker.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
  
}
