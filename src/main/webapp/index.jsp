<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:base>
    <h1>Welcome to Absence</h1>
    <div id="testingDiv" class="container-fluid">
        <h2>Testeo</h2>
        <div id="contactProvider">
            <form action="http://localhost:8080/absence-app/Contact/contactProvider.jsp">
                <input type="submit" value="Probar Contactar" />
            </form>
        </div>
    </div>
</t:base>