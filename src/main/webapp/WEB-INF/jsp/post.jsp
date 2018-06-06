<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <%@ include file="../templates/comment.jsp" %>
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
        <div class="ui container">
            <sec:authorize access="hasRole('ADMINISTRATOR')">
                <div class="ui compact menu inverted">
                    <a class="active green item" title="Uredi" href="/posts/${post.postId}/edit">
                        <i class="edit icon"></i>
                    </a>
                    <a class="active red item" href="" title="Obriši" onclick="deletePost('${post.postId}')">
                        <i class="trash icon"></i>
                    </a>
                    <c:if test="${!post.archived}">
                        <a class="active red item" href="" title="Arhiviraj" onclick="archivePost('${post.postId}')">
                            <i class="archive icon"></i>
                        </a>
                    </c:if>
                </div>
            </sec:authorize>
            <c:if test="${post.archived}">
                <span class="ui red massive label"> ARHIVIRAN </span>
            </c:if>
            <div class="ui segment">
                <div class="item">
                    <div class="content">
                        <a class="ui massive header">${post.title}</a>
                        <c:if test="${post.picture.pictureId != null}">
                            <img class="ui centered medium image" src="/pictures/${post.picture.pictureId}">
                        </c:if>
                        <c:if test="${post.address != ''}">
                            <div class="meta">
                                <span class="cinema">Adresa: ${post.address}</span>
                            </div>
                        </c:if>
                        <div class="meta">
                            <span class="cinema">Kreirano:
                                <fmt:formatDate value="${post.createDate}" pattern="dd-MM-yyyy, HH:mm"
                                />
                            </span>
                            <span class="cinema">Zadnje modificirano:
                                <fmt:formatDate value="${post.modifyDate}" pattern="dd-MM-yyyy, HH:mm"
                                />
                            </span>
                        </div>
                        <div class="meta">
                            Kategorije:
                            <c:forEach var="postcategory" items="${post.postCategories}">
                                <span class="ui label" id="postType">${postcategory.name}</span>
                            </c:forEach>
                        </div>
                        <div class="description">
                            <p>${post.shortDescription}</p>
                        </div>
                        <hr>
                        <div class="description">
                            <p>${post.longDescription}</p>
                        </div>
                        <hr>
                        <div class="ui comments">
                            <div id="${post.postId}-comment">
                                <c:forEach items="${comments}" var="comment">
                                    <div id="${comment.commentId}" class="comment">
                                        <div class="content">
                                            <a class="author">${comment.username}</a>
                                            <div class="metadata">
                                                <span class="date">
                                                    <fmt:formatDate value="${comment.date}" pattern="dd-MM-yyyy, HH:mm"
                                                    />
                                                </span>
                                                <sec:authorize access="hasRole('ADMINISTRATOR')">
                                                    <a title="Obriši komentar" onclick="deleteComment('${comment.commentId}')">
                                                        <i class="trash icon"></i>
                                                    </a>
                                                </sec:authorize>
                                            </div>
                                        </div>
                                        <div class="text">
                                            ${comment.message}
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <sec:authorize access="isAuthenticated()">
                                <div class="ui reply form">
                                    <div class="header">Komentiraj</div>
                                    <div class="field">
                                        <textarea id="${post.postId}-commentMessage" name="message"></textarea>
                                    </div>
                                    <button onclick="comment('${post.postId}')" class="ui blue labeled submit icon button">
                                        <i class="icon edit"></i> Komentiraj
                                    </button>
                                </div>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <div class="ui small message">
                                    <a href="/register">Registrirajte se</a> ili
                                    <a href="/login">prijavite</a> kako biste mogli komentirati.
                                </div>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="../../scripts/includes/moment.js"></script>
    <script type="text/javascript" src="../../scripts/includes/handlebars-v4.0.11.js"></script>
    <script type="text/javascript" src="../../scripts/includes/global.js"></script>
    <script type="text/javascript" src="../../scripts/post.js"></script>
</body>

</html>