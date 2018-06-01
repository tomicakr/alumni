<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Alumni - Popis linkova</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="../../scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
</head>

<body>
    <%@ include file="../partials/header.jsp" %>
    <div class="ui container">
        <div class="ui segment">
            <div class="ui right rail">
                <div class="ui vertical huge menu">
                    <div class="item">
                        <form action="/links/new" method="get">
                            <button type="submit" class="ui secondary button">Novi link</button>
                        </form>
                    </div>
                </div>
            </div>

            <h1 class="ui massive center aligned header">Linkovi</h1>
                <table class="ui very padded table">
                    <thead>
                        <tr>
                            <th>Naziv</th>
                            <th>Url</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="link" items="${links}">
                            <tr>
                                <td>${link.title}</td>
                                <td><a href="${link.url}">${link.url}</a></td>
                                <td>
                                    <a href="" onclick="deleteLink('${link.linkId}')">Obriši</a> |
                                    <a href="${pageContext.request.contextPath}/links/${link.linkId}/edit">Uredi</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/link.js"></script>
</body>

</html>