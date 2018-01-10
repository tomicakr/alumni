<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Korisnici" />
	<jsp:param name="view-name" value="users" />
</jsp:include>

<body>
	<%@ include file = "../partials/header.jsp" %>
		<main class="ui two column center aligned grid">
		<div class="column">
			<h1 class="ui image massive header">Korisnici aplikacije</h1>
			<table class="ui very padded table">
		<thead>
		<tr>
			<th>Ime</th>
			<th>Prezime</th>
			<th>E-mail adresa</th>
			<th>Uloga</th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.email}</td>
				<td>
				<c:choose>
					<c:when test="${user.roles[0].equals(\"ROLE_KORISNIK\")}">
						<a class="ui long tag label brown">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Klijent&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</c:when>
					<c:when test="${user.roles[0].equals(\"ROLE_ZAPOSLENIK\")}">
						<a class="ui long tag label orange">&nbsp;&nbsp;&nbsp;Zaposlenik&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</c:when>
					<c:when test="${user.roles[0].equals(\"ROLE_ADMINISTRATOR\")}">
						<a class="ui long tag label red">Administrator</a>
					</c:when>
					<c:otherwise>
						<a class="ui long tag label red">Greška</a>
					</c:otherwise>
				</c:choose>
				</td>
				<td><a href="${pageContext.request.contextPath}/users/${user.userId}/">Detalji</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</main> 	
<%@ include file = "../partials/footer.jsp" %>
</body>
</html>