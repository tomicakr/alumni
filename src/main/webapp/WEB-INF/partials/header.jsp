<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="userInSession" property="principal" />

<div class="ui top menu">
    <a href="/" class="item">
        <h1>Alumni</h1>
    </a>

    <sec:authorize access="isAnonymous()">
        <a href="/login" class="item">Prijava</a>
        <a href="/register" class="item">Registracija</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a class="item" href="${pageContext.request.contextPath}/users/${userInSession.userId}">
            Dobrodošli, ${userInSession.firstName}! </a>

        <a class="item right" href="${pageContext.request.contextPath}/logout">
            Odjava </a>
    </sec:authorize>
</div>