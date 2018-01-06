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
			<thead>
				<tr>
					<th>Ime ljubimaca</th>
					<th>Usluga</th>
					<th>Zaposlenik</th>
					<th>Status</th>
					<th>Vrijeme preuzimanja</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${reservation.pet}</td>
					<td>${reservation.service}</td>
					<td>${reservation.employee}</td>
					<td>${reservation.status}</td>
					<td>${reservation.time}</td>
				</tr>
			</tbody>
		</table>
	</main>
</body>
</html>