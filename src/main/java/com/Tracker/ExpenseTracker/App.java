package com.Tracker.ExpenseTracker;

/**
 * Hello world!
 *
 */


import com.Tracker.ExpenseTracker.dto.Report;
import com.Tracker.ExpenseTracker.model.AppMetric;
import com.Tracker.ExpenseTracker.model.Category;
import com.Tracker.ExpenseTracker.repo.AppMetricRepo;
import com.Tracker.ExpenseTracker.service.ExpenseService;
import com.Tracker.ExpenseTracker.service.ReportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
@EnableAspectJAutoProxy

public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    

    @Bean
    CommandLineRunner runner(ExpenseService expenseService,
                             ReportService reportService,
                             AppMetricRepo metricRepo) {
        return args -> {
        	new Thread(() -> {
            System.out.println("=== Personal Finance Tracker (Console) ===");
            System.out.println("Categories: " + Arrays.toString(Category.values()));

            Scanner sc = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1. Add Expense");
                System.out.println("2. View Weekly Report (current week)");
                System.out.println("3. View Last 7 Days Report");
                System.out.println("4. View Monthly Report (choose month)");
                System.out.println("5. Show Category-wise Analysis (choose range)");
                System.out.println("6. Show Report Views (AOP metric)");
                System.out.println("7. Exit");
                System.out.print("> ");

                String choice = sc.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> addExpenseFlow(sc, expenseService);
                        case "2" -> {
                            Report r = reportService.generateWeeklyReport(LocalDate.now());
                            printReport("Weekly (Mon..Sun)", r);
                        }
                        case "3" -> {
                            Report r = reportService.generateLast7DaysReport(LocalDate.now());
                            printReport("Last 7 Days", r);
                        }
                        case "4" -> monthlyReportFlow(sc, reportService);
                        case "5" -> categoryAnalysisFlow(sc, reportService);
                       
                        case "6" -> {
                            long views = metricRepo.findByName("report.views")
                                    .map(AppMetric::getCountValue).orElse(0L);
                            System.out.println("Report views so far (AOP): " + views);
                        }
                        case "7" -> running = false;
                        default -> System.out.println("Invalid option.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            System.out.println("Goodbye!");
        	}).start();
        };
        
    }

    private void addExpenseFlow(Scanner sc, ExpenseService expenseService) {
        System.out.print("Description: ");
        String desc = sc.nextLine().trim();

        System.out.print("Category (e.g., FOOD, TRAVEL, STUDY, ...): ");
        Category cat = Category.valueOf(sc.nextLine().trim().toUpperCase());

        System.out.print("Amount: ");
        BigDecimal amount = new BigDecimal(sc.nextLine().trim());

        System.out.print("Date (yyyy-MM-dd) or blank for today: ");
        String ds = sc.nextLine().trim();
        LocalDate date = ds.isEmpty() ? LocalDate.now() : LocalDate.parse(ds);

        var saved = expenseService.addExpense(desc, cat, amount, date);
        System.out.println("Saved expense id=" + saved.getId());
    }

    private void monthlyReportFlow(Scanner sc, ReportService reportService) {
        System.out.print("Enter month as yyyy-MM (e.g., 2025-08): ");
        String ymStr = sc.nextLine().trim();
        YearMonth ym = YearMonth.parse(ymStr);
        Report r = reportService.generateMonthlyReport(ym);
        printReport("Monthly " + ym, r);
    }

    private void categoryAnalysisFlow(Scanner sc, ReportService reportService) {
        System.out.print("Start date (yyyy-MM-dd): ");
        LocalDate start = LocalDate.parse(sc.nextLine().trim());
        System.out.print("End date (yyyy-MM-dd): ");
        LocalDate end = LocalDate.parse(sc.nextLine().trim());

        Report r = reportService.generateRangeReport(start, end);
        printReport("Category-wise Analysis (" + start + " to " + end + ")", r);
    }

    private void printReport(String title, Report report) {
        System.out.println("=== " + title + " ===");
        System.out.println("From " + report.getStart() + " to " + report.getEnd());
        System.out.println("Total: " + report.getTotal());

        if (report.getCategories().isEmpty()) {
            System.out.println("No expenses found in this period.");
        } else {
            System.out.println("Category Breakdown:");
            report.getCategories().forEach(cs ->
                    System.out.println("  " + cs.getCategory() + " → " + cs.getTotal()));
        }
    }

    }
    