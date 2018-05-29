<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/global.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/index.css">
    <script type="text/javascript">
        if (screen.width <= 800 || $(window).width() <= 800) {
            window.location.href = "${pageContext.request.contextPath}/mobile";
        }
    </script>
</head>
<sec:authentication var="userInSession" property="principal" />

<body id="sticky-context">
    <%@ include file="../partials/header.jsp" %>
        <div class="ui container">
            <div class="ui segment">
                <div class="ui left rail">
                    <div class="ui vertical huge menu">
                        <div class="item">
                            <div class="header">Linkovi</div>
                            <div class="menu" id="links">
                                <a class="item" href="https://suza.fer.hr">ŠUZA</a>
                                <a class="item" href="https://www.fer.unizg.hr">FER</a>
                                <a class="item" href="https://www.estudent.hr">E-STUDENT</a>
                            </div>
                        </div>
                        <div class="item">
                            <div class="header">Filtriranje</div>
                            <div class="menu">
                                <a class="item" href="/">Sve</a>
                                <a class="item" onclick="filterBy('EVENT')">Događaji</a>
                                <a class="item" onclick="filterBy('LECTURE')">Predavanja</a>
                                <a class="item" onclick="filterBy('INFO')">Informacije</a>
                            </div>
                        </div>
                    </div>
                </div>
                <sec:authorize access="hasRole('ADMINISTRATOR')">
                    <div class="ui right rail">
                        <div class="ui vertical huge menu">
                            <div class="item">
                                <div class="header">Administratorski panel</div>
                                <form action="/posts/newPost" method="get">
                                    <button type="submit" class="ui secondary button">Novi post</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </sec:authorize>
                <div id="posts" class="ui divided items">
                <script id="postsTemplate" type="text/x-handlebars-template">
                {{#each this}}
                <div class="item">
                
                    <div class="content">
                        <a class="header">{{title}}</a>
                        <div class="meta">
                            <span class="cinema">Adresa: {{address}}</span>
                        </div>
                        <div class="meta">
                            <span class="cinema">Kreirano: {{ formatTime createDate "DD-MM-YYYY, HH:mm" }}</span>
                            <span class="cinema">Zadnje modificirano: {{ formatTime modifyDate "DD-MM-YYYY, HH:mm" }}</span>
                        </div>
                        <div class="meta">
                            <span class="cinema">Tip: {{ postTypeConversion postType }}</span>
                        </div>
                        <div class="description">
                            <p>{{shortDescription}}</p>
                        </div>
                        <div class="item">
                            <div class="ui accordion">
                                <div class="active title">
                                    <i class="dropdown icon"></i>
                                    Više...
                                </div>
                                <div class="content">
                                    <p>{{longDescription}}</p>
                                    <hr>
                                    <div class="ui comments">
                                        {{#each comments}}
                                        <div class="comment">
                                            <div class="content">
                                                <a class="author">{{user.name}} {{user.surname}}</a>
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
                                        <sec:authorize access="hasRole('ADMINISTRATOR')">
                                            <form action="/posts/{{postId}}/delete" method="post">
                                                <button type="submit" class="ui secondary button">Obriši</button>
                                            </form>
                                            <form action="/posts/{{postId}}/edit" method="get">
                                                <button type="submit" class="ui secondary button">Uređivanje posta</button>
                                            </form>
                                        </sec:authorize>
                                    </div>
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
</body>

</html>