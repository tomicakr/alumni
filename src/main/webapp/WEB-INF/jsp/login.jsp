<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" flush="true">
<jsp:param name="title" value="Login" />
</jsp:include>

<main>
	<header>This is Login Page!</header>
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
