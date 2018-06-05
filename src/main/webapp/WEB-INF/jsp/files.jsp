<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Alumni - Popis datoteka</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="../../scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
</head>

<body>
    <%@ include file="../partials/header.jsp" %>
        <div class="ui container">
            <div class="ui compact menu inverted">
                <sec:authorize access="hasRole('ADMINISTRATOR')">
                    <a class="item" href="/files/newFile">
                        <i class="plus icon"></i>Nova datoteka
                    </a>
                </sec:authorize>
            </div>
            <h1 class="ui massive center aligned header">Repozitorij datoteka</h1>
           
            <c:if test="${!files.isEmpty()}">
            <table class="ui very padded table">

                    <thead>
                        <tr>
                            <th>Naslov</th>
                            <th>Opis</th>
                            <th>Naziv datoteke</th>
                        <th>Tip</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="file" items="${files}">
                        <tr>
                            <td>${file.title}</td>
                            <td>${file.description}</td>
                            <td>${file.name}</td>
                            <td>${file.type}</td>
                            <td>
                                <sec:authorize access="isAuthenticated()">
                                    <form action="/files/${file.fileId}" method="get">
                                        <button class="active green item" href="" title="Preuzmi" type="submit">
                                            <i class="download icon"></i>
                                        </button>
                                    </form>
                                </sec:authorize>
                            </td>
                            <sec:authorize access="hasRole('ADMINISTRATOR')">
                                <td>
                                    <div class="ui compact menu inverted">
                                        <a class="active green item" title="Uredi" href="/files/${file.fileId}/editFile">
                                            <i class="edit icon"></i>
                                        </a>
                                        <form action="/files/${file.fileId}/delete" method="post">
                                            <button class="active red item" href="" title="Obriši" type="submit">
                                                <i class="trash icon"></i>
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        </div>
        <script type="text/javascript" src="../../scripts/includes/global.js"></script>
        <script type="text/javascript" src="../../scripts/file.js"></script>
</body>

</html>