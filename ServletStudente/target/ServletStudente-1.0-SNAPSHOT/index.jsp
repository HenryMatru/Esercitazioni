<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <title>JSP - Studente</title>
    </head>

    <body>
        <div class="d-flex justify-content-center">
            <form action="StudenteServlet" method="post">
                <div class="mb-3">
                    <label for="nomeStudente" class="form-label">Nome studente:</label>
                    <input type="text" name="nomeStudente" id="nomeStudente" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="cognomeStudente" class="form-label">Cognome studente:</label>
                    <input type="text" name="cognomeStudente" id="cognomeStudente" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="scuolaStudente" class="form-label">Scuola studente:</label>
                    <input type="text" name="scuolaStudente" id="scuolaStudente" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Invia</button>
            </form>
        </div>
    </body>

</html>