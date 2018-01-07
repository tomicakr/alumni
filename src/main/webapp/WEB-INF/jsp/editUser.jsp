<!--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
-->
<!DOCTYPE html>
<jsp:include page="../partials/head.jsp" flush="true">
    <jsp:param name="title" value="Uredi profil" />
    <jsp:param name="view-name" value="register" />
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
                    <input type="text" name="name" id="first-name" placeholder="Ime">
                </div>
                <div class="field">
                    <input type="text" name="surname" id="last-name" placeholder="Prezime">
                </div>
            </div>
            <div class="field">
                <input type="text" name="userPid" id="oib" placeholder="OIB" disabled>
            </div>
            <div class="field">
                <input type="tel" name="mobilePhone" id="mobile-phone" placeholder="Broj mobitel">
            </div>
            <div class="field">
                <input type="tel" name="phone" id="telephone" placeholder="Broj telefona" disabled>
            </div>
            <div class="field">
                <input type="email" name="email" id="email" placeholder="Adresa elektroničke pošte" disabled>
            </div>
            <div class="field">
                <select name="location" title="">
                    <option value="10000" selected>Zagreb</option>
                    <option value="21000">Split</option>
                    <option value="31000">Osijek</option>
                    <option value="42000">Varaždin</option>
                </select>
            </div>
            <div class="field">
                <input type="text" name="address" id="address" placeholder="Adresa stavnovanja" disabled>
            </div>
            <div class="field">
                <input type="password" name="old-password" id="old-password" placeholder="Unesite staru lozinku">
            </div>
            <div class="field">
                <input type="password" name="password" id="password" placeholder="Unesite novu lozinku">
            </div>
            <div class="field">
                <input type="password" name="password2" id="password2" placeholder="Ponovite novu lozinku">
            </div>
            <i id="pass-match" class="fa large" aria-hidden="true"></i>
            <div class="ui fluid huge darkred submit button" style="width: 100%">Registriraj se!</div>
        </form:form>
    </div>
</main>
<script src="${pageContext.request.contextPath}/scripts/register.js">
</script>
</body>
</html>