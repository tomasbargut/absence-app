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
                <!-- Modal -->

                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="contactModalLabel">Contacto</h4>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid" style="height: 100%; width:100%;">
                                <c:choose>
                                    <c:when test="${mode == 0}">
                                        <!--StartContactForm------------------------------------------------------------------>
                                        <form>
                                            <div class="form-group">
                                                <label for="fechaInicio">Fecha tentativa de comienzo del trabajo</label>
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <div class="input-group-text">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                    </div>
                                                    <input id="fechaInicio" name="fechaInicio" placeholder="dd-mm-aaaa"
                                                        type="date" aria-describedby="fechaInicioHelpBlock" required="required"
                                                        class="form-control">
                                                </div>
                                                <span id="fechaInicioHelpBlock" class="form-text text-muted">Esto
                                                    ayudara al
                                                    proveedor
                                                    del servicio a responderte con mayor certidumbre.</span>
                                            </div>
                                            <div class="form-group">
                                                <label for="message">Mensaje</label>
                                                <textarea id="message" name="message" cols="40" rows="5"
                                                    aria-describedby="messageHelpBlock" class="form-control"></textarea>
                                                <span id="messageHelpBlock" class="form-text text-muted">Ej.: Que
                                                    necesitas que
                                                    se
                                                    haga?</span>
                                            </div>
                                            <div class="form-group">
                                                <label>Que datos de contacto deseas compartir?</label>
                                                <div>
                                                    <div class="custom-control custom-checkbox custom-control-inline">
                                                        <input name="chkShare" id="chkShare_0" type="checkbox"
                                                            aria-describedby="chkShareHelpBlock" class="custom-control-input"
                                                            value="compartirDatos">
                                                        <label for="chkShare_0" class="custom-control-label">Compartir
                                                            datos de contacto</label>
                                                    </div>
                                                    <span id="chkShareHelpBlock" class="form-text text-muted">Aunque no
                                                        compartas tus datos, los pondremos en contacto dentro del
                                                        sitio.</span>
                                                </div>
                                            </div>
                                        </form>
                                        <!--EndContactForm------------------------------------------------------------------>
                                    </c:when>
                                    <c:when test="${mode == 1}">
                                        <p style="word-wrap: break-word;">Estas seguro de que deseas cancelar solicitud
                                            de
                                            contacto?</p>>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-link" data-dismiss="modal">Atras</button>
                            <c:choose>
                                <c:when test="${mode == 0}">
                                    <button type="button" class="btn btn-success accion" data-dismiss="modal">Contactar</button>
                                </c:when>
                                <c:when test="${mode == 1}">
                                    <button type="button" class="btn btn-danger accion" data-dismiss="modal">Cancelar
                                        Contacto</button>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

</t:base>


<script type="text/javascript">
    //0=contactar, 1=ver/cancelar contacto
    var mode = 0;

    /* 
     @Pirchi:
     Habilita alguna de las de aca abajo, la primera obtiene el valor de la publicacion si se la asignaste a algun elemento, reemplaza por el ID del elemento.
     La segunda se la trae del "modelo de datos de la pagina" o en otras palabras, de algun resultado de un jquery.post/get
     Si necesitas saber mas https://stackoverflow.com/questions/17957933/how-to-get-a-jquery-variable-value
     */
    //var publicationID = $("#publicationID").val();
    //var publicationID = "${publicationID}";

    var publicationID = "000000001000000001";
    var action = "VERIFICAR_CONTACTO";

    var data = {
        publicationID: publicationID,
        ACTION: action
    };

    //dinamiza el formato inicial del boton que lanza el modal

    $.ajax({
        method: "POST",
        url: "${pageContext.request.contextPath}/contact",
        data: data,
        dataType: "json",
        success: function (res) {
            console.log(res);
            if (res.requestID != 0) {
                $("#estadoInicial").text("Contacto realizado el: " + res.requestDate);
                $("#openContactModal").text("Ver Estado");
                mode = 1;
            } else {
                $("#estadoInicial").text("Puedes contactar!");
                $("#openContactModal").text("Contactar");
                mode = 0;
            }
        },
        error: function (res) {
            alert("Error intentando recuperar datos, intenta mas tarde.");
        }
    });

    //dinamiza el formato inicial del panel modal de contacto
    $("#openContactModal").click(function () {
        if (mode == 0) {
            $(".modal-title").text("Contactar Proveedor");
        } else {
            $(".modal-title").text("Cancelar Contacto");
        }
        $("#divContactModal").modal('show');
    });

    $(".accion").click(function () {
        $("#divContactModal").modal('hide');
        if (mode == 0) {
            action == SOLICITAR_CONTACTO;
            var fechaInicio = $("#inputDate").val;
            var mensajeDeContacto = $("#inputMessage").val;
            var telefono = ($('input.compartirContacto').is(':checked')) ? ("${telefono}") : (null);

        } else {
            action == CANCELAR_CONTACTO;
        }

        var data = {
            publicationID: publicationID,
            ACTION: action,
            fechaInicio: fechaInicio,
            mensaje: mensajeDeContacto,
            telefono: telefono
        };

        $.ajax({
            method: "POST",
            url: "${pageContext.request.contextPath}/contact",
            data: data,
            dataType: "json",
            success: function (res) {
                var resultado = "${res.resultado}";
                setTimeout(function () {
                    if (resultado != "") {
                        //reemplazar con modal modificado y cambio de boton
                        alert("solicitud realizada con exito!");
                        mode = 1;
                    } else {
                        //reemplazar con modal modificado y cambio de boton
                        alert("solicitud eliminada con exito!");
                        mode = 0;
                    }
                    //recargar pagina?
                }, 500);

            }
        });
    });
</script>