<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<form action="/users" method="post"
			class="ui large form segment stacked">
			<div class="two fields">
				<div class="field">
					<input type="text" name="name" id="first-name" placeholder="Ime">
				</div>
				<div class="field">
					<input type="text" name="surname" id="last-name"
						placeholder="Prezime">
				</div>
			</div>
			<div class="field">
				<input type="text" name="userPid" id="oib" placeholder="OIB">
			</div>
			<div class="field">
				<input type="tel" name="mobilePhone" id="mobile-phone"
					placeholder="Broj mobitela">
			</div>
			<div class="field">
				<input type="tel" name="phone" id="telephone"
					placeholder="Broj telefona">
			</div>
			<div class="field">
				<input type="email" name="email" id="email"
					placeholder="Adresa elektroničke pošte">
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
					placeholder="Adresa stavnovanja">
			</div>
			<div class="field">
				<input type="password" name="password" id="password"
					placeholder="Lozinka">
			</div>
			<div class="field">
				<input type="password" name="password2" id="password2"
					placeholder="Ponovite lozinku">
			</div>
			<i id="pass-match" class="fa large" aria-hidden="true"></i>
			<div class="ui fluid huge darkred submit button" style="width: 100%">Registriraj
				se!</div>
		</form>
		<button class="ui huge button" id="komba">Skombaj mi sve
			podatke</button>
	</div>
	</main>
	<%@ include file = "../partials/footer.jsp" %>
	<script src="${pageContext.request.contextPath}/scripts/register.js">
		
	</script>
</body>
</html>