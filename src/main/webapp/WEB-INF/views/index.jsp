<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<html lang="de">
<head>
    <jsp:include page="header.jsp"/>
    <script src="<c:url value="/resources/js/indexScript.js"/>"></script>
</head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <form class="form-signin" method="post" action="/login">
            <div class="container my-4">
                <div class="row justify-content-center pt-4">
                    <label for="username" class="col-sm-1 col-form-label">Email</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" id="username" placeholder="email@example.com">
                    </div>
                </div>
            <div class="row justify-content-center py-2">
                <label for="password" class="col-sm-1 col-form-label">Password</label>
                <div class="col-sm-2">
                    <input type="password" class="form-control" id="password" name="password" placeholder= "password">
                </div>
            </div>
            <div class="row justify-content-center p-4">
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary">Confirm identity</button>
                </div>
            </div>
            </div>
        </form>
        <div id = "messages"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
