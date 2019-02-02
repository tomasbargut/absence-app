<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:base>
    <div id="contactModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <!--Title set with jQuery.js script-->
                    </h5>
                </div>
                <div class="modal-body">
                    <!--Body set with jQuery.js script-->
                </div>
                <div class="modal-footer">
                    <!--Footer set with JSTL ("inline script")-->
                    <c:choose>
                        <c:when test="">
                            test="${mode == 'contactar'}">
                            <button type="button" class="btn btn-primary">Contactar Proveedor</button>
                        </c:when>
                        <c:when test="">
                            test="${mode == 'cancelar'}">
                            <button type="button" class="btn btn-danger">Cancelar Contacto</button>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</t:base>

<!-- jQuery.js function executed when modal is loaded, configuring its content dynamically-->
<script type="text/javascript">
    var mode = "${mode}";
    if (mode == "contactar") {
        $("#modal-title").text("Contactar Proveedor");
        $("#modal-body").text(
            "//Info y opciones contacto (fecha tentativa inicio/fin, tareas, consulta, compartir datos, etc) con append(html)"
        ); //TODO
        $("#contactModal").show();
    } else {
        $("#modal-title").text("Cancelar Contacto");
        $("#modal-body").text("Estas seguro de que deseas cancelar tu solicitud?");
        $("#contactModal").show();
    }
</script>