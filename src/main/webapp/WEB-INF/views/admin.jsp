<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<html>
<head>
    <link href="<c:url value="/resources/css/styleAdmin.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/jquery-3.6.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/adminScript.js" />"></script>
    <script src="<c:url value="/resources/sockjs.min.js" />"></script>
    <script src="<c:url value="/resources/stomp.min.js" />"></script>
    <title>Admin panel</title>
</head>
<body>
    <div id = "expressionList">
        <div>
            <label>
                <input class="chkBoxSelectAll" type="checkbox" value="0">
            </label>
            <button class = "buttonDel">DELETE</button></div>
    </div>
</body>
</html>
