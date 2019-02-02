<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<form action="provider" method="post">
		<label for="fullname">Nombre completo</label>
		<input type="text" name="fullname">
		<label for="telephone">Telefono</label>
        <input type="phone" name="telephone">
        <label for="postalCode">Codigo Postal</label>
        <input type="text" name="postalCode">
        <label for="street">Domicilio</label>
        <input type="text" name="street">
        <label for="birthDate">Fecha de nacimiento</label>
        <input type="date" name="birthDate">
        <input type = "submit" value = "Ser proveedor" />
	</form>
</t:base>