<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
					<th>Detaljno</th>
					<th>Prihvati</th>
					<th>Potvrdi</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reservation" items="${reservations}">
				<tr>
					<td>${reservation.pet}</td>
					<td>${reservation.reservationId}</td>
					<td>
						<form method="get" action="/jobs/${reservation.reservationId}">
						<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
						<input type="submit" value="Detaljno"></input>				
					</form>
					</td>
					<td>
						<form method="post" action="/jobs/${reservation.reservationId}/accept">
						<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
						<input type="submit" value="Prihvati"></input>				
					</form>
					</td>
					<td>
						<form method="post" action="/jobs/${reservation.reservationId}/confirm">
						<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
						<input type="submit" value="Potvrdi"></input>					
					</form>
					</td>				
			</tr>
		</c:forEach>
	</tbody>
</table>
</main>
</body>
</html>
