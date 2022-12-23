<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <jsp:include page="head.jsp"></jsp:include>
    </head>

    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div>
            <form action="ServletInserUser" method="post">
                <div class="mb-3">
                    <label  class="form-label">Name:</label>
                    <input type="text" name="name" class="form-control" required="required">
                </div>
                <div class="mb-3">
                    <label class="form-label">Email:</label>
                    <input type="text" name="email" class="form-control" required="required">
                </div>
                <div class="mb-3">
                    <label  class="form-label">Country:</label>
                    <input type="text" name="country" class="form-control" required="required">
                </div>
                <div class="mb-3">
                    <label  class="form-label">Age:</label>
                    <input type="number" name="age" class="form-control" required="required">
                </div>
                <button type="submit" class="btn btn-success">Insert User</button>
            </form>
            <% if (request.getAttribute("utente-inserito") != null) { %>
                <div class="alert alert-success" role="alert">
                    <p> <%= request.getAttribute("utente-inserito") %> </p>
                </div>
            <% } %>
        </div>
    </body>

</html>
