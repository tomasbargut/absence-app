<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
	<h1>Dashboard</h1>
	<c:if test="${success != null}">
		${success}
	</c:if>
	<div id="divUsuario">
		<c:if test="${provider == null }">
			<h2>Mis Datos</h2>
			<p>PLACEHOLDER: Form ABM Datos Usuario/Proveedor</p>
			<a href="provider">Ser proveedor</a>
		</c:if>
	</div>
	<div id="divProveedor">
		<c:if test="${provider != null }">
			<h2>Mi Portfolio</h2>
			<div id="divServicios">
				<h3>Mis Servicios</h3>
				<a href="service">Añadir Servicios</a>
				<c:forEach items="${provider.getServices()}" var="service">
					<a href="${pageContext.request.contextPath}/service/${service.getServiceID()}">${service.getTitle()}</a>
				</c:forEach>
			</div>
			<div id="divSolicitudes" class="divSolicitudes-container" style="text-align: center">
				<h3>Listado de Solicitudes</h3>

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
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.service.serviceTitle}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.petitioner.petitionerName}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.message}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.requestDate}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.response}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.status}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">
										<button class="btn btn-info ver" title="Ver">Ver</button>
										<c:if test="${solicitud.status == 'solicitado'}">
											<button class="btn btn-success aceptar" title="Aceptar">Aceptar</button>
											<button class="btn btn-danger rechazar" title="Rechazar">Rechazar</button>
										</c:if>
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
	$(document).ready(function () {
		actualizarTablaSolicitudes();

		//Sera reemplazado con tecnologia de webSockets
		setInterval(function () {
			actualizarTablaSolicitudes();
		}, 15000);
	});
	*/

	$(".ver").click(function () {
		alert("Mostrando modal con datos completos de la solicitud y ejecutar ajax status->visto");
	});
	$(".aceptar").click(function () {
		alert("Mostrar confirmacion de aceptacion de solicitud, y ejecutar ajax status->respondida");
	});
	$(".borrar").click(function () {
		alert("Mostrar confirmacion de rechazo de solicitud, y ejecutar ajax status->cancelado");
	});

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