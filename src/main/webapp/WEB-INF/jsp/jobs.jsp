<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Rezervacije" />
	<jsp:param name="view-name" value="" />
</jsp:include>
</head>
<body>
	<%@ include file = "../partials/header.jsp" %>
	<h1>Pregled svih rezervacija: </h1>
	<main class=" ui middle aligned center  aligned grid">
		<table class="ui celled table">
			<thead>
				<tr>
					<th>Ime ljubimaca</th>
					<th>Usluga</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reservation" items="${reservations}">
				<tr>
					<td>${reservation.pet}</td>
					<td>${reservation.service}</td>

					<form method="get" action="/jobs/${reservation.reservationId}">
						<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
						<input type="submit" value="Detaljno">Detaljno</input>				
					</form>

					<form method="post" action="/jobs/${reservation.reservationId}/accept">
						<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
						<input type="submit" value="Prihvati">Prihvati</input>				
					</form>

					<form method="post" action="/jobs/${reservation.reservationId}/confirm">
						<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
						<input type="submit" value="Potvrdi">Potrvrdi</input>					
					</form>
			</tr>
		</c:forEach>
	</tbody>
</table>
</main>
</body>
</html>
