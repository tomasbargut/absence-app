<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<form method="post">
		<label for="title">Titulo</label>
		<input type="text" name="title" value="${service.getTitle()}">
		<label for="desc">Descripcion</label>
        <input type="text" name="desc" value="${service.getDesc()}">
		<input type = "submit" value = "Añadir"/>
		<c:if test="${categories != null }">
			<c:forEach items="${categories}" var="category">
				<c:if test="${service != null}">
					<c:forEach items="${service.getCategories()}" var="cat">
						<c:if test="${cat.getCategoryID() == category.getCategoryID()}">
							<div id="${category.getCategoryID()}">
								<input type="checkbox" id="${category.getCategoryID()}" name="categories" value="${category.getCategoryID()}" checked>
								<label for="${category.getCategoryID()}">${category.getName()}</label>
							</div>
						</c:if>
					</c:forEach>
					<div id="${category.getCategoryID()}">
						<input type="checkbox" id="${category.getCategoryID()}" name="categories" value="${category.getCategoryID()}">
						<label for="${category.getCategoryID()}">${category.getName()}</label>
					</div>
				</c:if>
				<c:if test="${service == null}">
				<div id="${category.getCategoryID()}">
					<input type="checkbox" id="${category.getCategoryID()}" name="categories" value="${category.getCategoryID()}">
					<label for="${category.getCategoryID()}">${category.getName()}</label>
				</div>
				</c:if>
			</c:forEach>
		</c:if>
	</form>
</t:base>