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


<div id="delete-user-modal"
     class="ui small basic test modal transition hidden">
    <div class="ui icon header">
        <i class="trash icon"></i> Brisanje korisničkog računa
    </div>
    <div class="content">
        <p>Jeste li sigurni da želite obrisati korisnički račun?</p>
    </div>
    <div class="actions">
        <div class="ui green basic cancel inverted button">
            <i class="remove icon"></i> Ne
        </div>
        <div class="ui red basic ok inverted button">
            <i class="checkmark icon"></i> Da
        </div>
    </div>
</div>
<div id="delete-pet-modal"
     class="ui small basic test modal transition hidden">
    <div class="ui icon header">
        <i class="trash icon"></i> Ukloni ljubimca
    </div>
    <div class="content">
        <p>Uklanjanje ljubimca uzrokovat će otkazivanje svih njegovih
            rezervacija, neovisno o razini. Jeste li sigurni da želite
            nastaviti?</p>
    </div>
    <div class="actions">
        <div class="ui green basic cancel inverted button">
            <i class="remove icon"></i> Ne
        </div>
        <div class="ui red basic ok inverted button">
            <i class="checkmark icon"></i> Da
        </div>
    </div>
</div>
<div id="delete-reservation-modal"
     class="ui small basic test modal transition hidden">
    <div class="ui icon header">
        <i class="remove from calendar icon"></i> Otkaži rezervaciju
    </div>
    <div class="content">
        <p>Otkazivanje rezervacije ne uključuje povrat novaca, jeste li sigurni da želite nastaviti?</p>
    </div>
    <div class="actions">
        <div class="ui green basic cancel inverted button">
            <i class="remove icon"></i> Ne
        </div>
        <div class="ui red basic ok inverted button">
            <i class="checkmark icon"></i> Da
        </div>
    </div>
</div>
<div id="delete-user-success"
     class="ui small basic test modal transition hidden">
    <div class="ui icon header">
        <i class="green checkmark icon"></i>
    </div>
    <h2>Korisnički račun uspješno obrisan.</h2>
    <i class="checkmark icon"></i>
    <div class="actions">
        <div class="ui red ok inverted button">U redu</div>
    </div>
</div>
<div id="add-pet-modal" class="ui mini modal">
    <i class="close icon"></i>
    <div class="header">Dodaj ljubimca</div>
    <div class="ui center aligned content">
        <form:form
                action="${pageContext.request.contextPath}/users/${user.userId}/pets/"
                method="post" id="add-pet-form" class="ui large form">
            <div class="field">
                <input type="text" name="name" id="pet-name" placeholder="Ime">
            </div>
            <div class="two equal width fields">
                <div class="field">
                    <input type="number" name="age" id="pet-age" placeholder="Starost">
                </div>
                <div class="field">
                    <div class="ui dropdown selection" tabindex="0">
                        <select name="sex" id="gender">
                            <option value="">Spol</option>
                            <option value="M">Dečko</option>
                            <option value="F">Cura</option>
                        </select><i class="dropdown icon"></i>
                        <div class="default text">Spol</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="M">Dečko</div>
                            <div class="item" data-value="F">Cura</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="field">
                <div class="required field">
                    <select name="species" class="ui dropdown" id="pet-species">
                    </select>
                </div>
            </div>
            <div class="field">
                <input type="text" name="microchip" id="pet-chip"
                       placeholder="Broj mikročipa ljubimca.">
            </div>
            <div class="field">
					<textarea rows="4" name="remark" id="remark"
                              placeholder="Napomene..."></textarea>
            </div>
            <div class="ui fluid huge darkred submit button" style="width: 100%">Dodaj</div>
        </form:form>
    </div>
</div>
<div id="add-reservation-modal" class="ui mini modal">
    <i class="close icon"></i>
    <div class="header">Nova rezervacija</div>
    <div class="ui center aligned content">
        <form:form
                action="${pageContext.request.contextPath}/users/${user.userId}/pets/"
                method="post" id="add-pet-form" class="ui large form">
            <div class="ui calendar field" id="res-time">
                <div class="ui input left icon">
                    <i class="calendar icon"></i>
                    <input type="text" name="executionTime" placeholder="Odaberite termin">
                </div>
            </div>
            <div class="field">
                <div class="required field">
                    <select name="service" class="ui dropdown" id="res-service">
                    </select>
                </div>
            </div>
            <div class="field">
                <div class="required field">
                    <select name="pet" class="ui dropdown" id="res-pet">
                    </select>
                </div>
            </div>
            <div class="field">
                <div class="required field">
                    <select name="employee" class="ui dropdown" id="res-employee">
                    </select>
                </div>
            </div>
            <div class="ui calendar field" id="res-duration">
                <div class="ui input left icon">
                    <i class="clock icon"></i>
                    <input type="text" name="duration" placeholder="Trajanje">
                </div>
            </div>
            <div class="field">
                <div class="ui checked checkbox">
                    <input type="checkbox" name="sendReminder" value="1" checked="checked">
                    <label>Želim dobiti podsjetnik na mail?</label>
                </div>
            </div>
            <div class="ui fluid huge darkred submit button" style="width: 100%">Rezerviraj</div>
        </form:form>
    </div>
</div>
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
                                 <i id="btn-employe-jobs" class="industry icon"
                                   title="Pogledaj poslove"></i>
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
        </table>
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


<script src="${pageContext.request.contextPath}/scripts/profile.js"></script>
</body>
</html>