<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Registracija" />
	<jsp:param name="view-name" value="register" />
</jsp:include>


<body>

	<main class="ui middle aligned center aligned grid">
	<div class="column">
		<h1 class="ui image massive header">Izradite svoj račun</h1>

		<form action="/users/new" method="post"
			class="ui large form segment stacked">
			<div class="two fields">
				<div class="field">
					<input type="text" name="name" id="first-name" placeholder="Ime" value="${registrationForm.name}">
				</div>
				<div class="field">
					<input type="text" name="surname" id="last-name"
						placeholder="Prezime" value="${registrationForm.surname}">
				</div>
			</div>
			<div class="field">
				<input type="text" name="userPid" id="oib" placeholder="OIB" value="${registrationForm.userPid}">
			</div>
			<div class="field">
				<input type="tel" name="mobilePhone" id="mobile-phone"
					placeholder="Broj mobitela" value="${registrationForm.mobilePhone}">
			</div>
			<div class="field">
				<input type="tel" name="phone" id="telephone"
					placeholder="Broj telefona" value="${registrationForm.phone}">
			</div>
			<div class="field">
				<input type="email" name="email" id="email"
					placeholder="Adresa elektroničke pošte" value="${registrationForm.email}">
			</div>
			<div class="field">
				<div class="ui dropdown selection" tabindex="0">
					<select name="location" id="gender">
						<c:forEach var="location" items="${locations}">
							<option value="${location.id}">${location.name}</option>
						</c:forEach>
					</select><i class="dropdown icon"></i>
					<div class="default text">Grad</div>
					<div class="menu transition hidden" tabindex="-1">
						<c:forEach var="location" items="${locations}">
							<div class="item" data-value="${location.id}">${location.name}</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="field">
				<input type="text" name="address" id="address"
					placeholder="Adresa stanovanja" value="${registrationForm.address}">
			</div>
			<div class="field">
				<input type="password" name="password" id="password"
					placeholder="Lozinka" value="${registrationForm.password}">
			</div>
			<div class="field">
				<input type="password" name="password2" id="password2"
					placeholder="Ponovite lozinku" value="${registrationForm.password2}">
			</div>
			<i id="pass-match" class="fa large" aria-hidden="true"></i>
			<div class="ui fluid huge darkred submit button" style="width: 100%">Registriraj
				se!</div>
		</form>
		
		<spring:hasBindErrors name="registrationForm">
			<c:forEach var="error" items="${errors.allErrors}">
				<div class="ui error message visible"><spring:message message="${error}" /></div>
				<br />
			</c:forEach>
		</spring:hasBindErrors>
		
		<button class="ui huge button" id="komba">Automatski popuni</button>
	</div>
	</main>
	<%@ include file="../partials/footer.jsp"%>
	<script src="../../scripts/forms.js" type="module"></script>
	t
	<script src="../../scripts/register.js" type="module">
	</script>
</body>
</html>