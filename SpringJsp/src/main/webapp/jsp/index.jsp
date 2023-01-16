<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <jsp:include page="header.jsp"></jsp:include>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <br><br>
        <c:if test="${Info == null}">
            <div class="alert alert-warning" role="alert">
                <div class="text-center">
                    <p>There is no element yet! Go create one.</p>
                </div>
            </div>
        </c:if>
        <c:if test="${Info != null}">
            <div class="text-center">
                <h3>The last added WebSiteInfo is:</h3><br>
            </div>
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="d-flex flex-column">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h5 class="card-title">${Info.name}</h5>
                            <p class="card-text">${Info.description}</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
    <jsp:include page="script.jsp"></jsp:include>
</html>
