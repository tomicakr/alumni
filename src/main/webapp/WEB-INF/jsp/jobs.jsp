<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Rezervacije" />
	<jsp:param name="view-name" value="jobs" />
</jsp:include>
</head>

<body>
	<%@ include file = "../partials/headerMobile.jsp" %>

	<div id="main-div" class="ui segment">


		<h1 align="center">Pregled svih rezervacija: </h1>
		<div id="main-grid" class="ui stackable column centered grid">

			<!-- prva kolona -->
			<div  id="col1" class="four wide column">
				<div class="ui segments">
					<div class="ui segment">
						<h3>Nove rezervacije</h3>
					</div>
					<div class="ui segments" id="scrolling">

						<div class="ui cards wide">								
						<c:forEach var="reservation" items="${open}">
						<div class="card">
							<div class="content">
								<div class="header">
									<h4 style="color: darkred">${reservation.service},  ${reservation.pet}</h4>
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

							<sec:authorize access="hasAuthority('ACCEPT_RESERVATION')">
							<form method="post" action="/jobs/${reservation.reservationId}/accept">
								<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
								<button class="ui darkred bottom fluid attached submit button" tabindex="0">
									<i class="level up icon"></i>
									Prihvati
								</button>

							</form>
							</sec:authorize>

						</div>
					</c:forEach>
					
				</div>

			</div>
		</div>

	</div>


	<!-- druga kolona -->
	<div id="col2" class="four wide column">
		<div class="ui segments">
			<div class="ui segment">
				<h3>Prihvaćene rezervacije</h3>
			</div>
			<div class="ui segments" id="scrolling">
				<div class="ui cards wide">
					<c:forEach var="reservation" items="${accepted}">
					<div class="card">
						<div class="content">
							<div class="header">
								<h4 style="color: darkred">${reservation.service},  ${reservation.pet}</h4>
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

						<sec:authorize access="hasAuthority('CONFIRM_RESERVATION')">
							<form method="post" action="/jobs/${reservation.reservationId}/confirm">
								<input type="hidden" name="reservationId" value="${reservation.reservationId}" />
								<button class="ui bottom darkred fluid attached submit button" tabindex="0">
									<i class="checkmark icon"></i>
									Potvrdi
								</button>
							</form>
						</sec:authorize>

					</div>
				</c:forEach>
			</div>

		</div>
	</div>

</div>

<!-- treca kolona -->
<div id="col3" class="four wide column">
	<div class="ui segments">
		<div class="ui segment">
			<h3>Potvrđene rezervacije</h3>
		</div>
		<div class="ui segments" id="scrolling">

			<div class="ui cards wide">
				<c:forEach var="reservation" items="${confirmed}">
				<div class="card">
					<div class="content">
						<div class="header">
							<h4 style="color: darkred">${reservation.service},  ${reservation.pet}</h4>
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
						<button class="ui bottom darkred fluid attached submit disabled button" tabindex="0">
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


<%@ include file = "../partials/footer.jsp" %>
<script src="${pageContext.request.contextPath}/scripts/jobs.js"></script>
</body>
</html>


