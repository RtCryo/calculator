<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<html lang="de">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.6.0.min.js"/>"></script>
    <script src="<c:url value="/resources/js/sockjs.min.js"/>"></script>
    <script src="<c:url value="/resources/js/stomp.min.js"/>"></script>
    <title>Calculator service</title>
    <script src="<c:url value="/resources/js/indexScript.js"/>"></script>
</head>
    <body>
    <div class="container-max-widths">
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e6e6e6;">
                    <img src="<c:url value="/resources/logo.png"/>" width="40" height="40" class="d-inline-block align-text-top ms-2 me-3" alt="">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    </ul>
                    <div class="navbar-brand" id = "userNameNavbar"></div>
                </nav>
            </div>
        </div>
    </div>
        <form class="form-signin" method="post" action="<c:url value="/login"/>">
            <div class="container my-4">
                <div class="row justify-content-center pt-4">
                    <label for="username" class="col-sm-1 col-form-label">Email</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" id="username" name="username" placeholder="email@example.com">
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
