<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Alumni - Novi post</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <script src="../../scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
</head>

<body>

    <%@ include file="../partials/header.jsp" %>

    <div class="ui container">
        <h1>Kreiranje novog posta</h1>
        <div class="ui info message">
            <i class="close icon"></i>
            <div class="header">
                Napomena
            </div>
                Polja označena sa zvjezdicom(*) su obavezna
        </div>
        <form method="post" action="/posts/newPost" class="ui form ">
            <div class="field">
                <label>Naslov*</label>
                <input type="text" name="title" value="${postForm.title}">
            </div>
            <div class="field">
                <label>Adresa</label>
                <input type="text" name="address" value="${postForm.address}">
            </div>
            <div class="field">
                <label>Kratki opis*</label>
                <textarea rows="2" name="shortDescription">${postForm.shortDescription}</textarea>
            </div>
            <div class="field">
                <label>Dugi opis</label>
                <div id="wysihtml5-toolbar" style="display: none;">
                    <button data-wysihtml5-command="bold" title="PODEBLJANO" class="command"><i class="bold icon"></i></button>
                    <button data-wysihtml5-command="italic" class="command"><i class="italic icon"></i></button>
                    <button data-wysihtml5-command="insertUnorderedList" class="command"><i class="list ul icon"></i></button>
                    <button data-wysihtml5-command="insertOrderedList" title="Insert an ordered list" class="command"><i class="list ol icon"></i></button>
                    <button data-wysihtml5-command="createLink" title="Insert a link" class="command"><i class="linkify icon"></i>                    </button>
                    <button data-wysihtml5-command="insertImage" title="Insert an image" class="command"><i class="file image icon"></i></button>
                    <button data-wysihtml5-command="formatBlock" data-wysihtml5-command-value="h1" title="Insert headline 1" class="command">h1</i></button>
                    <button data-wysihtml5-command="formatBlock" data-wysihtml5-command-value="h2" title="Insert headline 2" class="command">h2</button>
                        
                    <button data-wysihtml5-command="insertSpeech" title="Insert speech" class="command"><i class="volume up icon"></i></button>
                    <button data-wysihtml5-action="change_view" title="Show HTML" class="action"><i class="hashtag icon"></i></button>
                    <div data-wysihtml5-dialog="createLink" style="display: none;">
                    <label>
                        Link:
                        <input data-wysihtml5-dialog-field="href" value="http://">
                    </label>
                    <a data-wysihtml5-dialog-action="save">OK</a>&nbsp;<a data-wysihtml5-dialog-action="cancel">Cancel</a>
                    </div>
                        
                    <div data-wysihtml5-dialog="insertImage" style="display: none;">
                        <label>
                            Image:
                            <input data-wysihtml5-dialog-field="src" value="http://">
                        </label>
                        <a data-wysihtml5-dialog-action="save">OK</a>&nbsp;<a data-wysihtml5-dialog-action="cancel">Cancel</a>
                    </div>
                <textarea id="wysihtml5-textarea" name="longDescription">${postForm.longDescription}</textarea>
            </div>
            <div class="field">
                <label>Kategorije*</label>
                <select id="categories" multiple="" class="ui fluid dropdown" name="postCategories">
                </select>
            </div>
            <button class="ui button" type="submit">Objavi</button>
            <a class="ui button" href="/">Odustani</a>
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
    <!-- wysihtml5 parser rules -->
    <script src="/vendor/wysi/parser_rules/advanced.js"></script>
    <!-- Library -->
    <script src="/vendor/wysi/dist/wysihtml5-0.3.0.min.js"></script>
    <script src="../../scripts/newPost.js"></script>
    <script type="text/javascript" src="../../scripts/includes/global.js"></script>

</body>

</html>