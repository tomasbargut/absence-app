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
			<div id="divSolicitudes">
				<h3>Listado de Solicitudes</h3>

				<div id="divTblSolicitudes">
					<div id="tblControls">
						<button id="btnActualizar" class="btn btn-primary" title="Actualizar">Actualizar Listado</button>
					</div>
					<table id="tblSolicitudes">
						<thead>
							<th>N°</th>
							<th>Servicio Solicitado</th>
							<th>Usuario</th>
							<th>Mensaje</th>
							<th>Fecha Tentativa</th>
							<th>Tu respuesta</th>
							<th>Estado de Solicitud</th>
							<th>Acciones</th>
						</thead>
						<tbody id="tblSolicitudesBody">
							<c:foreach items="${solicitudes}" var="solicitud">
								<tr id="${solicitud.requestID}">
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.requestID}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.serviceTitle}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.petitionerName}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.message}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.response}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">
										<button class="btn btn-info" title="Ver">Ver</button>
										<button class="btn btn-success" title="Aceptar">Aceptar</button>
										<button class="btn btn-danger" title="Rechazar">Rechazar</button>
									</td>
								</tr>
						</tbody>
					</table>
				</div>
		</c:if>
	</div>
</t:base>

<script type="text/javascript">
	var notificationNumber = 0;

	$(document).ready(function () {
		actualizarNotificaciones();

		//Sera reemplazado con tecnologia de webSockets
		setInterval(function () {
			actualizarNotificaciones();
		}, 15000);
	});

	function actualizarNotificaciones() {
		var action = "CARGAR_SOLICITUDES"

		var data = {
			ACTION: action
		};

		$.ajax({
			method: "POST",
			url: "${pageContext.request.contextPath}/contact",
			data: data,
			dataType: "json",
			success: function (res) {
				if (res.notificationNumber != 0) {
					//cargar dropdown lleno o redirigir a pagina
				} else {
					//cargar dropdown vacio?
				}
			},
			error: function (res) {
				alert("Error intentando recuperar notificaciones, intenta mas tarde.");
			}
		});
	}