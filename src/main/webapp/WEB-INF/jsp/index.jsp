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
    <%@ include file="../templates/post.jsp" %>
    <%@ include file="../templates/comment.jsp" %>
        <div class="ui container">
            <div class="ui dropdown item">
                <i class="filter icon"></i>
                <span id="ftext">Sve</span>
                <i class="dropdown icon"></i>
                <div class="menu" id="categories">
                    <a class="item" onclick="filterBy(null)">
                        <i class="clipboard list icon"></i>Svi postovi
                    </a>
                </div>
            </div>
            <div class="ui segment">
                <span id="pager"></span>
                <div id="posts" class="ui divided items">
                    
                </div>
            </div>
        </div>
        <script src="../../scripts/includes/moment.js"></script>
        <script src="../../scripts/includes/handlebars-v4.0.11.js"></script>
        <script src="../../scripts/index.js"></script>
        <script src="../../scripts/post.js"></script>
        <script type="text/javascript" src="../../scripts/includes/paginate.js"></script>
        <script type="text/javascript" src="../../scripts/includes/global.js"></script>
</body>

</html>