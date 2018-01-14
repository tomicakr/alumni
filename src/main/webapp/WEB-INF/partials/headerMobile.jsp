<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<sec:authentication var="userInSession" property="principal" />
	<div id="sidebar-top" class="ui basic icon top fixed menu">

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
		<div id="sidebar-menu" class="ui sidebar vertical left menu">
			<h2 id="sidebarHeader">PetsOnlyZg</h2>

			<a class="item" id="home" name="Home" href="/"> <i
				class="home icon"></i> Naslovnica
			</a> <a class="item" id="services" name="Usluge"> <i
				class="empty heart icon"></i> Usluge
			</a>

			<sec:authorize access="hasRole('ZAPOSLENIK')">
			<a class="item" name="Jobs"
			href="${pageContext.request.contextPath}/jobs"> Poslovi </a>
			</sec:authorize>

			<sec:authorize access="hasRole('ADMINISTRATOR')">
				<a class="item" name="Jobs"
				href="${pageContext.request.contextPath}/jobs"> Poslovi 
			 <i class="industry icon"></i></a>
				<a class="item" name="Jobs"
				href="${pageContext.request.contextPath}/users"> Korisnici 
				<i class="users icon"></i></a>
			</sec:authorize>

		</div>
	</aside>
