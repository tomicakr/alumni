<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp" flush="true">
    <jsp:param name="title" value="Uredi profil" />
    <jsp:param name="view-name" value="editUser" />
</jsp:include>
<body>
<main class="ui middle aligned center aligned grid">
    <div class="column">
        <h1 class="ui image massive header">
            Promijenite svoje podatke
        </h1>
        <form:form action="/users/" method="post" class="ui large form segment stacked" modelAttribute="user">
            <div class="two fields">
                <div class="field">
                    <input type="text" name="name" id="first-name" disabled placeholder="Ime" value="${user.name}">
                </div>
                <div class="field">
                    <input type="text" name="surname" id="last-name" disabled placeholder="Prezime" value="${user.surname}">
                </div>
            </div>
            <div class="field">
                <input type="text" name="userPid" id="oib" placeholder="OIB" disabled value="${user.userPid}">
            </div>
            <div class="field">
                <input type="tel" name="mobilePhone" id="mobile-phone" placeholder="Broj mobitel" value="${user.mobilePhone}">
            </div>
            <div class="field">
                <input type="tel" name="phone" id="telephone" placeholder="Broj telefona" disabled value="${user.phone}">
            </div>
            <div class="field">
                <input type="email" name="email" id="email" placeholder="Adresa elektroničke pošte" disabled value="${user.email}">
            </div>
            <div class="field">
                <select name="location" title="">
                    <c:forEach var="location" items="${locations}">
                        <option value="${location.id}" selected>${location.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="field">
                <input type="text" name="address" id="address" placeholder="Adresa stanovanja" disabled value="${user.address}">
            </div>
            <div class="ui accordion field">
                <div class="ui left floated title">
                    <i class="icon dropdown"></i>
                    Promijenite lozinku
                </div>
                <div class="content field">
                    <div class="field">
                        <input type="password" name="old-password" id="old-password" placeholder="Unesite staru lozinku">
                    </div>
                    <div class="field">
                        <input type="password" name="password" id="password" placeholder="Unesite novu lozinku">
                    </div>
                    <div class="field">
                        <input type="password" name="password2" id="password2" placeholder="Ponovite novu lozinku">
                    </div>
                </div>
            </div>
            <div class="ui fluid huge darkred submit button" style="width: 100%">Spremi promjene</div>
        </form:form>
    </div>
</main>
<script src="${pageContext.request.contextPath}/scripts/editUser.js">
</script>
</body>
</html>