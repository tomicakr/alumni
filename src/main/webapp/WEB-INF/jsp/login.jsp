<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="head.jsp" flush="true">
<jsp:param name="title" value="Login" />
</jsp:include>

<body>
	<main class="ui middle aligned center aligned grid">
		<article>

			<header><h1>Login Here!</h1></header>
			<h4 style="color: red">${errorMessage }</h4>
			<form method="post" action="/sessions/">
				<ul>
					<li><label>Email</label></li>
					<input type="text" name="email"/>

					<li><label>Password</label></li>
					<input type="password" name="password"/>

					<li>
						<button type="submit" value="Login">Login</button>
					</li>
					
					<li>
						<h4>Nemate svoj profil? <a class="item" name="Usluge" href="/users/new">Registriraj se!</a> </h4>
					</li>				
				</ul>
			</form>
		</article>
	</main>
	<%@ include file = "footer.jsp" %>
</body>
</html>