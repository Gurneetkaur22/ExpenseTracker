<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Register</h2>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<form action="/register" method="post">
    Username: <input name="username"/><br/>
    Password: <input name="password" type="password"/><br/>
    <button type="submit">Register</button>
</form>
<a href="/login">Login</a>
</body>
</html>
