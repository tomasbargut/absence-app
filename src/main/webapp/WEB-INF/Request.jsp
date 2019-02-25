<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
${peticion.getRequestDate()}
${peticion.getMessage()}
${peticion.getResponse()}
<c:if test="${provider == null}">
    <c:if test="${report == null}">
    <a href="${pageContext.request.contextPath}/report">Reportar</a>
    </c:if>
    <c:if test="${review == null}">
    <a href="${pageContext.request.contextPath}/review">Review</a>
    </c:if>
</c:if>
<c:if test="${provider != null}">
    <c:if test="${peticion.getResponse() == null}">
        <form action="${pageContext.request.contextPath}/request?${peticion.getRequestID()}" method="POST">
            <input type="text">
            <input type="submit" values="response">
        </form>
    </c:if>
</c:if>
<c:if test="${report != null}">
<h3>${report.getTitle()}<h3>
<p>${report.getDesc()}</p>
<ul>
    <li>${report.getSentDate()}</li>
    <li>${report.getStatus()}</li>
<ul>
</c:if>
<c:if test="${review != null}">
<h3>${review.getTitle()}<h3>
<p>${review.getDesc()}</p>
${review.getPointsGiven()}
${review.getReviewDate()}
</c:if>
</t:base>

