<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Detalji korisnika" />
	<jsp:param name="view-name" value="profile" />
</jsp:include>
<body>
	<section id="user-info">
		<table class="ui celled table">
			<h2>Profil korisnika</h2>
			<tr>
				<td>Ime</td>
				<td>${user.firstName}</td>
			</tr>
			<tr>
				<td>Prezime</td>
				<td>${user.lastName}</td>
			</tr>
			<tr>
				<td>E-mail</td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td>Grad</td>
				<td>${user.city}</td>
			</tr>
			<tr>
				<td>Adresa</td>
				<td>${user.address}</td>
			</tr>
			<tr>
				<td>OIB</td>
				<td>${user.userPid}</td>
			</tr>
			<tr>
				<td>Broj mobitela</td>
				<td>${user.mobilePhone}</td>
			</tr>
			<tr>
				<td>Broj telefona</td>
				<td>${user.telephone}</td>
			</tr>
		</table>
	</section>


	<section id="reservations">
		<h2>Rezervacije</h2>
		<button id="btn-reservations" class="ui darkred button">Dohvati rezervacije</button>
		<table class="ui celled table"></table>
	</section>
	<section id="pets" >
		<h2>Ljubimci</h2>
		<button id="btn-pets" class="ui darkred button">Dohvati ljubimce</button>
		<table class="ui celled table"></table>
	</section>
	<script src="${pageContext.request.contextPath}/scripts/profile.js"></script>
</body>
</html>