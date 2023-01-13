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
            <p>The list is empty</p>
        </c:if>
        <c:if test="${!ListWebSiteIngo.isEmpty()}">
            <div class="container">
                <div class="row justify-content-center">
                    <c:forEach items="${ListWebSiteIngo}" var="WebSiteInfo">
                        <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title">${WebSiteInfo.description}</h5>
                                <p class="card-text">${WebSiteInfo.name}</p>
                                <a href="/deleteInfo?id=<c:out value="${WebSiteInfo.id}"/>">Delete</a>
                                <a href="/updateInfo?id=<c:out value="${WebSiteInfo.id}"/>">Delete</a>
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