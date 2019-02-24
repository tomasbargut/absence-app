<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>

    <a href="admin/category">Añadir categorias</a>
    <div class="divCategories-container" style="text-align: center">
        <div id="divCategories" style="display: inline-block;">

            <h3>Listado de Categorias Registradas</h3>

            <div id="divTblCategories">
                <div id="tblControls">
                    <button id="btnActualizar" class="btn btn-primary" title="Actualizar">Actualizar</button>
                </div>
                <table id="tblCategories" class="table table-hover table-dark">
                    <thead>
                        <tr>
                            <th scope="col">N°</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="tblCategoriesBody">
                        <c:forEach items="${categories}" var="categoria">
                            <tr id="${categoria.categoryID}" scope="row">
                                <td class="text-center" style="width:5%; white-space:nowrap;">${categoria.categoryID}</td>
                                <td class="text-center" style="width:5%; white-space:nowrap;">${categoria.name}</td>
                                <td class="text-center" style="width:5%; white-space:nowrap;">${categoria.desc}</td>
                                <td class="text-center" style="width:5%; white-space:nowrap;">
                                    <button class="btn btn-info" title="Detalles">Detalles</button>
                                    <button class="btn btn-primary" title="Modificar">Modificar</button>
                                    <button class="btn btn-danger" title="Eliminar">Eliminar</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</t:base>