<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Weekly Report</title></head>
<body>
    <h2>Weekly Expense Report (Last 7 Days)</h2>
    <table border="1">
        <tr><th>Date</th><th>Total Amount</th></tr>
        <c:forEach var="row" items="${results}">
            <tr>
                <td>${row.category}</td>
                <td>${row.total}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>