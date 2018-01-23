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
        <form action="/users/${ownerId}/reservations/${reservationId}" method="post"
              class="ui large form segment stacked">

            <div class="field">
                <select name="service" class="ui search dropdown" id="res-service">
                    <c:forEach var="service" items="${services}">
                        <option value="${service.id}"
                                <c:if test="${reservation.serviceId.equals(service.id)}">selected</c:if>>
                                ${service.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="field">
                <select name="pet" id="res-pet" class="ui search dropdown">
                    <c:forEach var="pet" items="${pets}">
                        <option value="${pet.petId}"
                                <c:if test="${petId.equals(pet.petId)}">selected</c:if>>
                                ${pet.name}
                        </option>
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
                <select name="preferredEmployee" class="ui search dropdown" id="res-employee">
                    <c:forEach var="employee" items="${employees}">
                        <option value="${employee.userId}"
                                <c:if test="${reservation.preferredEmployee.userId.equals(employee.userId)}">selected</c:if>>
                                ${employee.firstName} ${employee.lastName}
                        </option>
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