<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="userInSession" property="principal"/>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../partials/head.jsp" flush="true">
        <jsp:param name="title" value="Rezervacije"/>
        <jsp:param name="view-name" value="jobs"/>
    </jsp:include>
</head>

<body>
<%@ include file="../partials/sidebarBegin.jsp" %>
<%@ include file="../partials/header.jsp" %>

<div id="main-div" class="ui segment">
    <h1 align="center">Pregled svih rezervacija: </h1>
    <c:if test="${not empty errorMessage}">
        <div class="ui negative message">
          <i class="close icon"></i>
          <div class="header">
            Ups, dogodila se pogreška!
        </div>
        <ul class="list">
            <li>${errorMessage}</li>
        </ul>
        </div>

    </c:if>
    <div id="main-grid" class="ui stackable column centered grid">

        <!-- prva kolona -->
        <div id="col1" class="four wide column">
            <div class="ui segments">
                <div class="ui segment">
                    <h3>Nove rezervacije</h3>
                </div>

                <sec:authorize access="hasAuthority('ACCEPT_RESERVATION')">
                            <div class="ui right labeled input">
                              <input type="text" id="my-input-first-column" onkeyup="searchFunction('first-column')" placeholder="Pretraži...">
                              <div class="ui dropdown label" >
                                <div class="text">Naslov</div>
                                <i class="dropdown icon"></i>
                                <div class="menu" id="first-column-label">
                                  <div class="item active selected" value="service-pet-pair">Naslov</div>
                                  <div class="item" value="reservation-id-field">ID rez.</div>
                                  <div class="item" value="pet-owner-field">Vlasnici</div>
                                  <div class="item" value="petSpecies-field">Ljubimci</div>
                              </div>
                          </div>
                          
                      </div>

                                </sec:authorize>

                <div class="ui segments card-holder">
                    <div class="ui cards wide" id="first-column">
                        <c:forEach var="reservation" items="${open}">
                            <div class="card">
                                <div class="content">
                                    <div class="header">
                                        <h4 class="service-pet-pair" style="color: darkred">${reservation.service}, ${reservation.pet}</h4>
                                        <h5 style="margin-top: -10px">Vrijeme: ${reservation.time}</h5>

                                    </div>
                                    <div class="description">
                                        <div class="ui fluid accordion">
                                            <div class="title">
                                                <i class="dropdown icon"></i>
                                                <strong>Detalji rezervacije</strong>
                                            </div>
                                            <div class="content">
                                                <p><strong>ID: </strong><span class="reservation-id-field">${reservation.reservationId}</span></p>
                                                <p><strong>Vrsta: </strong><span class="petSpecies-field">${reservation.petSpecies}</span></p>
                                                <p><strong>Vlasnik: </strong><span class="pet-owner-field">${reservation.owner}</span></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <sec:authorize access="hasAuthority('ACCEPT_RESERVATION')">
                                    <form method="post" action="${pageContext.request.contextPath}/users/${userId}/jobs/${reservation.reservationId}/accept">
                                        <input type="hidden" name="reservationId" value="${reservation.reservationId}"/>
                                        <button class="ui darkred bottom fluid attached submit button" tabindex="0">
                                            <i class="level up icon"></i>
                                            Prihvati 
                                        </button>

                                    </form>
                                </sec:authorize>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


        <!-- druga kolona -->
        <div id="col2" class="four wide column">
            <div class="ui segments">
                <div class="ui segment">
                    <h3>Prihvaćene rezervacije</h3>
                </div>

                <sec:authorize access="hasAuthority('ACCEPT_RESERVATION')">
                    <div class="ui right labeled input">
                              <input type="text" id="my-input-second-column" onkeyup="searchFunction('second-column')" placeholder="Pretraži...">
                              <div class="ui dropdown label">
                                <div class="text">Naslov</div>
                                <i class="dropdown icon"></i>
                                <div class="menu" id="second-column-label">
                                  <div class="item active selected" value="service-pet-pair">Naslov</div>
                                  <div class="item" value="reservation-id-field">ID rez.</div>
                                  <div class="item" value="pet-owner-field">Vlasnici</div>
                                  <div class="item" value="petSpecies-field">Ljubimci</div>
                              </div>
                          </div>
                          
                      </div>
                </sec:authorize>


                <div class="ui segments card-holder">
                    <div class="ui cards wide" id="second-column">
                        <c:forEach var="reservation" items="${accepted}">
                            <div class="card">
                                <div class="content">
                                    <div class="header">
                                        <h4 class="service-pet-pair" style="color: darkred">${reservation.service}, ${reservation.pet}</h4>
                                        <h5 style="margin-top: -10px">Vrijeme: ${reservation.time}</h5>

                                    </div>
                                    <div class="description">
                                        <div class="ui fluid accordion">
                                            <div class="title">
                                                <i class="dropdown icon"></i>
                                                <strong>Detalji rezervacije</strong>
                                            </div>
                                            <div class="content">
                                                <p><strong>ID: </strong><span class="reservation-id-field">${reservation.reservationId}</span></p>
                                                <p><strong>Vrsta: </strong><span class="petSpecies-field">${reservation.petSpecies}</span></p>
                                                <p><strong>Vlasnik: </strong><span class="pet-owner-field">${reservation.owner}</span></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <sec:authorize access="hasAuthority('CONFIRM_RESERVATION')">
                                    <form method="post" action="${pageContext.request.contextPath}/users/${userId}/jobs/${reservation.reservationId}/confirm">
                                        <input type="hidden" name="reservationId" value="${reservation.reservationId}"/>
                                        <button class="ui bottom darkred fluid attached submit button" tabindex="0">
                                            <i class="checkmark icon"></i>
                                            Potvrdi
                                        </button>
                                    </form>
                                </sec:authorize>

                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <!-- treca kolona -->
        <div id="col3" class="four wide column">
            <div class="ui segments">
                <div class="ui segment">
                    <h3>Potvrđene rezervacije</h3>
                </div>

                <sec:authorize access="hasAuthority('ACCEPT_RESERVATION')">
                     <div class="ui right labeled input">
                               <input type="text" id="my-input-third-column" onkeyup="searchFunction('third-column')" placeholder="Pretraži...">
                              <div class="ui dropdown label">
                                <div class="text">Naslov</div>
                                <i class="dropdown icon"></i>
                                <div class="menu" id="third-column-label">
                                  <div class="item active selected" value="service-pet-pair">Naslov</div>
                                  <div class="item" value="reservation-id-field">ID rez.</div>
                                  <div class="item" value="pet-owner-field">Vlasnici</div>
                                  <div class="item" value="petSpecies-field">Ljubimci</div>
                              </div>
                          </div>
                        
                      </div>
                </sec:authorize>

                <div class="ui segments card-holder">

                    <div class="ui cards wide" id="third-column">
                        <c:forEach var="reservation" items="${confirmed}">
                            <div class="card">
                                <div class="content">
                                    <div class="header">
                                        <h4 class="service-pet-pair" style="color: darkred">${reservation.service}, ${reservation.pet}</h4>
                                        <h5 style="margin-top: -10px">Vrijeme: ${reservation.time}</h5>

                                    </div>
                                    <div class="description">
                                        <div class="ui fluid accordion">
                                            <div class="title">
                                                <i class="dropdown icon"></i>
                                                <strong>Detalji rezervacije</strong>
                                            </div>
                                            <div class="content">
                                                <p><strong>ID: </strong><span class="reservation-id-field">${reservation.reservationId}</span></p>
                                                <p><strong>Vrsta: </strong><span class="petSpecies-field">${reservation.petSpecies}</span></p>
                                                <p><strong>Vlasnik: </strong><span class="pet-owner-field">${reservation.owner}</span></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <sec:authorize access="hasAuthority('CONFIRM_RESERVATION')">
                                 <form method="post" action="${pageContext.request.contextPath}/users/${userId}/jobs/${reservation.reservationId}/archive">
                                        <input type="hidden" name="reservationId" value="${reservation.reservationId}"/>
                                        <button class="ui bottom darkred fluid attached submit button" tabindex="0">
                                            <i class="arrow down icon"></i>
                                            Završi i arhiviraj
                                        </button>
                                    </form>
                                </form>
                                </sec:authorize>

                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../partials/footer.jsp" %>
<%@ include file="../partials/sidebarEnd.jsp" %>

<script src="${pageContext.request.contextPath}/scripts/jobs.js"></script>
</body>
</html>


