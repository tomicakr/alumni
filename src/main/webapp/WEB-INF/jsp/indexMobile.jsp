<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

	<jsp:include page="../partials/head.jsp" flush="true">
		<jsp:param name="title" value="Naslovnica" />
		<jsp:param name="view-name" value="indexMobile" />
	</jsp:include>

<body>

	<%@ include file = "../partials/sidebar.jsp" %>
	
<div class="pusher" id="main-content">
	<%@ include file = "../partials/topFixedMobileMenu.jsp" %>

	<div class=" ui container">
		<div id="heading">

			<h1>
				<i class="fa fa-paw" aria-hidden="true"> </i> Pets Only Zagreb
			</h1>

			<sec:authorize access="isAnonymous()">
				<button class="ui inverted button">
					<a href="${pageContext.request.contextPath}/sessions/new">Naruči
					Uslugu</a>
				</button>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<button class="ui inverted button">
					<a href="${pageContext.request.contextPath}/users/${userInSession.userId}/reservations/new">Naruči Uslugu</a>
				</button>
			</sec:authorize>

		</div>
	</div>

	<div id="lower-main"></div>

<!-- sidebar close div -->
</div>

<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
</body>
</html>