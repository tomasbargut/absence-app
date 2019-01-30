<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/include.jsp"%>

<html>
	

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Solicitudes Recibidas</title>
</head>
<body>
	<div class="table" id="table">
		<table id="tblInformes" cellpadding="0" cellspacing="0" border="1"
			class="table table-striped table-condensed table-bordered">

			<tbody id="body">
				<c:forEach items="${provisionRequestList}" var="pR">
					<tr>
						<td><strong>${pR.user.profile.firstName}</strong> ha
							solicitado tus servicios de <strong>${pR.provision.providedService.description}</strong>
							<button id="btnVer" href="#fakelink"
								class="btn btn-block btn-lg btn-primary">Ver</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="tableButtons">
		<button id='btnClear' onclick='clearTable();'>Limpiar</button>
	</div>
</body>

<script type="text/javascript">
	function clearTable() {

	}
</script>

</html>