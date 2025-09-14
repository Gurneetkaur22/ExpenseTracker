<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Monthly Report</title></head>
<body>
    <h2>Monthly Expense Report</h2>
    <table border="1">
        <tr><th>Month</th><th>Total Amount</th></tr>
        <c:forEach var="row" items="${results}">
            <tr>
                <td>${row.category}</td>
                <td>${row.total}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>