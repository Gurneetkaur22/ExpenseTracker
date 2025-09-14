<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <form action="saveUser" method="post">
        Username: <input type="text" name="username"/><br/>
        Password: <input type="password" name="password"/><br/>
        <input type="submit" value="Register"/>
    </form>

    <c:if test="${not empty message}">
        <p style="color:green">${message}</p>
    </c:if>

    <p>Already registered? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
</body>
</html>