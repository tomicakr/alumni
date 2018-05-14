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
              <div class="header">Products</div>
              <div class="menu">
                <a class="item">Enterprise</a>
                <a class="item">Consumer</a>
              </div>
            </div>
            <div class="item">
              <div class="header">CMS Solutions</div>
              <div class="menu">
                <a class="item">Rails</a>
                <a class="item">Python</a>
                <a class="item">PHP</a>
              </div>
            </div>
            <div class="item">
              <div class="header">Hosting</div>
              <div class="menu">
                <a class="item">Shared</a>
                <a class="item">Dedicated</a>
              </div>
            </div>
            <div class="item">
              <div class="header">Support</div>
              <div class="menu">
                <a class="item">E-mail Support</a>
                <a class="item">FAQs</a>
              </div>
            </div>
          </div>
        </div>

        <div id="posts" class="ui divided items">
          <script id="postsTemplate" type="text/x-handlebars-template">
        {{#each this}}
        <div class="item">
          <div class="content">
            <a class="header">{{title}}</a>
            <div class="meta">
              <span class="cinema">{{address}}</span>
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
                    <div class="ui comments">
                      <div class="ui header">Komentari</div>
                      {{#each comments}}
                      <div class="comment">
                        <div class="content">
                          <a class="author">{{user.name}}</a>
                          <div class="metadata">
                            <span class="date">{{date}}</span>
                          </div>
                        </div>
                      </div>
                      <div class="text">
                        {{message}}
                      </div>
                      {{/each}}
                      <form method="post" action="/posts/{{postId}}/comment" class="ui reply form">
                        <div class="header">Komentiraj</div>
                          <div class="field">
                            <textarea name="message"></textarea>
                          </div>
                          <button type="submit" class="ui blue labeled submit icon button">
                            <i class="icon edit"></i> Komentiraj
                          </button>
                      </form>
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



    <script src="../../scripts/index.js"></script>
    <script src="../../scripts/includes/handlebars-v4.0.11.js"></script>
</body>

</html>