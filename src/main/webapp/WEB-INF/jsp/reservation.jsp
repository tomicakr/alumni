<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Rezervacija" />
	<jsp:param name="view-name" value="" />
</jsp:include>
</head>
<body>
	<%@ include file = "../partials/header.jsp" %>
	<h1>Detaljan pregled rezervacije</h1>
	<main class=" ui middle aligned center  aligned grid">
		<table class="ui celled table">
			<tr>
				<td><strong>Ime ljubimaca</strong></td>
				<td>${reservation.pet}</td>
			</tr>
			<tr>
				<td><strong>Usluga</strong></td>
				<td>${reservation.service}</td>
			</tr>
			<tr>
				<td><strong>Zaposlenik</strong></td>
				<td>${reservation.employee}</td>
			</tr>
			<tr>
				<td><strong>Status</strong></td>
				<td>${reservation.status}</td>
			</tr>
			<tr>
				<td><strong>Vrijeme preuzimanja</strong></td>
				<td>${reservation.time}</td>
			</tr>

		</table>

		<form method="get" action="/users/${reservation.reservationId}">
			<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
			<input type="submit" value="Uredi rezevraciju"></input>				
		</form>
	</main>
</body>
</html>