<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<c:if test="${success != null}">
		<div>
			<h1>${success}</h1>
		</div>
	</c:if>
	<form method="post">
		<label for="name">Nombre</label>
		<input type="text" name="name">
		<label for="desc">Descripcion</label>
        <input type="text" name="desc">
		<input type = "submit" value = "Añadir"/>
	</form>
</t:base>