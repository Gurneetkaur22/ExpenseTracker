# Personal Finance Tracker

A **microservices-based personal finance tracker** using Spring Boot, Eureka, API Gateway, and JSP frontend.

## **Overview**

This project allows users to manage their personal finances by tracking expenses. The system is built using **microservices architecture**:

- **User Service**: Manages user registration, login, and user details.
- **Expense Service**: Manages expenses including adding, updating, deleting, bulk add, category analysis, and calculating weekly/monthly totals.
- **Eureka Server**: Provides service discovery for UserService and ExpenseService.
- **API Gateway**: Single entry point for accessing microservices.
- **UI Service**: Frontend built using JSP and Bootstrap for login, registration, and dashboard.

---

## **Microservices Details**

### **1. User Service**
- Functionalities:
  - Add User
  - Update User
  - Delete User
  - Get User by ID
  - Get All Users
- Entity fields: `userId`, `userName`, `email`, `password`
- Endpoint example: `/api/users`

### **2. Expense Service**
- Functionalities:
  - Add Expense
  - Update Expense
  - Delete Expense
  - Bulk Add Expenses
  - Get All Expenses
  - Category-wise Analysis
  - Monthly Expenses
  - Weekly Expenses
- Entity fields: `id`, `userId`, `category`, `amount`, `date`
- Endpoint example: `/api/expenses`

### **3. Eureka Server**
- Service discovery for all services.
- Dashboard URL: `http://localhost:8761`

### **4. API Gateway**
- Provides a single access point to all microservices.
- Routes requests from UI Service to UserService and ExpenseService.

### **5. UI Service (Frontend)**
- JSP + Bootstrap frontend.
- Features:
  - Login & Registration pages
  - Dashboard with tabs:
    - Add Expense
    - View Expenses
    - Category Analysis
    - Monthly Summary
    - Weekly Summary
    - Delete Expense
- Communicates with backend via **API Gateway**.

---

## **Tech Stack**

- **Backend:** Java 17, Spring Boot 3.x  
- **Frontend:** JSP, Bootstrap 5  
- **Microservices:** Eureka Discovery, API Gateway  
- **Build:** Maven  
- **Data Format:** JSON  

---

## **Setup Instructions**

1. **Start Eureka Server**
   ```bash
   mvn spring-boot:run
