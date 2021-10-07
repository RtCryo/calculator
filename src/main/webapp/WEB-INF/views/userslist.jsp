<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<html>
<head>
    <title>UsersList#</title>
    <script src="<c:url value="/resources/jquery-3.6.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/usersScript.js" />"></script>
    <script src="<c:url value="/resources/sockjs.min.js" />"></script>
    <script src="<c:url value="/resources/stomp.min.js" />"></script>
</head>
<body>
    <div id = "userslist"></div>
</body>
</html>
