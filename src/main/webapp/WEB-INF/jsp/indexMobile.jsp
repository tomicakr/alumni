<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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

<sec:authentication var="userInSession" property="principal" />

<body>

	<div class="ui basic icon top fixed menu">

		<div class="item" id="toggle">
			<i class="sidebar icon"></i>
		</div>
		<div class="right menu">

			<sec:authorize access="isAnonymous()">
				<a class="item" name="Login"
					href="${pageContext.request.contextPath}/sessions/new"> Prijava
				</a>
				<a class="item" name="singup"
					href="${pageContext.request.contextPath}/users/new">
					Registracija </a>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<a class=" item"
					href="${pageContext.request.contextPath}/users/${userInSession.userId}">
					Dobrodošli, ${userInSession.firstName}! </a>

				<form method="post"
					action="${pageContext.request.contextPath}/sessions/">
					<input type="hidden" name="_method" value="DELETE"> <input
						class="item" type="submit" value="Odjavi se" id="logoutBtn">
				</form>
			</sec:authorize>
		</div>
	</div>


	<aside>
		<div class="ui inverted sidebar vertical left menu">
			<h2 id="sidebarHeader">PetsOnlyZg</h2>

			<a class="item" id="home" name="Home" href="/"> <i
				class="home icon"></i> Naslovnica
			</a> <a class="item" id="services" name="Usluge"> <i
				class="empty heart icon"></i> Usluge
			</a>

			<sec:authorize access="isAuthenticated()">

				<c:if test="${userInSession.roles[0].equals(\"ROLE_ZAPOSLENIK\")}">
					<a class="item" name="Jobs"
						href="${pageContext.request.contextPath}/jobs"> <i
						class="industry icon"></i> Poslovi
					</a>
				</c:if>

				<c:if
					test="${userInSession.roles[0].equals(\"ROLE_ADMINISTRATOR\")}">
					<a class="item" name="Jobs"
						href="${pageContext.request.contextPath}/jobs"> <i
						class="industry icon"></i> Poslovi
					</a>
					<a class="item" name="Jobs"
						href="${pageContext.request.contextPath}/users"> <i
						class="users icon"></i> Korisnici
					</a>
				</c:if>
			</sec:authorize>

		</div>
	</aside>


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

	<div id="lowerMain"></div>

	<script>
		$('#toggle').click(function() {
			$('.ui.sidebar').sidebar('toggle');
		});
	</script>

</body>
</html>