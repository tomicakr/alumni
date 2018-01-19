<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="../partials/head.jsp" flush="true">
    <jsp:param name="title" value="Korisnici"/>
    <jsp:param name="view-name" value="users"/>
</jsp:include>

<body>
<%@ include file = "../partials/sidebarBegin.jsp" %>
<%@ include file="../partials/header.jsp" %>
<main>
	<div class="ui container">
	        <h1 class   ="ui massive center aligned header">Korisnici aplikacije</h1>
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
	                                <p class="ui client long tag label">Klijent</p>
	                            </c:when>
	                            <c:when test="${user.roles[0].equals(\"ROLE_ZAPOSLENIK\")}">
	                                <p class="ui employee long tag label">Zaposlenik</p>
	                            </c:when>
	                            <c:when test="${user.roles[0].equals(\"ROLE_ADMINISTRATOR\")}">
	                                <p class="ui administrator long tag label">Administrator</p>
	                            </c:when>
	                            <c:otherwise>
	                                <p class="ui administrator long tag label">Greška</p>
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
<%@ include file="../partials/footer.jsp" %>
<%@ include file="../partials/sidebarEnd.jsp" %>

<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
</body>
</html>