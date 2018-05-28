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
                    <div class="ui segment">
                        <div class="ui right rail">
                            <div class="ui vertical huge menu">
                                <div class="item">
                                    <form action="/users/${user.userId}/edit" method="post">
                                        <button type="submit" class="ui secondary button">Uredi podatke</button>
                                    </form>
                                </div>
                                <div class="item">
                                    <button onclick="deleteUser('${user.userId}')" class="ui secondary button">Obriši račun</button>
                                </div>
                            </div>
                        </div>
                        <table class="ui celled striped table">
                            <tr>
                                <th>Ime</th>
                                <td>${user.firstName}</td>
                            </tr>
                            <tr>
                                <th>Prezime</th>
                                <td>${user.lastName}</td>
                            </tr>
                            <tr>
                                <th>E-mail</th>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <th>Adresa</th>
                                <td>${user.address}</td>
                            </tr>
                            <tr>
                                <th>Broj Mobitela</th>
                                <td>${user.phone}</td>
                            </tr>
                            <tr>
                                <th>Datum rođenja</th>
                                <td>${user.birthday}</td>
                            </tr>
                            <tr>
                                <th>Datum diplomiranja</th>
                                <td>${user.graduation}</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <script src="../../scripts/profile.js"></script>
        </body>

        </html>