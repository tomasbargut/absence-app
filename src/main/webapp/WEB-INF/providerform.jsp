<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>

    <div class="container-fluid">
        <section class="mt-3">
            <div class="row justify-content-center align-self-center">
                <div class="col-sm-offset-1 col-sm-10">


                    <h2 class="h1-responsive font-weight-bold text-center my-5">Registro de Proveedor</h2>
                    <p class="text-center dark-grey-text w-responsive mx-auto mb-5">No mostraremos ninguno de tus datos sin tu consentimiento previo.</p>

                    <form action="provider" method="post">
                        <label for="fullname">Nombre completo</label>
                        <input type="text" name="fullname">
                        <label for="telephone">Telefono</label>
                        <input type="phone" name="telephone">
                        <label for="postalCode">Codigo Postal</label>
                        <input type="text" name="postalCode">
                        <label for="street">Domicilio</label>
                        <input type="text" name="street">
                        <label for="birthDate">Fecha de nacimiento</label>
                        <input type="date" name="birthDate">
                        <input type="submit" value="Ser proveedor" />
                    </form>
                </div>
            </div>
        </section>
    </div>

</t:base>