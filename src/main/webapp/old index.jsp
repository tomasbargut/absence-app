<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:base>
    <h1>Welcome to Absence</h1>
    <div id="testingDiv" class="container-fluid">
        <h2>Testeo</h2>
        <div id="divContact">

            <!--Author notice: This HTML is only for testing purposes, it will be reformatted to a cleaner version later-->

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
                            <form id="formContacto" class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="control-label" for="inputMessage">Quieres contarme de que necesitas
                                        que haga?</label>
                                    <div class="col-sm-12">
                                        <input type="textarea" maxlength="120" class="form-control" id="inputMessage"
                                            placeholder="Tu mensaje aqui." />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="inputDate">Para que fecha me necesitas?</label>
                                    <div class="col-sm-12">
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
                            <div id="divContactoExitoso" style="display: none">
                                <h6>Hemos enviado tu mensaje!</h6>
                                <row class="rowComprobanteContacto">
                                    <h5>Solicitud Nro:</h5>
                                    <p id="pNroSolicitud"></p>
                                    <strong>Para:</strong>
                                    <p id="pReqProvider"></p>
                                    <strong>Tu Mensaje</strong>
                                    <p id="pReqMessage"></p>
                                </row>
                            </div>
                        </div>
                        <!-- Modal Footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                Volver
                            </button>
                            <button id="btnContactar" type="button" class="btn btn-primary accion">
                                Contactame
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

        if ("${user}" != null) {
            if (mode = true) {
                var publicationID = "${publicationID}";
                var fechaInicio = $("#inputDate").val;
                var mensajeDeContacto = $("#inputMessage").val;
                var telefono = ($('input.compartirContacto').is(':checked')) ? ("${telefono}") : (null)

                var data = {
                    publicationID: publicationID,
                    fechaInicio: fechaInicio,
                    telefono: telefono
                };
                //solicitud de proveeduria y formatos post-solicitud del panel modal
                $.post("${pageContext.request.contextPath}/contacto/realizarContacto", data,
                        function (req) {}
                    ).done(function () {
                        $("#modal-title").text("Contacto Realizado!");
                        $("#modal-body").empty().replaceWith("#divContactoExitoso").show();;
                        $("#pNroSolicitud").text("${req.requestID}");
                        $("#pReqProvider").text("${req.providerFullName}");
                        $("#pReqMessage").text("${req.requestMessage}");
                        $("#btnContactar").hide();
                        $("#btnCancelar").hide();
                    })
                    .fail(function () {
                        $("#modal-title").text("Ocurrio un Error");
                        $("#modal-body").text(
                            "Algo ha salido mal, por favor reintenta mas tarde o contacta a un administrador (link/post with stacktrace)."
                        );
                        $("#btnContactar").hide();
                        $("#btnCancelar").hide();
                    })
                    .always(function () {
                        $("#divContactModal").modal('show');
                    });


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