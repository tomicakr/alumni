<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Detalji korisnika" />
	<jsp:param name="view-name" value="profile" />
</jsp:include>
<body>
<div class="ui mini modal">
	<div class="ui loader active"></div>
	<i class="close icon"></i>
	<div class="header">
		Dodaj ljubimca
	</div>
	<div class="ui center aligned content">
		<form:form action="${pageContext.request.contextPath}/users/${user.mnemonicId}" method="post" id="add-pet-form" class="ui large form" modelAttribute="user">
			<div class="field">
				<input type="text" name="name" id="pet-name" placeholder="Ime">
			</div>
			<div class="field">
				<input type="number" name="age" id="pet-age" placeholder="Broj godina">
			</div>
			<div class="field">
				<input type="text" name="species" id="pet-species" placeholder="Vrsta">
			</div>
			<div class="field">
				<input type="text" name="breed" id="pet-breed" placeholder="Pasmina">
			</div>
			<div class="field">
				<input type="text" name="microchip" id="pet-chip" placeholder="Broj mikročipa ljubimca.">
			</div>
			<div class="field">
				<textarea rows="4" placeholder="Napomene..."></textarea>
			</div>
			<div class="ui fluid huge darkred submit button" style="width: 100%">Dodaj</div>
		</form:form>
	</div>
</div>
</div>
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
	<button id="btn-edit"  class="ui button yellow" >Uredi</button>
	<button id="btn-delete" class="ui button red">Obriši</button>

	<section id="reservations">
		<h2>Rezervacije</h2>
		<button id="btn-add-reservation" class="ui button darkred">Dodaj rezervaciju</button>
		<button id="btn-reservations" class="ui darkred button">Dohvati rezervacije</button>
		<table class="ui celled table"></table>
	</section>
	<section id="pets" >
		<h2>Ljubimci</h2>
		<button id="btn-add-pet" class="ui button darkred">Dodaj ljubimca</button>
		<button id="btn-pets" class="ui darkred button">Dohvati ljubimce</button>
		<table class="ui celled table"></table>
	</section>
	<script src="${pageContext.request.contextPath}/scripts/profile.js"></script>
</body>
</html>