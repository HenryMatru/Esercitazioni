<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <jsp:include page="header.jsp"></jsp:include>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="container">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${Info.name}</h5>
                    <p class="card-text">${Info.description}</p>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="script.jsp"></jsp:include>
</html>
