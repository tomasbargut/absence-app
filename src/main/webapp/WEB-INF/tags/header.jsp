<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
<c:if test="${user != null}">
	<a href="me">${user.getUsername()}</a>
	<a href="singout">Sing out</a>
</c:if>
<c:if test="${user == null }">
	<a href="singup">Sing up</a>
	<a href="singin">Sing in</a>
</c:if>
<c:if test="${error != null }">
	Hubo bardo ${error}
	<% session.removeAttribute("error"); %>
</c:if>
</div>