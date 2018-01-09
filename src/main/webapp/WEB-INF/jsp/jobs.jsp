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
	
	<%@ include file = "../partials/indexHeader.jsp" %>
	<h1 align="center">Pregled svih rezervacija: </h1>


<main>

<div class="ui three column grid">

<!-- prva kolona -->
	<div class="column">
		<div class="ui segments">
			<div class="ui segment">
				<h2>Nove rezervacije</h2>
			</div>
			<div class="ui segments">
				<c:forEach var="reservation" items="${open}">
				<div class="ui segment">
					<p> <strong> ${reservation.pet}</strong>, ${reservation.service}  <br>Vrijeme: ${reservation.time}</p>
					<div class="ui two column grid">

						<div class="column">
							<form method="get" action="/jobs/${reservation.reservationId}">
								<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
								<button class="ui fluid darkred submit button" >Detalji</button>
							</form>
						</div>

						<div class="column">
							<form method="post" action="/jobs/${reservation.reservationId}/accept">
								<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
								<button class="ui fluid darkred submit button" >Prihvati</button>
							</form>
						</div>

					</div>
				</div>
			</c:forEach>
		</div>
	</div>

</div>


<!-- druga kolona -->
<div class="column">
	<div class="ui segments">
		<div class="ui segment">
			<h2>Prihvaćene rezervacije</h2>
		</div>
		<div class="ui segments">
			<c:forEach var="reservation" items="${accepted}">
			<div class="ui segment">
				<p> <strong> ${reservation.pet}</strong>, ${reservation.service}  <br>Vrijeme: ${reservation.time}</p>
				<div class="ui two column grid">

					<div class="column">
						<form method="get" action="/jobs/${reservation.reservationId}">
							<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
							<button class="ui fluid darkred submit button" >Detalji</button>
						</form>
					</div>
					<div class="column">

						<form method="post" action="/jobs/${reservation.reservationId}/confirm">
							<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
							<button class="ui fluid darkred submit button" >Potvrdi</button>
						</form>
					</div>
				</div>
			</div>
		</c:forEach>

	</div>
</div>

</div>

<!-- treca kolona -->
<div class="column">
	<div class="ui segments">
		<div class="ui segment">
			<h2>Potvrđene rezervacije</h2>
		</div>
		<div class="ui segments">
			<c:forEach var="reservation" items="${confirmed}">
			<div class="ui segment">
				<p> <strong> ${reservation.pet}</strong>, ${reservation.service}  <br>Vrijeme: ${reservation.time}</p>
					
					<div class="ui three column grid">
						<div class="column">			
					</div>

					<div class="column">
						<form method="get" action="/jobs/${reservation.reservationId}">
							<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
							<button class="ui fluid darkred submit button" >Detalji</button>
						</form>
					</div>

					<div class="column">						
					</div>

				</div>

			</div>
		</c:forEach>

	</div>
</div>


</div>


</div>
</main>

	<%@ include file = "../partials/footer.jsp" %>
</body>
</html>
