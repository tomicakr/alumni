<<<<<<< HEAD
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Alumni - Registracija</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <script src="../../scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
</head>

<body>

    <%@ include file="../partials/header.jsp" %>

    <div class="ui container">
        <form class="ui form" action="/register" method="post">
            <div class="field">
                <label>Ime</label>
                <input type="text" name="firstName" id="first-name" value="${registrationForm.firstName}">
            </div>
            <div class="field">
                <label>Prezime</label>
                <input type="text" name="lastName" id="last-name" value="${registrationForm.lastName}">
            </div>
            <div class="field">
                <label>Broj telefona</label>
                <input type="number" name="phone" id="phone-number" value="${registrationForm.phone}">
            </div>
            <div class="field">
                <label>Email</label>
                <input type="email" name="email" id="email" value="${registrationForm.email}">
            </div>
            <div class="field">
                <label>Adresa</label>
                <input type="text" name="address" id="address" value="${registrationForm.address}">
            </div>
            <div class="field">
                <label>Datum rođenja</label>
                <input type="date" name="birthday" id="birthday" value="${registrationForm.birthday}">
            </div>
            <div class="field">
                <label>Datum diplomiranja</label>
                <input type="date" name="graduation" id="graduation" value="${registrationForm.graduation}">
            </div>
            <div class="field">
                <label>Lozinka</label>
                <input type="password" name="password" id="password" value="${registrationForm.password}">
            </div>
            <div class="field">
                <label>Ponovi lozinku</label>
                <input type="password" name="passwordConfirm" id="password-confirm" value="${registrationForm.passwordConfirm}">
            </div>
            <button class="ui button" type="submit">Submit</button>
        </form>

        <spring:hasBindErrors name="registrationForm">
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

        <script src="../../scripts/register.js"></script>
    </div>
</body>

</html>
=======
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
            <form class="ui form" action="/register" method="post">
                <div class="field">
                    <label>Ime</label>
                    <input type="text" name="firstName" id="first-name" value="${registrationForm.firstName}">
                </div>
                <div class="field">
                    <label>Prezime</label>
                    <input type="text" name="lastName" id="last-name" value="${registrationForm.lastName}">
                </div>
                <div class="field">
                    <label>Broj telefona</label>
                    <input type="number" name="phone" id="phone-number" value="${registrationForm.phone}">
                </div>
                <div class="field">
                    <label>Email</label>
                    <input type="email" name="email" id="email" value="${registrationForm.email}">
                </div>
                <div class="field">
                    <label>Adresa</label>
                    <input type="text" name="address" id="address" value="${registrationForm.address}">
                </div>
                <div class="field">
                    <label>Datum rođenja</label>
                    <input type="date" name="birthday" id="birthday" value="${registrationForm.birthday}">
                </div>
                <div class="field">
                    <label>Datum diplomiranja</label>
                    <input type="date" name="graduation" id="graduation" value="${registrationForm.graduation}">
                </div>
                <div class="field">
                    <label>Lozinka</label>
                    <input type="password" name="password" id="password" value="${registrationForm.password}">
                </div>
                <div class="field">
                    <label>Ponovi lozinku</label>
                    <input type="password" name="passwordConfirm" id="password-confirm" value="${registrationForm.passwordConfirm}">
                </div>
                <button class="ui button" type="submit">Submit</button>
            </form>

            <spring:hasBindErrors name="registrationForm">
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
            <script src="../../scripts/register.js"></script>
        </div>
    </body>

    </html>
>>>>>>> 3bcf26512a1a8adf6f997a55f5f812e44b8d47c8
