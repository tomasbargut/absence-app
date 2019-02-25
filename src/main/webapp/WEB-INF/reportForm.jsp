<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
<form method="POST">
<label for="title">Titulo</label>
<input type="text" name="title">
<label for="desc">Descripcion</label>
<input type="text" name="desc">

<c:if test="${administrator != null}">
<label for="status">Estado</label>
<input type="radio" name="status" values="APROBADA">
<input type="radio" name="status" values="REACHAZADA">
</c:if>
<input type="submit">
</form>
</t:base>