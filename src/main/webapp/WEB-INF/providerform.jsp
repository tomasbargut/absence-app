<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<t:base>
	<form action="provider" method="post">
		<label for="name">Nombre completo</label>
		<input type="text" name="name">
		<label for="phone">Telefono</label>
        <input type="phone" name="phone">
        <label for="postal_code">Codigo Postal</label>
        <input type="text" name="postal_code">
        <label for="street">Domicilio</label>
        <input type="text" name="street">
        <label for="birth_date">Fecha de nacimiento</label>
        <input type="text" name="birth_date">
        <input type = "submit" value = "Ser proveedor" />
	</form>
</t:base>