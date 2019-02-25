<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:base>

    <div class="container">
        <div class="row">
            <div class="col-sm-offset-1 col-sm-10"></div>


            <!-- Section: Blog v.3 -->
            <section class="my-5">

                <!-- Section heading -->
                <h2 class="h1-responsive font-weight-bold text-center my-5">Servicios Populares</h2>
                <!-- Section description -->
                <p class="text-center dark-grey-text w-responsive mx-auto mb-5">Estos son los servicios mejor valorados
                    de la
                    semana, hechales un vistazo!</p>

                <!-- Grid row -->
                <div class="row">

                    <!-- Grid column -->
                    <div class="col-lg-5 col-xl-4">

                        <!-- Featured image -->
                        <div class="view overlay rounded z-depth-1-half mb-lg-0 mb-4">
                            <img class="img-fluid" src="https://mdbootstrap.com/img/Photos/Others/images/49.jpg" alt="Sample image">
                            <a>
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>

                    </div>
                    <!-- Grid column -->

                    <!-- Grid column -->
                    <div class="col-lg-7 col-xl-8">

                        <!-- Post title -->
                        <h3 class="font-weight-bold mb-3"><strong>ELECTROFRIO Promo instalación</strong></h3>
                        <!-- Excerpt -->
                        <p class="dark-grey-text">Promo $ 3.000 todo en refriger. A/acond. Matriculado factura. c/gtía.
                            aceptamos tarjetas 154-624255</p>
                        <!-- Post data -->
                        <p>by <a class="font-weight-bold">Electrofrio</a>, 19/04/2018</p>
                        <!-- Read more button -->
                        <a class="btn btn-primary btn-md">Ver Mas</a>

                    </div>
                    <!-- Grid column -->

                </div>
                <!-- Grid row -->

                <hr class="my-5">

                <!-- Grid row -->
                <div class="row">

                    <!-- Grid column -->
                    <div class="col-lg-5 col-xl-4">

                        <!-- Featured image -->
                        <div class="view overlay rounded z-depth-1-half mb-lg-0 mb-4">
                            <img class="img-fluid" src="https://mdbootstrap.com/img/Photos/Others/images/31.jpg" alt="Sample image">
                            <a>
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>

                    </div>
                    <!-- Grid column -->

                    <!-- Grid column -->
                    <div class="col-lg-7 col-xl-8">

                        <!-- Post title -->
                        <h3 class="font-weight-bold mb-3"><strong>Talleres Dulcedani 2019</strong></h3>
                        <!-- Excerpt -->
                        <p class="dark-grey-text">TALLER de Patelería y Chocolatería Dulce Dany. Cursos para niños,
                            adolescentes y adultos. Cursos cortos y prácticos: Pastelería y chocolatería, Panadería y
                            pastelería, Pastelería para chicos. Inscripciones abiertas. T. 0342-154-443070.
                            face:dulcedany</p>
                        <!-- Post data -->
                        <p>by <a class="font-weight-bold">dulcedany</a>, 16/04/2019</p>
                        <!-- Read more button -->
                        <a class="btn btn-primary btn-md">Ver Mas</a>

                    </div>
                    <!-- Grid column -->

                </div>
                <!-- Grid row -->

                <hr class="my-5">

                <!-- Grid row -->
                <div class="row">

                    <!-- Grid column -->
                    <div class="col-lg-5 col-xl-4">

                        <!-- Featured image -->
                        <div class="view overlay rounded z-depth-1-half mb-lg-0 mb-4">
                            <img class="img-fluid" src="https://mdbootstrap.com/img/Photos/Others/images/52.jpg" alt="Sample image">
                            <a>
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>

                    </div>
                    <!-- Grid column -->

                    <!-- Grid column -->
                    <div class="col-lg-7 col-xl-8">

                        <!-- Post title -->
                        <h3 class="font-weight-bold mb-3"><strong>Apertura Sexologia Argent</strong></h3>
                        <!-- Excerpt -->
                        <p class="dark-grey-text">DR. NORBERTO DEL POZO Eyaculación precoz. Deseo. Impotencia.
                            Anorgasmia.
                            Ex-Pte. Federac. Sexológica Argent. Obispo Gelabert 2574. T. 156-155958.</p>
                        <!-- Post data -->
                        <p>by <a class="font-weight-bold">Dr Del Pozo</a>, 12/04/2018</p>
                        <!-- Read more button -->
                        <a class="btn btn-primary btn-md">Ver Mas</a>

                    </div>
                    <!-- Grid column -->

                </div>
                <!-- Grid row -->

            </section>
            <!-- Section: Blog v.3 -->

        </div>
    </div>






    <div id="testingDiv" class="container-fluid">
        <h2>Testeo</h2>
        <div id="divContact">
            <c:forEach items="${publications}" var="publication">
                <p id="estadoInicial"></p>
                <button id="openContactModal" type="button" class="btn btn-primary">
                    Contactar ${publication.getProvider().getUsername()}
                </button>
            </c:forEach>
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
                                                    <span id="chkShareHelpBlock" class="form-text text-muted">Aunque
                                                        no
                                                        compartas tus datos, los pondremos en contacto dentro del
                                                        sitio.</span>
                                                </div>
                                            </div>
                                        </form>
                                        <!--EndContactForm------------------------------------------------------------------>
                                    </c:when>
                                    <c:when test="${mode == 1}">
                                        <p style="word-wrap: break-word;">Estas seguro de que deseas cancelar
                                            solicitud
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
</script>