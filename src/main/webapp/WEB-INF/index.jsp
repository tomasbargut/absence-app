<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:base>
    <div class="container">
        <form action="${pageContext.request.contextPath}/search" method="POST">
            <input type="text" name="q">
            <input type="submit">
            <div id="categories">
                <c:forEach items="${categories}" var="category">
                    <div id="${category.getCategoryID()}">
                        <input type="checkbox" id="${category.getCategoryID()}" name="categorias" 
                                value="${category.getCategoryID()}">
                        <label for="${category.getCategoryID()}">${category.getName()}</label>
                    </div>
                </c:forEach>
            </div>
        <form> 
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
                <c:forEach items="${services}" var="service">
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
                        <h3 class="font-weight-bold mb-3"><strong>${service.getTitle()}</strong></h3>
                        <!-- Excerpt -->
                        <p class="dark-grey-text">${service.getDesc()}</p>
                        <!-- Post data -->
                        <p>by <a class="font-weight-bold">${service.getProvider().getUsername()}</a>, 19/04/2018</p>
                        <!-- Read more button -->
                        <a href="${pageContext.request.contextPath}/service?${service.getServiceID()}" 
                            class="btn btn-primary btn-md">Ver Mas</a>

                    </div>
                    <!-- Grid column -->

                </div>
                <!-- Grid row -->

                <hr class="my-5">
                </c:forEach>
            </section>
            <!-- Section: Blog v.3 -->

        </div>
    </div>
</t:base>
