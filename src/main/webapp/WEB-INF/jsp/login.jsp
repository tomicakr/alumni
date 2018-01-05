<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="head.jsp" flush="true">
<jsp:param name="title" value="Login" />
</jsp:include>

<body>
	<main class="ui middle aligned center aligned grid">
		<article>
			<header>Login Here!</header>
			<form method="post" action="/sessions/">
				<ul>
					<li><label>Email</label></li>
					<input type="text" name="email"/>

					<li><label>Password</label></li>
					<input type="password" name="password"/>

					<li>
						<button type="submit" value="Login">Login</button>
						<button href="reg.jsp">Register Here</button>
					</li>				
				</ul>
			</form>
		</article>
	</main>

	<%@ include file = "footer.jsp" %>
</body>
</html>