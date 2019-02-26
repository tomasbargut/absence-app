<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>

	<div class="container-fluid">
		<section class="my-5">
			<div class="row justify-content-center align-self-center">
				<div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3 h-100">


					<h2 class="h1-responsive font-weight-bold text-center my-5">Ingresa tu Cuenta</h2>


					<form method="post" action="login">
						<div class="form-group">
							<label for="username">Usuario</label>
							<input type="text" class="form-control" name="username">
						</div>
						<div class="form-group">
							<label for="password">Contrase&ntilde;a</label>
							<input type="password" class="form-control" name="password">
						</div>
						<input class="btn btn-primary btn-lg btn-block" type="submit" value="Log in" />
					</form>
				</div>
			</div>
		</section>
	</div>

</t:base>