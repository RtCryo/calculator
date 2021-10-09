<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<html lang = "de">
<head>
    <jsp:include page="header.jsp"/>
    <script src="<c:url value="/resources/js/usersScript.js" />"></script>
</head>
<body>
    <div id = "userslist"></div>
</body>
</html>
