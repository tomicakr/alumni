<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

<jsp:attribute name="head">
<title>Login</title>

</jsp:attribute>

<jsp:body>
<header>This is Login Page!</header>
<main>
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
</jsp:body>

</t:genericpage>
