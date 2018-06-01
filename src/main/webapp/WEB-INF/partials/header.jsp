<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="userInSession" property="principal" />
<div class="ui top stackable menu">
    <a href="/" class="item">
        <h1>Alumni</h1>
    </a>
    <div class="ui dropdown item">
        <i class="linkify icon"></i>
        Poveznice
        <i class="dropdown icon"></i>
        <div class="menu" id="links">
        </div>
    </div>
    <sec:authorize access="hasRole('ADMINISTRATOR')">
        <div class="ui dropdown item">
            <i class="linkify icon"></i>
            Admin
            <i class="dropdown icon"></i>
            <div class="menu">
                <a class="item" href="/posts/newPost">
                    <i class="book icon"></i>Novi post</a>
                <a class="item" href="/posts/archive">
                    <i class="archive icon"></i>Arhiva postova</a>
                </a>
                <a class="item" href="/users">
                    <i class="users icon"></i>Korisnici</a>
                <a class="item" href="/categories">
                    <i class="cubes icon"></i>Kategorije</a>
                <a class="item" href="/links">
                    <i class="magic icon"></i>Stranica poveznica</a>
            </div>
        </div>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <a href="/login" class="item">Prijava</a>
        <a href="/register" class="item">Registracija</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div class="ui dropdown item right">
            <i class="user icon"></i>
            ${userInSession.firstName}
            <i class="dropdown icon"></i>
            <div class="menu">
                <a class="item" href="/users/${userInSession.userId}">
                    <i class="address card icon"></i>Profil</a>
                <a class="item" href="/logout">
                    <i class="user times icon"></i>Odjava </a>
            </div>
        </div>
    </sec:authorize>
    <script src="../../scripts/header.js"></script>
</div>