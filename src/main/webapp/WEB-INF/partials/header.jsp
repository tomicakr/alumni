<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="userInSession" property="principal"/>

<header>
    <nav>
        <div class="ui massive stackable secondary menu" id="top-menu">
            <a class="item" id="home" name="Home" href="/"> Naslovnica </a>
            <a class="item" id="services" name="Usluge"> Usluge </a>

            <sec:authorize access="isAuthenticated()">

                <c:if test="${userInSession.roles[0].equals(\"ROLE_ZAPOSLENIK\")}">
                    <a class="item" name="Jobs"
                       href="${pageContext.request.contextPath}/jobs"> Poslovi </a>
                </c:if>

                <c:if test="${userInSession.roles[0].equals(\"ROLE_ADMINISTRATOR\")}">
                    <a class="item" name="Jobs"
                       href="${pageContext.request.contextPath}/jobs"> Poslovi </a>
                    <a class="item" name="Jobs"
                       href="${pageContext.request.contextPath}/users"> Korisnici </a>
                </c:if>

            </sec:authorize>


            <div class="right menu">
                <sec:authorize access="isAnonymous()">
                    <a class="item" name="Login"
                       href="${pageContext.request.contextPath}/sessions/new"> Prijava
                    </a>
                    <a class="item" name="singup"
                       href="${pageContext.request.contextPath}/users/new">
                        Registracija </a>
                </sec:authorize>


                <sec:authorize access="isAuthenticated()">
                    <a class=" item"
                       href="${pageContext.request.contextPath}/users/${userInSession.userId}">
                        Dobrodošli, ${userInSession.firstName}! </a>

                    <form method="post"
                          action="${pageContext.request.contextPath}/sessions/">
                        <input class="item" type="hidden" name="_method" value="DELETE">
                        <input class="item" type="submit" value="Odjavi se">
                    </form>
                </sec:authorize>


            </div>
        </div>
    </nav>
</header>
<div id="service-list"
     class="ui small basic test modal transition hidden"
     style="font-size: 2em">
    <div class="ui center aligned massive header">Popis usluga</div>
    <div class="scrolling content">
        <div class="ui inverted celled middle aligned list">
        </div>
    </div>
    <div class="ui actions">
        <div class="ui huge ok inverted button">
            <i class="checkmark icon"></i> Izađi
        </div>
    </div>
</div>
<script src="/scripts/services.js"></script>
