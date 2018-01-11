<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Rezervacije" />
	<jsp:param name="view-name" value="jobs" />
</jsp:include>
</head>

<body>
<main>
	<div id="main-div" class="ui segment">

		<main>
			<h1 align="center">Pregled svih rezervacija: </h1>
			<div id="main-grid" class="ui stackable three column grid">
				
				<!-- prva kolona -->
				<div class="column">
					<div class="ui segments">
						<div class="ui segment">
							<h2>Nove rezervacije</h2>
						</div>
						<div class="ui segments" id="scrolling">

							<div class="ui cards wide">
								<c:forEach var="reservation" items="${open}">
								<div class="card">
									<div class="content">
										<div class="header">
											<h2>${reservation.service},  ${reservation.pet}</h2>
											<h5 style="margin-top: -10px">Vrijeme: ${reservation.time}</h5>

										</div>
										<div class="description">
											<div class="ui fluid accordion">
												<div class="title">
													<i class="dropdown icon"></i>
													<strong>Detalji rezervacije</strong>
												</div>
												<div class="content">
													<p>A dog is a type of domesticated animal. Known for its loyalty and faithfulness, it can be found as a welcome guest in many households across the world.</p>
												</div>
											</div>
										</div>
									</div>
									<form method="post" action="/jobs/${reservation.reservationId}/accept">
										<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
										<button class="ui bottom fluid attached submit button" tabindex="0">
											<i class="level up icon"></i>
											Prihvati
										</button>
										
									</form>


								</div>
							</c:forEach>
						</div>

					</div>
				</div>

			</div>


			<!-- druga kolona -->
			<div class="column">
				<div class="ui segments">
					<div class="ui segment">
						<h2>Prihvaćene rezervacije</h2>
					</div>
					<div class="ui segments" id="scrolling">
						<div class="ui cards wide">
							<c:forEach var="reservation" items="${accepted}">
							<div class="card">
								<div class="content">
									<div class="header">
										<h2>${reservation.service},  ${reservation.pet}</h2>
										<h5 style="margin-top: -10px">Vrijeme: ${reservation.time}</h5>

									</div>
									<div class="description">
										<div class="ui fluid accordion">
											<div class="title">
												<i class="dropdown icon"></i>
												<strong>Detalji rezervacije</strong>
											</div>
											<div class="content">
												<p>A dog is a type of domesticated animal. Known for its loyalty and faithfulness, it can be found as a welcome guest in many households across the world.</p>
											</div>
										</div>
									</div>
								</div>
								<form method="post" action="/jobs/${reservation.reservationId}/confirm">
									<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
									<button class="ui bottom fluid attached submit button" tabindex="0">
										<i class="checkmark icon"></i>
										Potvrdi
									</button>

								</form>


							</div>
						</c:forEach>
					</div>

				</div>
			</div>

		</div>

		<!-- treca kolona -->
		<div class="column">
			<div class="ui segments">
				<div class="ui segment">
					<h2>Potvrđene rezervacije</h2>
				</div>
				<div class="ui segments" id="scrolling">
					
					<div class="ui cards wide">
						<c:forEach var="reservation" items="${confirmed}">
						<div class="card">
							<div class="content">
								<div class="header">
									<h2>${reservation.service},  ${reservation.pet}</h2>
									<h5 style="margin-top: -10px">Vrijeme: ${reservation.time}</h5>

								</div>
								<div class="description">
									<div class="ui fluid accordion">
										<div class="title">
											<i class="dropdown icon"></i>
											<strong>Detalji rezervacije</strong>
										</div>
										<div class="content">
											<p>A dog is a type of domesticated animal. Known for its loyalty and faithfulness, it can be found as a welcome guest in many households across the world.</p>
										</div>
									</div>
								</div>
							</div>
							<form method="post" action="">
								<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
								<button class="ui bottom fluid attached submit disabled button" tabindex="0">
									<i class="thumbs up icon"></i>
									Završi
								</button>
							</form>



						</div>
					</c:forEach>
				</div>


			</div>
		</div>


	</div>


</div>

</div>

</main>
<%@ include file = "../partials/footer.jsp" %>
<script src="${pageContext.request.contextPath}/scripts/jobs.js"></script>
</body>
</html>


