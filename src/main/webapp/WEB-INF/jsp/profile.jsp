<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp" flush="true">
    <jsp:param name="title" value="Detalji korisnika"/>
    <jsp:param name="view-name" value="profile"/>
</jsp:include>

<body>
<%@ include file="../partials/sidebarBegin.jsp" %>
<%@ include file="../partials/header.jsp" %>

<main class="ui container" id="profileContent">
    <div class="ui top attached huge tabular menu">
        <a class="item active" data-tab="first"> Detalji </a> <a class="item"
                                                                 data-tab="second"> Ljubmici </a> <a class="item"
                                                                                                     data-tab="third">
        Rezervacije </a>
    </div>
    <section id="user-info"
             class="ui bottom attached tab segment active"
             data-tab="first">
        <div>
            <h2 class="ui darkred left floated header">Detalji korisnika</h2>
            <h4 class="ui right floated header">
                <sec:authorize access="hasRole('KORISNIK')">
                    <i id="btn-edit-user" class="edit action icon" title="Uredi profil"></i>
                    <i id="btn-delete-user" class="trash action icon"
                       title="Obriši profil"></i>
                </sec:authorize>

                <sec:authorize access="hasRole('ZAPOSLENIK')">
                    <i id="btn-edit-user" class="edit action icon" title="Uredi profil"></i>
                    <i id="btn-delete-user" class="trash action icon"
                       title="Obriši profil"></i>
                </sec:authorize>

                <sec:authorize access="hasRole('ADMINISTRATOR')">
                    <sec:authorize access="hasAuthority('ELEVATE_USER_TO_EMPLOYEE')">
                        <c:choose>
                            <c:when test="${user.roles.contains(\"ROLE_ADMINISTRATOR\")}">
                            </c:when>
                            <c:when test="${not user.roles.contains(\"ROLE_ZAPOSLENIK\")}">
                                
                                <i id="btn-employ-user" class="add user action icon"
                                   title="Zaposli"></i>
                                <i id="btn-fire-user" class="inactive delete user action icon"
                                   title="Otpusti"></i>
                            </c:when>
                            <c:otherwise>
                                 <i id="btn-employe-jobs" class="industry action icon"
                                   title="Poslovi"></i>
                                <i id="btn-employ-user" class="inactive add user action icon"
                                   title="Zaposli"></i>
                                <i id="btn-fire-user" class="delete user action icon"
                                   title="Otpusti"></i>
                            </c:otherwise>
                        </c:choose>
                    </sec:authorize>

                    <i id="btn-edit-user" class="edit action icon" title="Uredi profil"></i>
                    <i id="btn-delete-user" class="trash action icon"
                       title="Obriši profil"></i>
                </sec:authorize>

            </h4>
        </div>
        <div class="ui hidden divider"></div>
        <table class="ui celled table">

            <tr>
                <td><strong>Ime</strong></td>
                <td>${user.firstName}</td>
            </tr>
            <tr>
                <td><strong>Prezime</strong></td>
                <td>${user.lastName}</td>
            </tr>
            <tr>
                <td><strong>E-mail</strong></td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td><strong>Grad</strong></td>
                <td>${user.city}</td>
            </tr>
            <tr>
                <td><strong>Adresa</strong></td>
                <td>${user.address}</td>
            </tr>
            <tr>
                <td><strong>OIB</strong></td>
                <td>${user.userPid}</td>
            </tr>
            <tr>
                <td><strong>Broj mobitela</strong></td>
                <td>${user.mobilePhone}</td>
            </tr>
            <tr>
                <td><strong>Broj telefona</strong></td>
                <td>${user.telephone}</td>
            </tr>
            <tr>
                <td><strong>Uloga</strong></td>
                <td id="role"><c:choose>
                    <c:when test="${user.roles.contains(\"ROLE_ADMINISTRATOR\")}">
                        <p class="ui administrator long tag label">Administrator</p>
                    </c:when>
                    <c:when test="${user.roles.contains(\"ROLE_ZAPOSLENIK\")}">
                        <p class="ui employee long tag label">Zaposlenik</p>
                    </c:when>
                    <c:when test="${user.roles.contains(\"ROLE_KORISNIK\")}">
                        <p class="ui long tag label brown">Klijent</p>
                    </c:when>
                    <c:otherwise>
                        <p class="ui employee long tag label">Greška</p>
                    </c:otherwise>
                </c:choose></td>
            </tr>
            <tr>
                <td><strong>Radno vrijeme</strong></td>
                <td>${user.workingTime}</td>
            </tr>
        </table>
        <div class="ui hidden divider"></div>
        <div>
            <h2 class="ui darkred left floated header">Postavke</h2>
            <h4 class="ui right floated header">
                    <i id="btn-edit-settings" class="setting action icon" title="Promijeni"></i>
            </h4>
        </div>
    </section>

    <section id="pets"
             class="ui bottom attached tab segment"
             data-tab="second">
        <div>
            <h2 class="ui darkred left floated header">Ljubimci</h2>
            <h4 class="ui right floated header">
                <i id="btn-pets" class="refresh action icon" title="Osvježi"></i>
                <i id="btn-add-pet" class="plus action icon"
                   title="Dodaj novog ljubimca"></i>
            </h4>
        </div>
        <div class="ui hidden divider"></div>
        <div id="pets-table-container">
            <div id="pet-loader" class="ui massive loader"></div>
            <h2 id="pet-placeholder" class="ui centered aligned header inactive">Nema
                prijavljenih ljubimaca</h2>
            <table class="ui celled table inactive" id="pets-table">
                <thead>
                <tr>
                    <th>Ime</th>
                    <th>Starost</th>
                    <th>Vrsta</th>
                    <th>Spol</th>
                    <th>Mikročip</th>
                    <th>Napomena</th>
                    <th>Opcije</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>

    </section>

    <section id="reservations"
             class="ui bottom attached tab segment"
             data-tab="third">
        <div>
            <h2 class="ui darkred left floated header">Rezervacije</h2>
            <h4 class="ui right floated header">
                <i id="btn-reservations" class="refresh action icon"
                   title="Osvježi"></i> <i id="btn-add-reservation"
                                           class="plus action icon" title="Nova rezervacija"></i>
            </h4>
        </div>
        <div class="ui hidden divider"></div>
        <div id="reservations-table-container">
            <div id="reservations-loader" class="ui massive loader"></div>
            <h2 id="reservations-placeholder" class="ui centered aligned header inactive">Nema
                aktivnih rezervacija</h2>
            <table class="ui celled table inactive" id="reservations-table">
                <thead>
                <tr>
                    <th>Ljubimac</th>
                    <th>Usluga</th>
                    <th>Zaposlenik</th>
                    <th>Status</th>
                    <th>Termin</th>
                    <th>Opcije</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </section>
</main>
<%@ include file="../partials/sidebarEnd.jsp" %>
<%@ include file="../partials/footer.jsp" %>

<script src="${pageContext.request.contextPath}/scripts/includes/utilities.js"></script>
<script src="${pageContext.request.contextPath}/scripts/includes/calendar.js"></script>
<script src="${pageContext.request.contextPath}/scripts/includes/profileForms.js"></script>
<script src="${pageContext.request.contextPath}/scripts/includes/table.js"></script>
<script src="${pageContext.request.contextPath}/scripts/profile.js"></script>
</body>
</html>