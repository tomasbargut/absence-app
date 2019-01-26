<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<c:if test="${provider == null }">
		<a href="provider">Ser proveedor</a>
	</c:if>
	<c:if test="${provider != null }">
		<a href="services">Añadir servicios</a>
	</c:if>
</t:base>