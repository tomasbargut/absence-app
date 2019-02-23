<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:base>
	<jsp:body>
		<form method="post" action="singup">
			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" class="form-control" name="username">
			</div>
			<div class="form-group">
				<label for="email">Email</label>
				<input type="text" class="form-control" name="email">
			</div>
			<div class="form-group">
				<label for="password1">Password</label>
				<input type="password" class="form-control" name="password">
			</div>
			<div class="form-group">
				<label for="password2">Repetir Password</label>
				<input type="password" class="form-control" name="password2">
			</div>
			<div class="form-group">
			<input class="btn btn-primary" type="submit" value="Sign up" />
    	</form>
	</jsp:body>
</t:base>