<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Expenses</title>
</head>
<body>
    <h2>Expenses</h2>
    <table border="1">
        <tr>
            <th>Category</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Description</th>
        </tr>
        <c:forEach var="expense" items="${expenses}">
            <tr>
                <td>${expense.category}</td>
                <td>${expense.amount}</td>
                <td>${expense.date}</td>
                <td>${expense.description}</td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="${pageContext.request.contextPath}/addExpense">Add New Expense</a></p>
     <a href="deleteExpense/${expense.expenseId}" onclick="return confirm('Are you sure?')">Delete</a>
    
     <br><hr>
    <h3>More Reports</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/reports/category">Category Wise Analysis</a></li>
        <li><a href="${pageContext.request.contextPath}/reports/weekly">Weekly Report</a></li>
        <li><a href="${pageContext.request.contextPath}/reports/monthly">Monthly Report</a></li>
    </ul>
</body>
</html>