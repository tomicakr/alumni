<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Alumni - Uređivanje posta</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <script src="../../scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
</head>

<body>
    <%@ include file="../partials/header.jsp" %>
        <div class="ui container">
            <h1>Uređivanje posta</h1>
            <form method="post" action="/posts/${postForm.postId}/edit" class="ui form ">
                <div class="field">
                    <label>Naslov</label>
                    <input type="text" name="title" value="${postForm.title}">
                </div>
                <div class="field">
                    <label>Adresa</label>
                    <input type="text" name="address" value="${postForm.address}">
                </div>
                <div class="field">
                    <label>Kratki opis</label>
                    <textarea rows="2" name="shortDescription">${postForm.shortDescription}</textarea>
                </div>
                <div class="field">
                    <label>Dugi opis</label>
                    <textarea name="longDescription">${postForm.longDescription}</textarea>
                </div>

                <div class="field">
                    <label>Tip</label>
                    <input type="hidden" id="selectValue" value="${postForm.postType}">
                    <select id="select" class="ui dropdown" name="postType">
                        <option value="EVENT">Događaj</option>
                        <option value="LECTURE">Predavanje</option>
                        <option value="INFO">Informacija</option>
                    </select>
                </div>

                <button class="ui button" type="submit">Ažuriraj</button>
                <a class="ui button" href="/posts/${postForm.postId}">Odustani</a>
            </form>

            
            <spring:hasBindErrors name="postForm">
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

        <script src="../../scripts/editPost.js"></script>
</body>

</html>