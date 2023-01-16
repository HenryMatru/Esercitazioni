<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>

    <jsp:include page="header.jsp"></jsp:include>

    <body>
        <jsp:include page="navbar.jsp"></jsp:include>

        <div class="text-center">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Descrizione</th>
                        <th scope="col">Link</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ListProjects}" var="project">
                        <tr>
                            <td>${project.name}</td>
                            <td>${project.description}</td>
                            <td><a href="${project.link}">link repository</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>

    <jsp:include page="script.jsp"></jsp:include>

</html>