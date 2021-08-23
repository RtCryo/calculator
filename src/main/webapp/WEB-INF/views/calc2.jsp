<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html lang = "de">
<head>
    <link href="<c:url value="/resources/css/style2.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/newScript.js" />"></script>
    <title>Calc</title>
</head>
<body>
    <div class = "calc">
        <div class = "containerScore">
            <div class = "item score small"></div>
            <div class = "item score big" id = "result">0</div>
        </div>
        <div class = "containerBase">
            <div class = "containerButtRaw">
                <button class = "item op">/</button>
                <button class = "item op">*</button>
                <button class = "item op">-</button>
                <button class = "item num">7</button>
                <button class = "item num">8</button>
                <button class = "item num">9</button>
                <button class = "item num">4</button>
                <button class = "item num">5</button>
                <button class = "item num">6</button>
                <button class = "item num">1</button>
                <button class = "item num">2</button>
                <button class = "item num">3</button>
                <button class = "item doubleButtRaw comma">,</button>
                <button class = "item num">0</button>
            </div>
            <div class = containerButtCol>
                <button class = "item cancel">C</button>
                <button class = "item doubleButtCol op">+</button>
                <button class = "item doubleButtCol enter">E</button>
            </div>
        </div>
        <div>
            <ul id = "expressionList"></ul>
        </div>
    </div>
</body>
</html>