<%@ include file="header.jsp" %>
<h2 class="text-center mb-4">Register</h2>
<form method="post" action="/register">
    <div class="mb-3">
        <label>Email</label>
        <input type="email" name="email" class="form-control" required/>
    </div>
    <div class="mb-3">
        <label>Username</label>
        <input type="text" name="username" class="form-control" required/>
    </div>
    <div class="mb-3">
        <label>Password</label>
        <input type="password" name="password" class="form-control" required/>
    </div>
    <button type="submit" class="btn btn-primary w-100">Register</button>
</form>
<p class="mt-3 text-center">
    Already registered? <a href="/login">Login</a>
</p>
<%@ include file="footer.jsp" %>
