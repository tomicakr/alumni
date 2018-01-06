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
	<h1>Korisnici aplikacije:</h1>
<main class=" ui middle aligned center  aligned grid	">
	<table class="ui celled table">
		<thead>
		<tr><th>Ime</th>
			<th>Prezime</th>
			<th>E-mail</th>
			<th>Uloga</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>Klijent</td><!--Sva logika mora biti u kontroleru, ovdje zelimo imati objekt koji je obavio sve i slozio model-->
				<td>tu.dolazi@email.com</td>
				<td><a href="${pageContext.request.contextPath}/users/nekiId/">Detalji</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</main>
</body>
</html>