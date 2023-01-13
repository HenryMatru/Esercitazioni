<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <jsp:include page="header.jsp"></jsp:include>
    <body>
    <jsp:include page="navbar.jsp"></jsp:include>
    <c:if test="${operation != null}">
        <div class="alert alert-success" role="alert">
            <p>${operation}</p>
            <p>${websitename}</p>
            <p>${description}</p>
        </div>
    </c:if>
    <c:if test="${operation == null && redirect == null}">
        <div class="container">
            <form action="${"/updateInfo"}" method="post">
                <input type="hidden" name="id" value="${WebSiteInfo.id}">
                <div class="mb-3">
                    <label for="description" class="form-label">Description: </label>
                    <input type="text" name="description" value="${WebSiteInfo.description}" class="form-control" id="description">
                </div>
                <div class="mb-3">
                    <label for="webSiteName" class="form-label">WebSite name: </label>
                    <input type="text" name="webSiteName" value="${WebSiteInfo.name}" class="form-control" id="webSiteName">
                </div>
                <button type="submit" class="btn btn-primary">Modify</button>
            </form>
        </div>
    </c:if>
    <br><br>
    <div class="d-flex justify-content-center">
        <div class="btn-group mr-4 pr-4">
            <a href="${"/home"}" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Home</a>
            <a href="${"/details"}" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Details</a>
            <a href="${"/createInfo"}" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Create Info</a>
        </div>
    </div>
    </body>
    <jsp:include page="script.jsp"></jsp:include>
</html>