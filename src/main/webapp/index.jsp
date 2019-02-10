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

            <p id="estadoInicial"></p> <button id="openContactModal" type="button" class="btn btn-primary">Contactar
                Proveedor</button>
            <div id="divContactModal" class="modal fade" tabindex="-1" role="dialog" style="display: none">

            </div>
        </div>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="contactModal" tabindex="-1" role="dialog" aria-labelledby="contactModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="contactModalLabel">Contacto</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid" style="height: 100%; width:100%;">
                        <p style="word-wrap: break-word;">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-link" onclick='' data-dismiss="modal">Atras</button>
                    <c:choose>
                        <c:when test="${mode == 0}">
                            <button type="button" class="btn btn-success" onclick='' data-dismiss="modal">Contactar</button>
                        </c:when>
                        <c:when test="${mode == 1}">
                            <button type="button" class="btn btn-danger" onclick='' data-dismiss="modal">Cancelar
                                Contacto</button>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

</t:base>


<script type="text/javascript">
    /* 
    @Pirchi:
        Habilita alguna de las de aca abajo, la primera obtiene el valor de la publicacion si se la asignaste a algun elemento, reemplaza por el ID del elemento.
        La segunda se la trae del "modelo de datos de la pagina" o en otras palabras, de algun resultado de un jquery.post/get
        Si necesitas saber mas https://stackoverflow.com/questions/17957933/how-to-get-a-jquery-variable-value
    */
    //var publicationID = $("#publicationID").val();
    //var publicationID = "${publicationID}";
    if ("${user != null}") {
        var publicationID = "${publicationID}";
        var action = "VERIFICAR_CONTACTO"

        var data = {
            publicationID: publicationID,
            ACTION: action
        };

        $.post("${pageContext.request.contextPath}/contact", data,
                function (req) {}
            ).done(function () {
                if ("${req.solicitud != null || req.solicitud != ''}") {
                    $("#estadoInicial").text("Contacto realizado el: " + "${req.solicitud.requestDate}");
                    $("#openContactModal").text("Ver Estado");
                } else {
                    $("#estadoInicial").text("Puedes contactar!");
                    $("#openContactModal").text("Contactar");
                }
            })
            .fail(function () {
                alert("Error intentando recuperar datos, intenta mas tarde.");
            });
        
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
    } else {
        $("#estadoInicial").text("Debes ingresar para contactar");
        $("#openContactModal").text("Ingresar");
        //TODO: levantar modal/redireccion a login.
    }
</script>