<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html lang = "de">
<head>
    <link href="<c:url value="/resources/css/style2.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>
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
                <button class = "item">7</button>
                <button class = "item">8</button>
                <button class = "item">9</button>
                <button class = "item">4</button>
                <button class = "item">5</button>
                <button class = "item">6</button>
                <button class = "item">1</button>
                <button class = "item">2</button>
                <button class = "item">3</button>
                <button class = "item doubleButtRaw">,</button>
                <button class = "item">0</button>
            </div>
            <div class = containerButtCol>
                <button class = "item">C</button>
                <button class = "item doubleButtCol op">+</button>
                <button class = "item doubleButtCol">E</button>
            </div>
        </div>
    </div>
</body>
</html>