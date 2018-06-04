<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script id="postsTemplate" type="text/x-handlebars-template">
    {{#each this}}
    <div class="item">
    
        <div class="content">
            <a class="header" href="/posts/{{postId}}">{{title}}</a>
            <div class="meta">
                <span class="cinema">Adresa: {{address}}</span>
            </div>
            <div class="meta">
                <span class="cinema">Kreirano: {{ formatTime createDate "DD-MM-YYYY, HH:mm" }}</span>
                <span class="cinema">Zadnje modificirano: {{ formatTime modifyDate "DD-MM-YYYY, HH:mm" }}</span>
            </div>
            <div class="meta">
                Kategorije:
                {{#each postCategories}}
                <button onclick="filterBy('{{ this.name }}')" class="ui label"> {{ this.name }}</button>
                {{/each}}
            </div>
            <div class="description">
                <p>{{shortDescription}}</p>
            </div>
            <sec:authorize access="hasRole('ADMINISTRATOR')">
                <div class="extra">
                    {{#unless archived}}
                        <div class="item">
                            <button onclick="archivePost('{{postId}}')" class="ui tiny right floated red button">Arhiviraj</button>
                        </div>
                    {{/unless}}
                    <div class="item">
                        <button onclick="deletePost('{{postId}}')" class="ui tiny right floated red button">Obriši</button>
                    </div>
                    <div class="item">
                        <form action="/posts/{{postId}}/edit" method="get">
                            <button type="submit" class="ui tiny right floated default button">Uređivanje posta</button>
                        </form>
                    </div>
                </div>
            </sec:authorize>
            <a href="/posts/{{postId}}">Više informacija</a>
            <div class="item">
                <div class="ui accordion">
                    <div class="active title">
                        <i class="dropdown icon"></i>
                        Komentari...
                    </div>
                    <div class="content">
                        <div class="ui comments">
                            <div id="{{ postId }}-comment">
                                {{#each comments}}
                                <div id="{{commentId}}" class="comment">
                                    <div class="content">
                                        <a class="author">{{username}}</a>
                                        <div class="metadata">
                                            <span class="date">{{formatTime date "DD-MM-YYYY, HH:mm"}}</span>
                                            <sec:authorize access="hasRole('ADMINISTRATOR')">
                                                <a title="Obriši komentar" onclick="deleteComment('{{ ../postId }}', '{{ commentId }}')">
                                                    <i class="trash icon"></i>
                                                </a>
                                            </sec:authorize>
                                        </div>
                                    </div>
                                    <div class="text">
                                        {{message}}
                                    </div>
                                </div>
                                {{/each}}
                            </div>
                            <sec:authorize access="isAuthenticated()">
                                <div class="ui reply form">
                                    <div class="fields">
                                        <div class="ten wide field">
                                            <textarea id="{{postId}}-commentMessage" name="message"></textarea>
                                        </div>
                                        <div class="four wide field">
                                            <button onclick="comment('{{postId}}')" class="ui blue labeled submit icon button">
                                                <i class="icon edit"></i> Komentiraj
                                            </button>
                                        </div>
                                    </div>
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
    {{/each}}
</script>