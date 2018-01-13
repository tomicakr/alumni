<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp" flush="true">
<jsp:param name="title" value="Detalji korisnika" />
<jsp:param name="view-name" value="profile" />
</jsp:include>

<body>
	<%@ include file = "../partials/header.jsp" %>
	<div id="delete-user-modal" class="ui small basic test modal transition hidden">
		<div class="ui icon header">
			<i class="trash icon"></i>
			Brisanje korisničkog računa
		</div>
		<div class="content">
			<p>Jeste li sigurni da želite obrisati korisnički račun?</p>
		</div>
		<div class="actions">
			<div class="ui green basic cancel inverted button">
				<i class="remove icon"></i>
				Ne
			</div>
			<div class="ui red basic ok inverted button">
				<i class="checkmark icon"></i>
				Da
			</div>
		</div>
	</div>
	<div id="delete-pet-modal" class="ui small basic test modal transition hidden">
		<div class="ui icon header">
			<i class="trash icon"></i>
			Brisanje ljubimca
		</div>
		<div class="content">
			<p>Brisanje ljubimca uzrokovat će otkazivanje svih njegovih rezervacija, neovisno o razini. Jeste li sigurni da želite nastaviti?</p>
		</div>
		<div class="actions">
			<div class="ui green basic cancel inverted button">
				<i class="remove icon"></i>
				Ne
			</div>
			<div class="ui red basic ok inverted button">
				<i class="checkmark icon"></i>
				Da
			</div>
		</div>
	</div>
	<div id="delete-user-success" class="ui small basic test modal transition hidden">
		<div class="ui icon header">
			<i class="green checkmark icon"></i>
		</div>
		<h2>Korisnički račun uspješno obrisan.</h2>
		<i class="checkmark icon"></i>
		<div class="actions">
			<div class="ui red ok inverted button">
				U redu
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

	<main class="ui container" id="profileContent">
			<div class="ui top attached huge tabular menu">
				<a class="item active"  data-tab="first">
					Detalji
				</a>
				<a class="item"  data-tab="second">
					Ljubmici
				</a>
				<a class="item"  data-tab="third">
					Rezervacije
				</a>
			</div>
			<section id="user-info" class="ui bottom attached tab segment transition fade in active" data-tab="first">
				<div>
					<h2 class="ui darkred left floated header">
						Detalji korisnika
					</h2>
					<h4 class="ui right floated header">
						<i id="btn-employ-user" class="add user action icon" title = "Zaposli"></i>
						<i id="btn-fire-user" class="delete user action icon" title = "Otpusti"></i>
						<i id="btn-edit-user" class="edit action icon" title="Uredi profil"></i>
						<i id="btn-delete-user" class="trash action icon" title="Obriši profil"></i>
					</h4>
				</div>
				<div class="ui hidden divider"></div>
				<table class="ui celled table" >

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
						<td><strong>Ovlasti</strong></td>
						<td><sec:authentication property="principal.authorities"/></td>
					</tr>
				</table>
			</section>

			<section  id="pets" class="ui bottom attached tab segment transition fade in" data-tab="second">
				<div>
					<h2 class="ui darkred left floated header">
						Ljubimci
					</h2>
					<h4 class="ui right floated header">
						<i id="btn-pets" class="refresh action icon" title="Osvježi podatke"></i>
							<i id="btn-add-pet" class="plus action icon" title="Dodaj novog ljubimca"></i>
					</h4>
				</div>
				<div class="ui hidden divider"></div>
				<div id="pets-table-container">
					<h2 id="pet-placeholder" class="ui centered aligned header">Nema prijavljenih ljubimaca</h2>
					<table class="ui celled table" id="pets-table">
						<thead>
						<tr>
							<th>Ime</th>
							<th>Starost</th>
							<th>Vrsta</th>
							<th>Pasmina</th>
							<th>Spol</th>
							<th>Mikročip</th>
							<th>Napomena</th>
							<th></th>
						</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>

			</section>

			<section id="reservations" class="ui bottom attached tab segment transition fade in" data-tab="third">
				<div>
					<h2 class="ui darkred left floated header">
						Rezervacije
					</h2>
					<h4 class="ui right floated header">
						<i id="btn-reservations" class="refresh action icon" title="Osvježi rezervacije"></i>
						<i id="btn-add-reservation" class="plus action icon" title="Nova rezervacija"></i>
					</h4>
				</div>
				<div class="ui hidden divider"></div>
				<div id="reservations-table-container">
					<h2 id="reservations-placeholder" class="ui centered aligned header">Nema aktivnih rezervacija</h2>
					<table class="ui celled table" id="reservations-table">
						<thead>
						<tr>
							<th>Ljubimac</th>
							<th>Usluga</th>
							<th>Zaposlenik</th>
							<th>Status</th>
							<th>Termin</th>
						</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
			</section>
	</main>

	<%@ include file = "../partials/footer.jsp" %>

<script src="${pageContext.request.contextPath}/scripts/profile.js"></script>
</body>
</html>