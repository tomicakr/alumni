<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="../partials/head.jsp" flush="true">
	<jsp:param name="title" value="Login" />
	<jsp:param name="view-name" value="login" />
</jsp:include>

<body>
<main class="ui middle aligned center aligned grid">
	<div class="four wide column">
		<h1 class="ui image massive header">Dobrodošli nazad!</h1>
		<form method="post" action="/sessions/" class="ui form ">
			<div class="ui stacked primary  segment">
				<div class="field">
					<div class="ui left icon input">
						<i class="user icon"></i>
						<input type="text" name="email" placeholder="Adresa elektroničke pošte">
					</div>
				</div>
				<div class="field">
					<div class="ui left icon input">
						<i class="lock icon"></i>
						<input type="password" name="password" id="password" placeholder="Lozinka">
					</div>
				</div>
				<div class="ui fluid large darkred submit button">Login</div>
			</div>
			<div class="ui primary message">
				<h4>Nemate račun? <a class="item" name="Usluge" href="/users/new">Registirajte se</a> </h4>
			</div>
			<div class="ui error message"></div>
		</form>
	</div>
</main>
<script src="../../scripts/login.js"></script>
</body>

</html>