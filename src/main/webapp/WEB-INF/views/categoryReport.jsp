<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Category Wise Analysis</title></head>
<body>
    <h2>Category Wise Expense Analysis</h2>
    <table border="1">
        <tr><th>Category</th><th>Total Amount</th></tr>
        <c:forEach var="row" items="${results}">
            <tr>
                <td>${row.category}</td>
                <td>${row.total}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
