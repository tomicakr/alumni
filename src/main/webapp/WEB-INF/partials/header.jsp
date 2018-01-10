<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<header>
    <style type="text/css">
        #logoutBTN:hover{
            background-color: #e0e4e5;
        }
        #logoutBTN{
            margin-top: 3px;
        }
    </style>

</header>
<nav>
    <div class="ui massive stackable secondary menu" id="customMenu">
        <a class="item" id="home" name="Home" href="/">
            Naslovnica
        </a>
        <a class="item" id="services" name="Usluge">
            Usluge
        </a>
        <c:if  test="${userInSession.roles[0].equals(\"ROLE_ZAPOSLENIK\")}">
            <a  class="item" name="Jobs" href="${pageContext.request.contextPath}/jobs">
                Poslovi
            </a>
        </c:if>
        <c:if  test="${userInSession.roles[0].equals(\"ROLE_ADMINISTRATOR\")}">
            <a  class="item" name="Jobs" href="${pageContext.request.contextPath}/jobs">
                Poslovi
            </a>
            <a  class="item" name="Jobs" href="${pageContext.request.contextPath}/users">
                Korisnici
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
                    <input class="item" type="submit" value="Odjavi se" id="logoutBTN">
                </form>
            </c:if>

        </div>
    </div>
</nav>

