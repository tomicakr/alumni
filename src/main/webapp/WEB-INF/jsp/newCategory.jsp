<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Alumni - Unos nove kategorije</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <script src="../../scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
</head>

<body>

    <%@ include file="../partials/header.jsp" %>

    <div class="ui container">
        <h1>Unos nove kategorije</h1>
        <div class="ui info message">
            <i class="close icon"></i>
            <div class="header">
                Napomena
            </div>
                Polja označena sa zvjezdicom(*) su obavezna
        </div>
        <form method="post" action="/categories/new" class="ui form ">
            <div class="field">
                <label>Naziv*</label>
                <input type="text" name="name" value="${categoryForm.name}">
            </div>
           
            <button class="ui button" type="submit">Unesi</button>
            <a class="ui button" href="/categories">Odustani</a>
        </form>
        <spring:hasBindErrors name="categoryForm">
            <div class="ui error message">
                <div class="header">
                    Imate neke pogreške
                </div>
                <ul class="list">
                    <c:forEach var="error" items="${errors.allErrors}">
                        <li>
                            <spring:message message="${error}" />
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </spring:hasBindErrors>
    </div>

    <script src="../../scripts/category.js"></script>
    <script type="text/javascript" src="../../scripts/includes/global.js"></script>

</body>

</html>