<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page session = "true" %>
<t:base>
	<div><h1>Contacto Realizado!</h1></div>
		<div><h2>Detalles</h2></div>
			<div><h3>Solicitud N°: <c:out value="${user.username}"/></h3></div>
			<div><h3>Solicitante: <c:out value="${user.username}"/></h3></div>
			<div><p>Email: <c:out value="${user.email}"/></p></div>
			<div><p>Telefono: <c:out value="${user.tel}"/></p></div>
			<div><h3>Servicio: <c:out value="${service.name}"/></h3></div>
			<div><p>Categoria: <c:out value="${category.name}"/></p></div>
			<div><h3>Proveedor: <c:out value="${provider.username}"/></h3></div>
			<div><p>Email: <c:out value="${provider.email}"/></p></div>
			<div><p>Telefono: <c:out value="${provider.tel}"/></p></div>
			
			<form action="/Absence">
				<input type="submit" value="Volver al Inicio">
			</form>
</t:base>