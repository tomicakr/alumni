<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp" flush="true">
    <jsp:param name="title" value="Rezervacija"/>
    <jsp:param name="view-name" value="register"/>
</jsp:include>
<body>
<main class="ui middle aligned center aligned grid">
    <div class="column">
        <h1 class="ui image massive header">Uredite rezervaciju</h1>
        <form action="/users/${reservation.ownerId}/reservations/${reservation.reservationId}" method="post"
              class="ui large form segment stacked">

            <div class="field">
                <select name="service" class="ui search dropdown" id="res-service">
                    <option value="${reservation.service.id}">${reservation.service.name}</option>
                    <c:forEach var="service" items="${services}">
                        <option value="${service.id}">${service.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="field">
                <select name="pet" id="res-pet" class="ui search dropdown">
                    <option value="${reservation.pet.petId}">${reservation.pet.name}</option>
                    <c:forEach var="pet" items="${pets}">
                        <option value="${pet.petId}">${pet.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="ui calendar field" id="res-time">
                <div class="ui input left icon">
                    <i class="calendar icon"></i>
                    <input type="text" name="executionTime" placeholder="Odaberite termin">
                </div>
            </div>

            <div class="field">
                <select name="preferedEmployee" class="ui search dropdown" id="res-employee">
                    <option value="${reservation.preferedEmployee.userId}">${reservation.preferedEmployee.firstName} ${reservation.preferedEmployee.lastName}</option>
                    <c:forEach var="employee" items="${employees}">
                        <option value="${employee.userId}">${employee.firstName} ${employee.lastName}</option>
                    </c:forEach>
                </select>

            </div>
            <div class="ui calendar field" id="res-duration">
                <div class="ui input left icon">
                    <i class="clock icon"></i>
                    <input type="text" name="duration" placeholder="Trajanje">
                </div>
            </div>


            <div class="field">
                <div class="ui checked checkbox">
                    <input type="checkbox" name="sendReminder" id="res-reminder" value="1" checked="checked">
                    <label>Želim dobiti podsjetnik na mail?</label>
                </div>
            </div>


            <div class="ui fluid huge darkred submit button" style="width: 100%">Spremi promjene</div>
        </form>
        <c:if test="${not empty errorMessage}">
            <div class="ui error message visible">${errorMessage}</div>
        </c:if>
    </div>
</main>
<script src="${pageContext.request.contextPath}/scripts/forms.js"></script>
<script src="${pageContext.request.contextPath}/scripts/includes/calendar.js"></script>
<script src="${pageContext.request.contextPath}/scripts/editReservation.js"></script>
<%@ include file="../partials/footer.jsp" %>
</body>
</html>