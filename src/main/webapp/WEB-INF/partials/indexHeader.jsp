<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<header>
    <!--<div class="ui centered grid" style="margin-top: 10px">
		<img src="${pageContext.request.contextPath}/images/logo1.png" height="100px" width="350px">
	</div>-->
</header>
<nav>
    <div class="ui massive secondary menu" id="customMenu">
        <a class="item" id="home" name="Home" href="/">
            Kontakt
        </a>
        <a class="item" id="services" name="Usluge">
            Usluge
        </a>
        <c:if  test="${userInSession.roles[0].equals(\"ROLE_ZAPOSLENIK\")}">
        <a  class="item" name="Jobs" href="${pageContext.request.contextPath}/jobs">
            Poslovi
        </a>
         </c:if> 

    <div class="right menu">
        <c:if test="${empty userInSession.userPid}">
        <a  class="item" name="Login" href="${pageContext.request.contextPath}/sessions/new">
            Prijava
        </a>
        <a  class="item" name="singup" href="${pageContext.request.contextPath}/users/new">
            Registracija
        </a>
    </c:if>

    <c:if test="${not empty userInSession.firstName}">
    <a  class=" item" href="${pageContext.request.contextPath}/users/${userInSession.userId}">
        Dobrodošli, ${userInSession.firstName}!
    </a>

    <form method="post" action="${pageContext.request.contextPath}/sessions/">
        <input class="item" type="hidden" name="_method" value="DELETE">
        <input class="item" type="submit" value="Logout">
    </form>
</c:if>

</div>
</div>
</nav>

