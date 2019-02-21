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
									<td class="text-center" style="width:5%; white-space:nowrap; text-align:center;">${solicitud.requestID}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.service.serviceTitle}</td>
									<td class="text-center" style="width:5%; white-space:nowrap;">${solicitud.petitioner.username}</td>
									<td class="text-center" style="width:10%; white-space:nowrap;">${solicitud.message}</td>
									<td class="text-center" style="width:5%; white-space:nowrap; text-align:center;">${solicitud.requestDate}</td>
									<td class="text-center" style="width:10%; white-space:nowrap;">${solicitud.response}</td>
									<td class="text-center" style="width:5%; white-space:nowrap; text-align:center;">${solicitud.status}</td>
									<td class="text-center" style="width:10%; white-space:nowrap;">
										<button class="btn btn-info accion" title="Ver">Ver</button>
										<button class="btn btn-success accion" title="Aceptar">Aceptar</button>
										<button class="btn btn-danger accion" title="Rechazar">Rechazar</button>
									</td>
								</tr>
							</c:foreach>
						</tbody>
					</table>
				</div>
		</c:if>
	</div>
</t:base>

<script type="text/javascript">

	$(document).ready(function () {
		actualizarTablaSolicitudes();
	});

	$(".accion").click(
		//TODO: Acciones
		alert("PLACEHOLDER: Boton presionado!")
	);

	function actualizarTablaSolicitudes() {
		var action = "CARGAR_NOTIFICACIONES"
		var data = {
			ACTION: action
		};

		$.ajax({
			method: "POST",
			url: "${pageContext.request.contextPath}/contact",
			data: data,
			success: function () {},
			error: function () {
				alert("Error intentando recuperar solicitudes, intenta mas tarde.");
			}
		});
	}