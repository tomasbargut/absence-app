<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page session = "true" %>

<t:base>
	<form method="post" action="contactProvider">
	          <label for="message">Mensaje</label>
	          <input type="text" name="message">
	          <!--
	          <label for="sharePersInfo">Compartir mis datos de contacto:</label>
	          <input type="checkbox" name="sharePersInfo"> 
	          -->
	          <!-- Auto retrieve of user session attributes via JSTL -->
	          <input type="text" value="${user.username}" />
	          <input type="text" value="${user.email}" />
	          <input type="text" value="${user.tel}"/>
	          
	          <!-- SHOULD SEND PROVISION/PROVIDER/SERVICE DATA TOO? -->
	          
	          <input type = "submit" value = "Contactar" />
    	</form>
</t:base>