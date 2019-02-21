<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<c:if test="${error != null }">
    Hubo bardo ${error}
    <% session.removeAttribute("error");%>
</c:if>
<nav class="nav">
    <li class="nav-item">
        <a href="" class="nav-link">Absence</a>
    </li>
    <ul class="nav justify-content-end">
        <c:if test="${user != null}">
            <li class="nav-item">
                <a href="me" class="nav-link">${user.getUsername()}</a>
            </li>
            <li class="nav-item">
                <a href="logout" class="nav-link">Log out</a>
            </li>
        </c:if>
        <c:if test="${user == null }">
            <li class="nav-item">
                <a href="singup" class="nav-link">Register</a>
            </li>
            <li class="nav-item">
                <a href="login" class="nav-link">Login</a>
            </li>
        </c:if>
        <c:if test="${providerID != null}">
            <li class="nav-item p-2">
                <div id="drpNotifications" class="dropdown">
                    <c:choose>
                        <c:when test="${cantNotificaciones != 0}">
                            <button id="drpBtnNotifications" class="btn btn-danger dropdown-toggle" type="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="material-icons">
                                    notifications_active
                                </i>${cantNotificaciones}
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                <c:foreach items="${notificaciones}" var="notificacion">
                                    <a class="dropdown-item" href="me.jsp#divSolicitudes">${notificacion.petitioner.username}
                                        ha solicitado tus servicios.</a>
                                </c:foreach>
                            </div>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="me.jsp#divSolicitudes">Ver Todas</a>
                            <!--^ACA VA EL ENLACE AL LISTADO DE SOLICITUDES QUE VE EL PROVEEDOR (Informe?)-->
                        </c:when>
                        <c:when test="${cantNotificaciones == 0}">
                            <button id="drpBtnNotifications" class="btn btn-light dropdown-toggle" type="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="material-icons">
                                    notifications_none
                                </i>0
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenu2"></div>
                            <a class="dropdown-item" href="me.jsp#divSolicitudes">Ver Todas</a>
                            <!--^ACA VA EL ENLACE AL LISTADO DE SOLICITUDES QUE VE EL PROVEEDOR (Informe?)-->
                        </c:when>
                    </c:choose>
                </div>
            </li>
        </c:if>
    </ul>
</nav>

<script type="text/javascript">
    var cantNotificaciones = 0;

    $(document).ready(function () {
        actualizarNotificaciones();

        //Sera reemplazado con tecnologia de webSockets
        setInterval(function () {
            actualizarNotificaciones();
        }, 15000);
    });

    function actualizarNotificaciones() {
        var action = "CARGAR_NOTIFICACIONES"

        var data = {
            ACTION: action
        };

        $.ajax({
            method: "POST",
            url: "${pageContext.request.contextPath}/contact",
            data: data,
            dataType: "json",
            success: function () {

            },
            error: function () {
                alert("Error intentando recuperar notificaciones, intenta mas tarde.");
            }
        });
    }
</script>