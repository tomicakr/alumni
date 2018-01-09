<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../partials/head.jsp" flush="true">
<jsp:param name="title" value="Detalji korisnika" />
<jsp:param name="view-name" value="profile" />
</jsp:include>
<body>
	<%@ include file = "../partials/indexHeader.jsp" %>
	<main>

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
				<div class="ui red ok inverted button">
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
</div>
<section id="user-info">
	<table class="ui celled table" style="margin:auto; width: 50%">
		<center>
			<h2>Profil korisnika</h2>
		</center>
		
		<tr>
			<td><strong>Ime</strong></td>
			<td>${userInSession.firstName}</td>
		</tr>
		<tr>
			<td><strong>Prezime</strong></td>
			<td>${userInSession.lastName}</td>
		</tr>
		<tr>
			<td><strong>E-mail</strong></td>
			<td>${userInSession.email}</td>
		</tr>
		<tr>
			<td><strong>Grad</strong></td>
			<td>${userInSession.city}</td>
		</tr>
		<tr>
			<td><strong>Adresa</strong></td>
			<td>${userInSession.address}</td>
		</tr>
		<tr>
			<td><strong>OIB</strong></td>
			<td>${userInSession.userPid}</td>
		</tr>
		<tr>
			<td><strong>Broj mobitela</strong></td>
			<td>${userInSession.mobilePhone}</td>
		</tr>
		<tr>
			<td><strong>Broj telefona</strong></td>
			<td>${userInSession.telephone}</td>
		</tr>
	</table>
</section>
<div style="margin: 15px 0">
	<center>
		<button id="btn-edit"  class="ui button yellow" >Uredi</button>
		<button id="btn-delete" class="ui button red">Obriši</button>


	</center>

</div>

<center>
	<div class="ui two column grid">
		<div class="column">
			<div class="ui segment">
				<section id="reservations">
					<h2>Rezervacije</h2>
					<button id="btn-add-reservation" class="ui button darkred" ><a href="${pageContext.request.contextPath}/users/${userInSession.userId}/reservations/new">Dodaj rezervaciju</a></button>
					<button id="btn-reservations" class="ui darkred button">Dohvati rezervacije</button>
					<table class="ui celled table"></table>
				</section>
			</div>
		</div>
		<div class="column">
			<div class="ui segment">
				<section id="pets" >
					<h2>Ljubimci</h2>
					<button id="btn-add-pet" class="ui button darkred">Dodaj ljubimca</button>
					<button id="btn-pets" class="ui darkred button">Dohvati ljubimce</button>
					<table class="ui celled table"></table>
				</section>
			</div>
		</div>
	</div>


</center>

</main>


<%@ include file = "../partials/footer.jsp" %>	




<script src="${pageContext.request.contextPath}/scripts/profile.js"></script>
</body>
</html>