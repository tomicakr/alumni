<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Alumni</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="../../scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/global.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/index.css">
    <script type="text/javascript">
        if (screen.width <= 800 || $(window).width() <= 800) {
            window.location.href = "${pageContext.request.contextPath}/mobile";
        }
    </script>
</head>
<body>
<%@ include file="../partials/header.jsp" %>
    <div class="ui container">
        <h1 class="ui massive center aligned header">Korisnici aplikacije</h1>
        <table class="ui very padded table">
            <thead>
            <tr>
                <th>Ime</th>
                <th>Prezime</th>
                <th>E-mail adresa</th>
                <th>Uloga</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.roles[0].equals(\"ROLE_KORISNIK\")}">
                                <p class="ui client long tag label">Korisnik</p>
                            </c:when>
                            <c:when test="${user.roles[0].equals(\"ROLE_MODERATOR\")}">
                                <p class="ui employee long tag label">Moderator</p>
                            </c:when>
                            <c:when test="${user.roles[0].equals(\"ROLE_ADMINISTRATOR\")}">
                                <p class="ui administrator long tag label">Administrator</p>
                            </c:when>
                            <c:otherwise>
                                <p class="ui administrator long tag label">Greška</p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/users/${user.userId}/">Detalji</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

<script src="${pageContext.request.contextPath}/scripts/global.js"></script>
</body>
</html>