<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-max-widths">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e6e6e6;">
                <img src="<c:url value="/resources/logo.png"/>" width="40" height="40" class="d-inline-block align-text-top ms-2 me-3">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<c:url value="/"/>">Index</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" aria-current="page" href="#">Calculator</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" aria-current="page" href="#">Admin</a>
                    </li>
                </ul>
                <a class="navbar-brand" href="#">Hallo, Anon</a>
            </nav>
        </div>
    </div>
</div>
