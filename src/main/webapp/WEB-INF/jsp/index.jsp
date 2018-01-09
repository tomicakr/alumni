<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="../partials/head.jsp" flush="true">
<jsp:param name="title" value="Home" />
<jsp:param name="view-name" value="index"/>
</jsp:include>

<body>
	<%@ include file = "../partials/indexHeader.jsp" %>
	<main>
		<div id="heading">
			<h1><i class="fa fa-paw" aria-hidden="true"> </i> Pets Only Zagreb</h1>
			<c:if test="${empty userInSession.userPid}">

               <button class="ui inverted button" ><a href="${pageContext.request.contextPath}/sessions/new" >Naruči Uslugu</a></button>
            </c:if>

            <c:if test="${not empty userInSession.firstName}">
                <button class="ui inverted button"><a href="${pageContext.request.contextPath}/users/${userInSession.userId}/reservations/new">Naruči Uslugu</a></button>
            </c:if>
		</div>
	</main>
	<ul class="slideshow">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<script src="scripts/index.js"></script>
</body>
</html>