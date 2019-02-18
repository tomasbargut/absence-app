<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error != null }">
	Hubo bardo ${error}
	<% session.removeAttribute("error"); %>
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
		<c:if test="${providerID != null">
			<li class="nav-item p-2">
				<a href="notifications" class="nav-link">Tienes ${provider.getNotificationAmmount()} Notificaciones!</a>
			</li>
		</c:if>
	</ul>
</nav>