<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Detalji korisnika" />
	<jsp:param name="view-name" value="profile" />
</jsp:include>
<body>
<div id="delete-user-modal" class="ui small basic test modal transition hidden">
	<div class="ui icon header">
		<i class="trash icon"></i>
		Brisanje profila
	</div>
	<div class="content">
		<p>Jeste li sigurni da želite obrisati profil</p>
	</div>
	<div class="actions">
		<div class="ui green basic cancel inverted button">
			<i class="remove icon"></i>
			Ne
		</div>
		<div class="ui red ok inverted button">
			<i class="checkmark icon"></i>
			Da
		</div>
	</div>
</div>
<div id="add-pet-modal" class="ui mini modal">
	<i class="close icon"></i>
	<div class="header">
		Dodaj ljubimca
	</div>
	<div class="ui center aligned content">
		<form:form action="${pageContext.request.contextPath}/users/${user.userId}/pets/" method="post" id="add-pet-form" class="ui large form" modelAttribute="user">
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
				<input type="text" name="species" id="pet-species" placeholder="Vrsta">
			</div>
			<div class="field">
				<input type="text" name="breed" id="pet-breed" placeholder="Pasmina">
			</div>
			<div class="field">
				<input type="text" name="microchip" id="pet-chip" placeholder="Broj mikročipa ljubimca.">
			</div>
			<div class="field">
				<textarea rows="4" name="remark" id="remark" placeholder="Napomene..."></textarea>
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
				<td>${userInSession.firstName}</td>
			</tr>
			<tr>
				<td>Prezime</td>
				<td>${userInSession.lastName}</td>
			</tr>
			<tr>
				<td>E-mail</td>
				<td>${userInSession.email}</td>
			</tr>
			<tr>
				<td>Grad</td>
				<td>${userInSession.city}</td>
			</tr>
			<tr>
				<td>Adresa</td>
				<td>${userInSession.address}</td>
			</tr>
			<tr>
				<td>OIB</td>
				<td>${userInSession.userPid}</td>
			</tr>
			<tr>
				<td>Broj mobitela</td>
				<td>${userInSession.mobilePhone}</td>
			</tr>
			<tr>
				<td>Broj telefona</td>
				<td>${userInSession.telephone}</td>
			</tr>
		</table>
	</section>
	<button id="btn-edit"  class="ui button yellow" >Uredi</button>
	<button id="btn-delete" class="ui button red">Obriši</button>

	<section id="reservations">
		<h2>Rezervacije</h2>
		<button id="btn-add-reservation" class="ui button darkred" ><a href="${pageContext.request.contextPath}/users/${userInSession.userPid}/reservations/new">Dodaj rezervaciju</a></button>
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