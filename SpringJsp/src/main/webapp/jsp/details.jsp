<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <jsp:include page="header.jsp"></jsp:include>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="container">
            <h1>Details</h1>
        </div>
        <div class="container">
            <c:forEach items="${ListWebSiteIngo}" var="WebSiteInfo">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${WebSiteInfo.description}</h5>
                        <p class="card-text">${WebSiteInfo.name}</p>
                    </div>
                </div>
                <br>
            </c:forEach>
        </div>
    </body>
    <jsp:include page="script.jsp"></jsp:include>
</html>