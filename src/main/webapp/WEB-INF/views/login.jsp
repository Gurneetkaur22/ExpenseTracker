<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Login</h2>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<form action="/login" method="post">
    Username: <input name="username"/><br/>
    Password: <input name="password" type="password"/><br/>
    <button type="submit">Login</button>
</form>
<a href="/register">Register</a>
</body>
</html>
