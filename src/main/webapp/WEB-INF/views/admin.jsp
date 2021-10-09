<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<html lang = "de">
<head>
    <link href="<c:url value="/resources/css/styleAdmin.css" />" rel="stylesheet">
    <jsp:include page="header.jsp"/>
    <script src="<c:url value="/resources/js/adminScript.js"/>"></script>
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div id = "expressionList">
        <label>
            <input class="chkBoxSelectAll" type="checkbox" value="0">
        </label>
        <button class = "buttonDel">DELETE</button>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
