package com.Tracker.ExpenseTracker.aop;

import com.Tracker.ExpenseTracker.model.AppMetric;
import com.Tracker.ExpenseTracker.repo.AppMetricRepo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class ReportingAspect {
	private final AppMetricRepo metricRepo;

    public ReportingAspect(AppMetricRepo metricRepo) {
        this.metricRepo = metricRepo;
    }

    @After("execution(* com.Tracker.ExpenseTracker.service.ReportService.generate*(..))")
    @Transactional
    public void countReportViews() {
    	 metricRepo.findByName("report.views")
         .ifPresentOrElse(
             metric -> {
                 metric.setCountValue(metric.getCountValue() + 1);
                 metricRepo.saveAndFlush(metric);
             },
             () -> {
                 AppMetric metric = new AppMetric("report.views", 1L);
                 metricRepo.saveAndFlush(metric);
             }
         );
    }

}
