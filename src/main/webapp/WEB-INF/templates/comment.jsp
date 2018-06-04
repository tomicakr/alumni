<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script id="commentTemplate" type="text/x-handlebars-template">
    <div id="{{ commentId }}"class="comment">
        <div class="content">
            <a class="author">{{username}}</a>
            <div class="metadata">
                <span class="date">{{formatTime date "DD-MM-YYYY, HH:mm"}}</span>
                <sec:authorize access="hasRole('ADMINISTRATOR')">
                    <a title="Obriši komentar" onclick="deleteComment('{{ commentId }}')">
                        <i class="trash icon"></i>
                    </a>
                </sec:authorize>
            </div>
        </div>
        <div class="text">
            {{message}}
        </div>
    </div>
</script>