<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<div class="container-fluid">
		<section class="my-5">
			<div class="row justify-content-center align-self-center">
				<div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3 h-100">


					<h2 class="h1-responsive font-weight-bold text-center my-5">Ingresa el Servicio que quieres Brindar</h2>


					<form method="post">
						<label for="title">Titulo</label>
						<input type="text" name="title">
						<label for="desc">Descripcion</label>
						<input type="text" name="desc">
						<input type="submit" value="Añadir" />
						<c:if test="${categories != null }">
							<c:forEach items="${categories}" var="category">
								<div id="${category.getCategoryID()}">
									<input type="checkbox" id="${category.getCategoryID()}" name="categories" value="${category.getCategoryID()}">
									<label for="${category.getCategoryID()}">${category.getName()}</label>
								</div>
							</c:forEach>
						</c:if>
					</form>
				</div>
			</div>
		</section>
	</div>

</t:base>