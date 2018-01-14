<%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<sec:authentication var="userInSession" property="principal" />

<nav>
	<div class="ui massive stackable secondary menu" id="customMenu">
		<div class="left menu">
			<a class="item" id="home" name="Home" href="/"> Naslovnica </a> <a
			class="item" id="services" name="Usluge"> Usluge </a>

			<sec:authorize access="hasRole('ZAPOSLENIK')">
			<a class="item" name="Jobs"
			href="${pageContext.request.contextPath}/jobs"> Poslovi </a>
			</sec:authorize>

			<sec:authorize access="hasRole('ADMINISTRATOR')">
				<a class="item" name="Jobs"
				href="${pageContext.request.contextPath}/jobs"> Poslovi </a>
				<a class="item" name="Jobs"
				href="${pageContext.request.contextPath}/users"> Korisnici </a>
			</sec:authorize>

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
				<input class="item" type="hidden" name="_method" value="DELETE">
				<input class="item" type="submit" value="Odjavi se">
				</form>
			</sec:authorize>

	</div>
</div>
</nav>

