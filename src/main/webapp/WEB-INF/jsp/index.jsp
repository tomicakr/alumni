<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Home" />
	<jsp:param name="view-name" value="index"/>
</jsp:include>

<body>
	<%@ include file = "../partials/header.jsp" %>

	<main style="text-align: center;">
		<h1>Dobrodosli na Pets Only Zg stranicu!</h1>
		
		<c:if test="${not empty user.userId}">
		<h4>Sad za sad nek tu stoji: <a class="item" name="Usluge" href="/users/${user.userId}/reservations/new">Dodaj novu rezervaciju!</a></h4>
		</c:if>
		
	</main>
	
	<%@ include file = "../partials/footer.jsp" %>
</body>
</html>