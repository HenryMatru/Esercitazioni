<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

  <head>
      <title>Login</title>
  </head>

  <body>
      <jsp:include page="navbar.jsp"></jsp:include>
      <form action="LoginServlet" method="post">
        <div class="mb-3">
          <label for="username" class="form-label">Username</label>
          <input type="text" name="username" id="username" class="form-control">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input type="password" name="password" id="password" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Sign in</button>
      </form>
      <jsp:scriptlet> if (request.getAttribute("error") != null) { </jsp:scriptlet>
          <div class="alert alert-danger" role="alert">
              <jsp:expression> request.getAttribute("error") </jsp:expression>
          </div>
      <jsp:scriptlet> } </jsp:scriptlet>
  </body>

</html>
