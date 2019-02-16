<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<form method="post">
		<label for="title">Titulo</label>
		<input type="text" name="title">
		<label for="desc">Descripcion</label>
        <input type="text" name="desc">
		<input type = "submit" value = "Añadir"/>
		<c:if test="${categories != null }">
			<c:forEach items="${categories}" var="category">
				<div id="${category.getCategoryID()}">
					<input type="checkbox" id="${category.getCategoryID()}" name="categories" value="${category.getCategoryID()}">
					<label for="${category.getCategoryID()}">${category.getTitle()}</label>
				</div>
			</c:forEach>
		</c:if>
	</form>
</t:base>