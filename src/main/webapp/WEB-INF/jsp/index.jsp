<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="head.jsp" flush="true">
<jsp:param name="title" value="Home" />
</jsp:include>

<body>
	<%@ include file = "header.jsp" %>

	<main>
		<h1 style="text-align: center;">Dobrodosli na Pets Only Zg stranicu!</h1>
		<div>
			${user.name }
			${user.surname }
		</div>
	</main>

	<%@ include file = "footer.jsp" %>
</body>
</html>