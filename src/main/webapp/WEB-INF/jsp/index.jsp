<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Alumni - Početna</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="../../scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
    <style>
        html {
        font-size: 15px;
        }
        #content div {
			float: left;
			width: 33%;
		}
		.pager-link {
			padding: 2px;
		}
		.pager-link-selected {
			color: #FFF;
			background-color: #333;
		}
    </style>
</head>

<body>
    <%@ include file="../partials/header.jsp" %>
        <div class="ui container">
            <div class="ui segment">
                <div class="ui left massive rail">
                    <div class="ui vertical huge menu">
                        <div class="item">
                            <div class="header">Linkovi</div>
                            <div class="menu" id="links">
                            </div>
                        </div>
                        <div class="item">
                            <div class="header">Filtriranje</div>
                            <div id="categories" class="menu">
                                <a class="item" onclick="filterBy(null)">Sve</a>
                            </div>
                        </div>
                    </div>
                </div>
                <sec:authorize access="hasRole('ADMINISTRATOR')">
                    <div class="ui right massive rail">
                        <div class="header">Administratorski panel</div>
                        <div class="ui vertical huge menu">
                            <div class="item">
                                <form action="/posts/newPost" method="get">
                                    <button type="submit" class="ui secondary button">Novi post</button>
                                </form>
                            </div>
                            <div class="item">
                                <button type="submit" onclick="getArchived()" class="ui secondary button">Arhiva postova</button>
                            </div>
                            <div class="item">
                                <form action="/users" method="get">
                                    <button type="submit" class="ui secondary button">Korisnici</button>
                                </form>
                            </div>
                            <div class="item">
                                <form action="/categories" method="get">
                                    <button type="submit" class="ui secondary button">Kategorije</button>
                                </form>
                            </div>
                            <div class="item">
                                <form action="/links" method="get">
                                    <button type="submit" class="ui secondary button">Linkovi</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </sec:authorize>
                <span id="pager"></span>
                <div id="posts" class="ui divided items">
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
                                        <div class="item">
                                            <button onclick="archivePost('{{postId}}')" class="ui tiny right floated red button">Arhiviraj</button>
                                        </div>
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
                                                {{#each comments}}
                                                <div class="comment">
                                                    <div class="content">
                                                        <a class="author">{{user.firstName}} {{user.lastName}}</a>
                                                        <div class="metadata">
                                                            <span class="date">{{formatTime date "DD-MM-YYYY, HH:mm"}}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="text">
                                                    {{message}}
                                                </div>
                                                {{/each}}
                                                <sec:authorize access="isAuthenticated()">
                                                    <form method="post" action="/posts/{{postId}}/comment" class="ui reply form">
                                                        <div class="fields">
                                                            <div class="ten wide field">
                                                                <textarea name="message"></textarea>
                                                            </div>
                                                            <div class="four wide field">
                                                                <button type="submit" class="ui blue labeled submit icon button">
                                                                    <i class="icon edit"></i> Komentiraj
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </form>
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
                </div>
            </div>
        </div>
        <script src="../../scripts/includes/moment.js"></script>
        <script src="../../scripts/includes/handlebars-v4.0.11.js"></script>
        <script src="../../scripts/index.js"></script>
        <script src="../../scripts/post.js"></script>
        <script type="text/javascript" src="../../scripts/includes/paginate.js"></script>
</body>

</html>