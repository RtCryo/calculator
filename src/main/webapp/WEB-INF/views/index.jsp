<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<html lang="de">
<head>
    <script src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/indexScript.js" />"></script>
    <script src="<c:url value="/resources/sockjs.min.js" />"></script>
    <script src="<c:url value="/resources/stomp.min.js" />"></script>
    <title>Index</title>
</head>
    <body>
        <h2>Hello World!</h2>
        <h1><a href="calc">Calc</a></h1>
        <div id = "messages">
        </div>
    </body>
</html>
