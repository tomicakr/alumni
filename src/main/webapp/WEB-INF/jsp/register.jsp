<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

            <div class="ui top menu">
                <a class="item">
                    <h1>Alumni</h1>
                </a>
                <a href="/login" class="item">Prijava</a>
                <a href="/register" class="item">Registracija</a>
            </div>
            <form class="ui form">
                <div class="field">
                    <label>Ime</label>
                    <input type="text" name="first-name">
                </div>
                <div class="field">
                    <label>Prezime</label>
                    <input type="text" name="last-name">
                </div>
                <div class="field">
                    <label>Datum rođenja</label>
                    <input type="date" name="birthday">
                </div>
                <button class="ui button" type="submit">Submit</button>
            </form>



            <script src="../../scripts/index.js"></script>
            <script src="../../scripts/includes/handlebars-v4.0.11.js"></script>
        </body>

        </html>