<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<body>
    <%@ include file="../partials/header.jsp" %>

    <div class="ui container">
        <form method="post" action="/login" class="ui large form ">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" name="username" id="email" placeholder="Adresa elektroničke pošte" autofocus>
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" id="password" placeholder="Lozinka">
                    </div>
                </div>
                <button class="ui button" type="submit">Prijava</button>
            <c:if test="${not empty errorMessage}">
                <div class="ui error message visible">${errorMessage}</div>
            </c:if>
        </form>
    </div>

    <script src="../../scripts/login.js"></script>
</body>

</html>