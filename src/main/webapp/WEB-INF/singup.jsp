<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<t:base>
	<jsp:body>
		<form method="post" action="singup">
	          <label for="username">Username</label>
	          <input type="text" name="username"><br>
	          <label for="email">Email</label>
	          <input type="text" name="email"><br>
	          <label for="password1">Password</label>
	          <input type="password" name="password"><br>
	          <label for="password2">Repetir Password</label>
	          <input type="password" name="password2"><br>
	          <input type = "submit" value = "Sign up" />
    	</form>
	</jsp:body>
</t:base>