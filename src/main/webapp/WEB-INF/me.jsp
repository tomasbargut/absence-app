<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8"></div>

			<h1 class="h1-responsive font-weight-bold text-center my-5">Dashboard</h1>
			<c:if test="${success != null}">
				${success}
			</c:if>
			<div id="divUsuario">
				<c:if test="${provider == null }">
					<h2 class="h2-responsive font-weight-bold text-center my-5"></h2>Mis Datos</h2>
					<p>PLACEHOLDER: Form ABM Datos Usuario/Proveedor</p>
					<a href="provider" class="btn btn-info btn-sm ver" role="button">Ser proveedor</a>
				</c:if>
			</div>
		</div>
	</div>
	<div id="divProveedor">
		<c:if test="${provider != null }">
			<h2 class="h2-responsive font-weight-bold text-center my-5">Mi Portfolio</h2>
			<div id="divServicios">
				<h3 class="h3-responsive text-center my-5">Mis Servicios</h3>

				<a href="service"></a>
				<h4 class="h4-responsive text-center my-5">Añadir Servicios</h4>
				</a>
				<table id="tblServicios" class="table table-hover table-dark">
					<thead>
						<tr>
							<th scope="col">Servicios que prestas</th>
							<th scope="col">Acciones</th>
						</tr>
					</thead>
					<tbody id="tblServiciosBody">
						<c:forEach items="${services}" var="service">
							<tr scope="row">
								<td class="text-center" style="width:5%; white-space:nowrap;">
									<a href="${pageContext.request.contextPath}/service?${service.serviceID}" class="text-white">${service.title}
									</a>
								</td>
								<td class="text-center" style="width:5%; white-space:nowrap;">
									<button class="btn btn-info btn-sm ver" title="Ver">Detalle</button>
									<c:if test="${solicitud.status == 'solicitado'}">
										<button class="btn btn-primary btn-sm aceptar" title="Aceptar">Modificar</button>
										<button class="btn btn-danger btn-sm rechazar" title="Rechazar">Eliminar</button>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="divSolicitudes" class="divSolicitudes-container" style="text-align: center">
				<h3 class="h3-responsive text-center my-5">Listado de Solicitudes</h3>

				<div id="divTblSolicitudes">
					<div id="tblControls">
						<button id="btnActualizar" class="btn btn-primary" title="Actualizar">Actualizar Listado</button>
					</div>
					<table id="tblSolicitudes" class="table table-hover table-dark">
						<thead>
							<tr>
								<th scope="col">N°</th>
								<th scope="col">Servicio Solicitado</th>
								<th scope="col">Usuario</th>
								<th scope="col">Mensaje</th>
								<th scope="col">Fecha Tentativa</th>
								<th scope="col">Tu respuesta</th>
								<th scope="col">Estado de Solicitud</th>
								<th scope="col">Acciones</th>
							</tr>
						</thead>
						<tbody id="tblSolicitudesBody">
							<c:forEach items="${solicitudes}" var="solicitud">
								<tr id="${solicitud.requestID}" scope="row">
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.requestID}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.service.title}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.petitioner.username}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.message}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.requestDate}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.response}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.status}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">
										<a href="${pageContext.request.contextPath}/request?${solicitud.requestID}" class="btn btn-info btn-sm ver"
										 role="button" title="Ver">Ver</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
		</c:if>
	</div>
</t:base>

<script type="text/javascript">
	/*
		function actualizarTablaSolicitudes() {
			var action = "CARGAR_SOLICITUDES"

			var data = {
				ACTION: action
			};

			$.ajax({
				method: "POST",
				url: "${pageContext.request.contextPath}/contact",
				data: data,
				dataType: "json",
				success: function (res) {},
				error: function (res) {
					alert("Error intentando recuperar solicitudes, intenta mas tarde.");
				}
			});
		}
		*/
</script>