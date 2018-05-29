<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
            </head>

            <body>
                <%@ include file="../partials/header.jsp" %>
                    <div class="ui container">
                        <spring:hasBindErrors name="commentForm">
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
                        <div class="ui items">
                            <div class="item">
                                <div class="content">
                                    <a class="header">${post.title}</a>
                                    <div class="description">
                                        <p>${post.shortDescription}</p>
                                    </div>
                                    <hr>
                                    <div class="description">
                                        <p>${post.longDescription}</p>
                                    </div>
                                    <hr>
                                    <div class="ui comments">
                                        <c:forEach items="${comments}" var="comment">
                                            <div class="comment">
                                                <div class="content">
                                                    <a class="author">${comment.user.firstName} ${comment.user.lastName}</a>
                                                    <div class="metadata">
                                                        <span class="date">${comment.date}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="text">
                                                ${comment.message}
                                            </div>
                                        </c:forEach>
                                        <sec:authorize access="isAuthenticated()">
                                            <form method="post" action="/posts/${post.postId}/comment" class="ui reply form">
                                                <div class="header">Komentiraj</div>
                                                <div class="field">
                                                    <textarea name="message"></textarea>
                                                </div>
                                                <button type="submit" class="ui blue labeled submit icon button">
                                                    <i class="icon edit"></i> Komentiraj
                                                </button>
                                            </form>
                                        </sec:authorize>
                                        <sec:authorize access="isAnonymous()">
                                            <div class="ui small message">
                                                <a href="/register">Registrirajte se</a> ili
                                                <a href="login">prijavite</a> kako biste mogli komentirati.
                                            </div>
                                        </sec:authorize>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script src="../../scripts/profile.js"></script>
            </body>

            </html>