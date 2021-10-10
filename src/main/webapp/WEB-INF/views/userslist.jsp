<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<html lang = "de">
<head>
    <jsp:include page="header.jsp"/>
    <script src="<c:url value="/resources/js/usersScript.js" />"></script>
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="p-3">
        <ul id = "userslist">
        </ul>
    </div>
</body>
</html>
