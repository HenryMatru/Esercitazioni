<%@ page import="it.course.servletuseresempio2.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="it.course.servletuseresempio2.dao.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

  <head>
      <jsp:include page="head.jsp"></jsp:include>
  </head>

  <body>
      <jsp:include page="navbar.jsp"></jsp:include>
      <c:if test="${empty lista_user}">
        <p>There are no users</p>
      </c:if>
      <c:if test="${not empty lista_user}">
          <table class="table table-striped">
              <thead>
                  <tr>
                      <th>Id</th>
                      <th>Name</th>
                      <th>Email</th>
                      <th>Country</th>
                      <th>Age</th>
                      <th>Action</th>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach var="user" items="${lista_user}">
                      <tr>
                          <td><c:out value="${user.getId()}"/></td>
                          <td><c:out value="${user.getName()}"/></td>
                          <td><c:out value="${user.getEmail()}"/></td>
                          <td><c:out value="${user.getCountry()}"/></td>
                          <td><c:out value="${user.getAge()}"/></td>
                          <td>
                              <a href="ServletDeleteUser?id=<c:out value='${user.getId()}'/>">Delete</a>
                              <a href="ServletUpdateUser?id=<c:out value='${user.getId()}'/>">Update</a>
                              <!-- <a href="ServletDeleteUser?id=<c:out value='${user.getId()}'/>"><i class="bi bi-trash"></i></a> -->
                              <!-- <a href="ServletUpdateUser?id=<c:out value='${user.getId()}'/>"><i class="bi bi-pencil-square"></i></a> -->
                          </td>
                      </tr>
                  </c:forEach>
              </tbody>
          </table>
      </c:if>
  </body>

</html>
