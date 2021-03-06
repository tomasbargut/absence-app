<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:base>
    <h1>Welcome to Absence</h1>
    <div id="testingDiv" class="container-fluid">
        <h2>Testeo</h2>
        <div id="divContact">
            <button id="openContactModal" type="button" class="btn btn-primary">Contactar Proveedor</button>
            <div id="divContactModal" class="modal fade" tabindex="-1" role="dialog" style="display: none">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h5 class="modal-title">
                                <!--Title set with jQuery.js script-->
                            </h5>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="control-label" for="inputDate">Para que fecha lo necesitas?</label>
                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="inputDate" placeholder="Fecha" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" class="compartirContacto" /> Compartir mis datos
                                                de contacto.
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <!-- Modal Footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                Volver
                            </button>
                            <button id="btnContactar" type="button" class="btn btn-primary accion">
                                Contactar
                            </button>
                            <button id="btnCancelar" type="button" class="btn btn-danger accion">
                                Cancelar Contacto
                            </button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:base>


<script type="text/javascript">
    var mode = true;

    //dinamiza el formato inicial del panel modal de contacto
    $("#openContactModal").click(function () {
        if (mode == true) {
            $("#modal-title").text("Contactar Proveedor");
            $("#btnContactar").show();
            $("#btnCancelar").hide();

        } else {
            $("#modal-title").text("Cancelar Contacto");
            $("#modal-body").text("Estas seguro de que deseas cancelar tu solicitud?");
            $("#btnContactar").hide();
            $("#btnCancelar").show();
        }
        $("#divContactModal").modal('show');
    });

    //dependiendo del formato inicial del panel modal valida si el usuario esta logueado, de estarlo solicita el contacto, caso contrario (transmuta modal) invita a loguearse y se cierra
    //luego se añadira reconocimiento dinamico de estado de esta solicitud, para que el boton SIEMPRE tenga el estado correcto para cada usuario solicitante
    $(".accion").click(function () {

        if ("${userID}" != null) {
            if (mode = true) {
                var provisionID = "${provisionID}";
                var userID = "${userID}";
                var fechaInicio = $("#inputDate").val;
                var telefono = ($('input.compartirContacto').is(':checked')) ? ("${telefono}") : (null)

                var data = {
                    provisionID: provisionID,
                    userID: userID,
                    fechaInicio: fechaInicio,
                    telefono: telefono
                };
                $.post("${pageContext.request.contextPath}/contacto/realizarContacto", data,
                    function (res) {
                        $("#table").html(res);
                        $("#nuevoNombreComun").val("");
                        $.unblockUI();
                    }
                );
            } else {
                var provisionID = "${provisionID}";
                var userID = "${userID}";
                //requestID?

                var data = {
                    provisionID: provisionID,
                    userID: userID,                    
                };
                $.post("${pageContext.request.contextPath}/contacto/cancelarContacto", data,
                    function (res) {
                        $("#table").html(res);
                        $("#nuevoNombreComun").val("");
                        $.unblockUI();
                    }
                );
            }
        } else {
            alert("No estas logueado (loguearse?)"); //TODO: Dar opcion loguearse via modal (o via home, lo que sea)
        }
        $("#divContactModal").modal('hide');
    });
</script>