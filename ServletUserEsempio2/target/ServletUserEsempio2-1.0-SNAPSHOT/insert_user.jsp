<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <title>JSP - DAO</title>
    </head>

    <body>
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
