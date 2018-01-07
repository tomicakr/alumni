<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp" flush="true">
<jsp:param name="title" value="Rezervacija" />
<jsp:param name="view-name" value="register" />
</jsp:include>
<body>
	<main class="ui middle aligned center aligned grid">
		<div class="column">
			<h1 class="ui image massive header">Nova rezervacija</h1>
			<form action="/users/${userId}/reservations/" method="post"
			class="ui large form segment stacked">
			
			<div class="field">
				<input type="text" name="service" id="usluga" placeholder="Usluga">
			</div>

			<div class="field">
				<select name="pet" class="ui search dropdown">
					<option value="">Odaberite svog ljubimca</option>
					<c:forEach var="pet" items="${pets}">
					<option value="${pet.petId}">${pet.name}</option>
				</c:forEach>
				</select>
			</div>

			<div class="field">
				<label>Unesite datum rezervacije</label>
				<input type="date" name="executionDate" id="executionDate">
			</div>
			<div class="field">
				<label>Unesite vrijeme rezervacije</label>
				<input type="time" name="executionTime" id="executionTime"
				placeholder="Vrijeme rezervacije">
			</div>

			<div class="field">
				<select name="employee" class="ui search dropdown" id="employeeOdabir">
					<option value="">Odaberite zaposlenika</option>
					<c:forEach var="employee" items="${employees}">
					<option value="${employee.userId}">${employee.name}</option>
				</c:forEach>
			</select>

			
			<div class="field">
				<label>Vrijeme trajanja usluge</label>
				<input type="time" name="duration" id="duration"
				placeholder="Trajanje">
			</div>


			<div class="field">
				<div class="ui checked checkbox">
				<input type="checkbox" name="sendReminder" checked="">
				<label>Želim dobiti podsjetnik na mail?</label>
			</div>
			</div>


			<div class="ui fluid huge darkred submit button" style="width: 100%">Napravi rezervaciju!</div>
		</form>
	</div>
</main>
<script src="${pageContext.request.contextPath}/scripts/newRegForm.js"></script>
<%@ include file = "../partials/footer.jsp" %>
</body>
</html>