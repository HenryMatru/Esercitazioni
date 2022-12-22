<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"
         import="it.course.servletstudente.models.*" %>
<html>

  <head>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
      <title>Lista Studenti</title>
  </head>

  <%
      Studente s = ((Studente)request.getAttribute("studente"));
  %>

  <body>
      <table class="table table-striped">
          <thead>
              <tr>
                  <th>Nome</th>
                  <th>Cognome</th>
                  <th>Scuola</th>
              </tr>
          </thead>
          <tbody>
              <tr>
                  <th><%= s.getNome() %></th>
                  <th><%= s.getCognome() %></th>
                  <th><%= s.getScuola() %></th>
              </tr>
          </tbody>
      </table>
  </body>

</html>
