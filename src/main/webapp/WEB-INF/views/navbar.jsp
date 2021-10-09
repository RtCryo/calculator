<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-max-widths">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e6e6e6;">
                <img src="<c:url value="/resources/logo.png"/>" width="40" height="40" class="d-inline-block align-text-top ms-2 me-3" alt="">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<c:url value="/calc"/>">Calculator</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<c:url value="/admin"/>">Admin</a>
                    </li>
                </ul>
                <div class="navbar-brand" id = "userNameNavbar"></div>
                <form class="form-signin" method="post" action="<c:url value="/logout"/>">
                    <button type="submit" class="btn btn-primary me-3">Logout</button>
                </form>
            </nav>
        </div>
    </div>
</div>
