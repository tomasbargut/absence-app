<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
<label for="title">Titulo</label>
<input type="text" name="title">
<label for="desc">Descripcion</label>
<input type="text" name="desc">
<t:if test="${administrator != null}">
<label for="status">Estado</label>
<input type="radio" name="status" values="APROBADA">
<input type="radio" name="status" values="REACHAZADA">
</t:if>
</t:base>