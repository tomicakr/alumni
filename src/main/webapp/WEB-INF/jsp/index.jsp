<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Pets Only Zg</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>

    <script src="../../scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
    <!-- css -->
    <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/styles/global.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/index.css">
    <script type="text/javascript">
            if (screen.width <= 800  || $(window).width() <= 800) {
                window.location.href="${pageContext.request.contextPath}/mobile";

            }
    </script>
</head>

<sec:authentication var="userInSession" property="principal" />
<body>
	<%@ include file = "../partials/indexHeader.jsp" %>
	<main>
		
		<div id="heading">
			<h1><i class="paw icon"></i> Pets Only Zagreb</h1>
			<sec:authorize access="isAnonymous()">
			<button class="ui inverted button" ><a href="${pageContext.request.contextPath}/sessions/new" >Naruči Uslugu</a></button>
		</sec:authorize>

		<sec:authorize access="isAuthenticated()">
		<button class="ui inverted button"><a href="${pageContext.request.contextPath}/users/${userInSession.userId}/reservations/new">Naruči Uslugu</a></button>
	</sec:authorize>
</div>
</main>
<ul class="slideshow preload">
	<li></li>
	<li></li>
	<li></li>
	<li></li>
	<li></li>
</ul>
<script src="../../scripts/index.js"></script>
</body>
</html>