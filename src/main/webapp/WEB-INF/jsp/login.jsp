<%@ include file="header.jsp" %>
<h2 class="text-center mb-4">Login</h2>
<form method="post" action="/login">
    <div class="mb-3">
        <label>Email</label>
        <input type="text" name="username" class="form-control" required/>
    </div>
    <div class="mb-3">
        <label>Password</label>
        <input type="password" name="password" class="form-control" required/>
    </div>
    <button type="submit" class="btn btn-success w-100">Login</button>
</form>
<p class="mt-3 text-center">
    New user? <a href="/register">Register</a>
</p>
<%@ include file="footer.jsp" %>
