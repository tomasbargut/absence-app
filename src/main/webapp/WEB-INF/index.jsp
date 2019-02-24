<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:base>

    <h1>Welcome to Absence</h1>
    <form action="${pageContext.request.contextPath}/search">
        <input type="text" name="q">
        <input type="submit">
        <div id="categories">
            <c:forEach items="${categories}" var="category">
                <div id="${category.getCategoryID()}">
                    <input type="checkbox" id="${category.getCategoryID()}" name="categorias" value="${category.getCategoryID()}">
                    <label for="${category.getCategoryID()}">${category.getName()}</label>
                </div>
            </c:forEach>
        </div>
    <form> 
    <c:forEach items="${services}" var="service">
    <div id="divContact">
        <a href="${pageContext.request.contextPath}/service?${service.getServiceID()}">
            Contactar ${service.getProvider().getUsername()}
        </a>
    </div>
    </c:forEach>
    
</t:base>
<%-- <div id="divContactModal" class="modal fade" tabindex="-1" role="dialog" style="display: none">
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
</div> --%>

<%-- <script type="text/javascript">
    //0=contactar, 1=ver/cancelar contacto
    var mode = 0;
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
                alert(res.status);
            }
        });
    });
</script> --%>