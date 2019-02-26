<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:base>
	<jsp:body>
		<div class="container-fluid">
			<div class="row justify-content-center align-self-center">
				<div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3 h-100">

					<form method="post" action="singup">
						<div class="form-group">
							<label for="username">Usuario</label>
							<input type="text" class="form-control" name="username">
						</div>
						<div class="form-group">
							<label for="email">Email</label>
							<input type="text" class="form-control" name="email">
						</div>
						<div class="form-group">
							<label for="password1">Contrase&ntilde;a</label>
							<input type="password" class="form-control" name="password">
						</div>
						<div class="form-group">
							<label for="password2">Repetir Contrase&ntilde;a</label>
							<input type="password" class="form-control" name="password2">
						</div>
						<div class="form-group">
							<input class="btn btn-primary btn-lg btn-block" type="submit" value="Registrarse" />
					</form>
				</div>
			</div>
		</div>
	</jsp:body>
</t:base>