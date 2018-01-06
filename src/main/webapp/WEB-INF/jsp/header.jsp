<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<header>
	<div class="ui centered grid" style="margin-top: 10px">
		<img src="/images/logo1.png" height="100px" width="350px">
	</div>
</header>
<nav>
	<div class="ui secondary pointing menu" id="customMenu">
		<a class="item" name="Home" href="/">
			Home
		</a>
		<a class="item" name="Usluge">
			Usluge
		</a>
		<a class="item" name="Cjenik">
			Cjenik
		</a>
		<a  class="item" name="Forum">
			Forum
		</a>
		<div class="right menu">
			<c:if test="${empty user.name}">
			<a  class="item" name="Login" href="/sessions/new">
			Login
			</a>
		    </c:if>

			<c:if test="${not empty user.name}">
			<a  class=" item">
			Dobrodošli, ${user.name }!
			</a>
			
			<form method="post" action="/sessions/">
			<input class="item" type="hidden" name="_method" value="DELETE">
			<input class="item" type="submit" value="Logout">
			</form>
			</c:if>

		</div>   	
</div>
</nav>

