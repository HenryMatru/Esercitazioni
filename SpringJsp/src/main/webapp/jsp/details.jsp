<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <jsp:include page="header.jsp"></jsp:include>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="text-center">
            <h1>Details</h1>
        </div>
        <c:if test="${ListWebSiteIngo.isEmpty()}">
            <div class="alert alert-warning" role="alert">
                <div class="text-center">
                    <p>The list is empty</p>
                </div>
            </div>
        </c:if>
        <c:if test="${!ListWebSiteIngo.isEmpty()}">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="d-flex flex-column">
                    <c:forEach items="${ListWebSiteIngo}" var="WebSiteInfo">
                        <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title">${WebSiteInfo.description}</h5>
                                <p class="card-text">${WebSiteInfo.name}</p>
                                <a href="/deleteInfo?id=<c:out value="${WebSiteInfo.id}"/>"><span class="bi bi-trash" style="font-size: 1rem; color: rgb(255, 0, 0);"></span></a>
                                <a href="/updateInfo?id=<c:out value="${WebSiteInfo.id}"/>"><span class="bi bi-pencil-fill" style="font-size: 1rem; color: rgb(0, 0, 255);"></span></a>
                            </div>
                        </div>
                        <br>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </body>
    <jsp:include page="script.jsp"></jsp:include>
</html>