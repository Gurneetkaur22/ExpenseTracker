<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="loginUser" method="post">
        Username: <input type="text" name="username"/><br/>
        Password: <input type="password" name="password"/><br/>
        <input type="submit" value="Login"/>
    </form>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <p>Don’t have an account? <a href="register.jsp">Register here</a></p>
</body>
</html>
