<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="../partials/head.jsp" flush="true">
<jsp:param name="title" value="Register" />
</jsp:include>

<body>
	<main class="ui middle aligned center aligned grid">
		<div class="column">
			<h1 class="ui image header">
				Izradite svoj račun
			</h1>
			<form class="ui large form segment stacked">
				<div class="two fields">
					<div class="field">
						<input type="text" name="first-name" id="first-name" placeholder="Ime">
					</div>
					<div class="field">
						<input type="text" name="last-name" id="last-name" placeholder="Prezime">
					</div>
				</div>
				<div class="field">
					<input type="text" name="oib" id="oib" placeholder="OIB">
				</div>
				<div class="field">
					<input type="tel" name="phone" id="phone" placeholder="Broj telefona">
				</div>
				<div class="field">
					<input type="email" name="email" id="email" placeholder="Adresa elektroničke pošte">
				</div>
				<div class="field">
					<input type="text" name="address" id="address" placeholder="Adresa stavnovanja">
				</div>
				<div class="field">
					<input type="password" name="password" id="password" placeholder="Lozinka">
				</div>
				<div class="field">
					<input type="password" name="password2" id="password2" placeholder="Ponovite lozinku">
				</div>
				<i id="pass-match" class="fa large" aria-hidden="true"></i>
				<div class="ui fluid huge darkred submit button" style="width: 100%">Registriraj se!</div>
			</form>
			<button class="ui huge button" id="komba">Skombaj mi sve podatke</button>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/scripts/register.js">
	</script>
</body>
</html>