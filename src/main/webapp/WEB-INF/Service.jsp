<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
    ${service.getTitle()}
    ${service.getDesc()}
    ${service.getProvider().getFullname()}
    <form action="${pageContext.request.contextPath}/request" method="POST">
        <input type="text" name="message">
        <input type="submit" value="Contactar">
    </form>
</t:base>