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
                        <th scope="col">Categoria</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ListSkills}" var="skill">
                        <tr>
                            <td>${skill.name}</td>
                            <td>${skill.description}</td>
                            <td>${skill.category.name}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>

    <jsp:include page="script.jsp"></jsp:include>

</html>
