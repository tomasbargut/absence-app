<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
<form method="POST">
    <label for="title">Titulo</label>
    <input type="text" name="title">
    <label for="desc">Descripcion</label>
    <input type="text" name="desc">
    <label for="pointsGiven">Puntaje dado</label>
    <input type="radio" name="pointsGiven" value="1">
    <input type="radio" name="pointsGiven" value="2">
    <input type="radio" name="pointsGiven" value="3">
    <input type="radio" name="pointsGiven" value="4">
    <input type="radio" name="pointsGiven" value="5">
    <input type="submit">
</form>
</t:base>