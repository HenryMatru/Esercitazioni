<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <jsp:include page="header.jsp"></jsp:include>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <c:if test="${operation != null}">
            <div class="alert alert-success" role="alert">
                <p>${operation}</p>
            </div>
        </c:if>
        <div class="container">
            <form action="${"/createInfo"}" method="post">
                <div class="mb-3">
                    <label for="description" class="form-label">Description: </label>
                    <input type="text" name="description" class="form-control" id="description">
                </div>
                <div class="mb-3">
                    <label for="websitename" class="form-label">WebSite name: </label>
                    <input type="text" name="websitename" class="form-control" id="websitename">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </body>
    <jsp:include page="script.jsp"></jsp:include>
</html>