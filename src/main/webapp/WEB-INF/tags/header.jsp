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
			<a href="signout" class="nav-link">signout</a>
		</li>
	</c:if>
	<c:if test="${user == null }">
		<li class="nav-item">
			<a href="singup" class="nav-link">Sing up</a>
		</li>
		<li class="nav-item">
			<a href="singin" class="nav-link">Sing in</a>
		</li>
	</c:if>
	</ul>
</nav>