<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<c:if test="${success != null}">
		${success}
	</c:if>
	<c:if test="${provider == null }">
		<a href="provider">Ser proveedor</a>
	</c:if>
	<c:if test="${provider != null }">
		<a href="service">Añadir servicios</a>
		<c:forEach items="${provider.getServices()}" var="service">
  			<a href="${pageContext.request.contextPath}/service/${service.getServiceID()}">${service.getTitle()}</a>
 		</c:forEach>
	</c:if>
</t:base>