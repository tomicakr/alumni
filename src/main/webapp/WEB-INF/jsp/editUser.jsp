<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

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

    <sec:authentication var="userInSession" property="principal" />

    <body>

        <%@ include file="../partials/header.jsp" %>

        <div class="ui container">
            <form class="ui form" action="/users/${user.userId}/edit" method="post">
                <div class="field">
                    <label>Ime</label>
                    <input type="text" name="firstName" id="first-name" value="${user.firstName}">
                </div>
                <div class="field">
                    <label>Prezime</label>
                    <input type="text" name="lastName" id="last-name" value="${user.lastName}">
                </div>
                <div class="field">
                    <label>Broj telefona</label>
                    <input type="number" name="phone" id="phone-number" value="${user.phone}">
                </div>
                <div class="field">
                    <label>Email</label>
                    <input type="email" name="email" id="email" value="${user.email}">
                </div>
                <div class="field">
                    <label>Adresa</label>
                    <input type="text" name="address" id="address" value="${user.address}">
                </div>
                <div class="field">
                    <label>Datum rođenja</label>
                    <input type="date" name="birthday" id="birthday" value="${user.birthday}">
                </div>
                <div class="field">
                    <label>Datum diplomiranja</label>
                    <input type="date" name="graduation" id="graduation" value="${user.graduation}">
                </div>
                <button class="ui button" type="submit">Submit</button>
            </form>

            <spring:hasBindErrors name="editUserForm">
                <div class="ui error message">
                    <div class="header">
                        Imate neke pogreške
                    </div>
                    <ul class="list">
                        <c:forEach var="error" items="${errors.allErrors}">
                           <li><spring:message message="${error}" /></li>
                        </c:forEach>
                    </ul>
                </div>
            </spring:hasBindErrors>

            <script src="../../scripts/includes/handlebars-v4.0.11.js"></script>
            <script src="../../scripts/editUser.js"></script>
        </div>
    </body>

    </html>