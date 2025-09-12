<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Add Expense</title>
</head>
<body>
    <h2>Add Expense</h2>
    <form action="saveExpense" method="post">
        Category: <input type="text" name="category"/><br/>
        Amount: <input type="number" step="0.01" name="amount"/><br/>
        Date: <input type="date" name="date"/><br/>
        Description: <input type="text" name="description"/><br/>
        <input type="submit" value="Save Expense"/>
    </form>

    <p><a href="${pageContext.request.contextPath}/viewExpenses">View Expenses</a></p>
</body>
</html>
