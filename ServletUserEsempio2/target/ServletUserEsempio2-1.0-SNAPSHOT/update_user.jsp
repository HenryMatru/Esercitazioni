<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <jsp:include page="head.jsp"></jsp:include>
    </head>

    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div>
            <form action="ServletUpdateUser" method="post">
                <input type="hidden" name="id" value="${user.getId()}">
                <div class="mb-3">
                    <label  class="form-label">Name:</label>
                    <input type="text" name="name" class="form-control" value="${user.getName()}">
                </div>
                <div class="mb-3">
                    <label class="form-label">Email:</label>
                    <input type="text" name="email" class="form-control" value="${user.getEmail()}">
                </div>
                <div class="mb-3">
                    <label  class="form-label">Country:</label>
                    <input type="text" name="country" class="form-control" value="${user.getCountry()}">
                </div>
                <div class="mb-3">
                    <label  class="form-label">Age:</label>
                    <input type="number" name="age" class="form-control" value="${user.getAge()}">
                </div>
                <button type="submit" class="btn btn-success">Update User</button>
            </form>
            <% if (request.getAttribute("utente-aggiornato") != null) { %>
            <div class="alert alert-success" role="alert">
                <p> <%= request.getAttribute("utente-inserito") %> </p>
            </div>
            <% } %>
        </div>
    </body>

</html>
