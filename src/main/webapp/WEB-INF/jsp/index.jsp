
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" flush="true">
<jsp:param name="title" value="Home" />
</jsp:include>

<main>
	<h1>Dobrodosli na Pets Only Zg stranicu!</h1>
	<div>
		${user.name }
		${user.surname }
	</div>
</main>

<%@ include file = "footer.jsp" %>