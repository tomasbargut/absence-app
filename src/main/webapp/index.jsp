<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:base>
    <h1>Welcome to Absence</h1>
    <div id="testingDiv" class="container-fluid">
        <h2>Testeo</h2>
        <div id="contactProvider">
            <button id="openContactModal" type="button" class="btn btn-primary" onclick="openContactModal()">Primary</button> 
            <div class="divContactModal"></div>
        </div>
        
    </div>
</t:base>


<script type="text/javascript">
	function openContactModal(){
        var mode = "contactar"; //button behavior will change if provider was already contacted and user wants to cancel/delete request
		
        var url = "Contact/contactProviderModal.jsp";//better practice involves making a constants lists, and using the const name instead of actual urls (more secure)		
        
        $("#divContactModal").load(url, {var:mode});  //POST with one param "mode" that replaces divContactModal with the actual modal on set mode (contactar/cancelar)
        // $("#divContactModal").load("Contact/contactProviderModal.jsp?var=mode"); //GET variation
    };
</script>