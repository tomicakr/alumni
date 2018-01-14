<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Naslovnica</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/indexMobile.css" />

</head>

<body>

<%@ include file = "../partials/headerMobile.jsp" %>


	<main>
	<div class="pusher" id="mainContent">
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
						<a
							href="${pageContext.request.contextPath}/users/${userInSession.userId}/reservations/new">Naruči
							Uslugu</a>
					</button>
				</sec:authorize>

			</div>
		</div>
	</div>
	</main>

	<div id="lowerMain">
	</div>

	<script>
		$('#toggle').click(function() {
			$('.ui.sidebar').sidebar('toggle');
		});
	</script>

</body>
</html>