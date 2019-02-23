<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<c:if test="${error != null }">
    Hubo bardo ${error}
    <% session.removeAttribute("error");%>
</c:if>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="${request.getContextPath()}" class="navbar-brand mr-auto">Absence</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
      
        </ul>
        <ul class="navbar-nav my-2 my-lg-0">
        <c:if test="${user != null}">
            <li class="nav-item active">
                <a href="me" class="nav-link">${user.getUsername()}</a>
            </li>
            <li class="nav-item active">
                <a href="logout" class="nav-link">Log out</a>
            </li>
        </c:if>
        <c:if test="${user == null }">
            <li class="nav-item active">
                <a href="singup" class="nav-link">Register</a>
            </li>
            <li class="nav-item active">
                <a href="login" class="nav-link">Login</a>
            </li>
        </c:if>
        </ul>
    </div>
</nav>