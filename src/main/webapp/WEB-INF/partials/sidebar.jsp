<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>



<div id="sidebar-menu" class="ui sidebar vertical left menu">
	<h2 id="sidebar-header">PetsOnlyZg</h2>

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

