<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <title>JSP - Hello World</title>
    </head>

    <body>
        <!--
        <h1>
            <%= "Hello World!" %>
        </h1>
        <br/>
        <a href="hello-servlet">Hello Servlet</a>
        -->
        <jsp:include page="navbar.jsp"></jsp:include>

        <div class="d-flex justify-content-center">
            <form action="NameServlet" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Inserisci il tuo nome: </label>
                    <input name="name" type="text" id="name" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Invia nome</button>
            </form>
        </div>

    </body>

</html>