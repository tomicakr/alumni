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
			<button class="ui inverted button" id="btn-order-service">Naruči Uslugu</button>
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