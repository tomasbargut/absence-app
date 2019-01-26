<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<form method="post" action="singin">
	          <label for="username">Username</label>
	          <input type="text" name="username">
	          <label for="password">Password</label>
	          <input type="password" name="password">
	          <input type = "submit" value = "Log in" />
    	</form>
</t:base>