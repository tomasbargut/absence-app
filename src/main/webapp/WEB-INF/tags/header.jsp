<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<div style="background-color: #6351ce; color: #ECEFF1;">
    <c:if test="${error != null }">
        Hubo bardo ${error}
        <% session.removeAttribute("error");%>
    </c:if>
    <nav class="navbar navbar-expand-lg">
        <a href="${pageContext.request.contextPath}" class="navbar-brand mr-auto text-white"><h2><strong>Absence</strong></h2></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

            </ul>
            <ul class="navbar-nav my-2 my-lg-0">
                <c:if test="${user != null}">
                    <li class="nav-item active">
                        <a href="${pageContext.request.contextPath}/me" class="nav-link text-white">${user.getUsername()}</a>
                    </li>
                    <li class="nav-item active">
                        <a href="logout" class="nav-link text-white">Salir</a>
                    </li>
                </c:if>
                <c:if test="${user == null }">
                    <li class="nav-item active">
                        <a href="singup" class="nav-link text-white">Registrarse</a>
                    </li>
                    <li class="nav-item active">
                        <a href="login" class="nav-link text-white">Ingresar</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</div>