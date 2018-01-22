<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<!-- head -->
<jsp:include page="../partials/head.jsp" flush="true">
    <jsp:param name="title" value="Login"/>
    <jsp:param name="view-name" value="login"/>
</jsp:include>

<body>
<div class="ui middle aligned center aligned grid">
    <div class="column">
        <form method="post" action="/sessions/" class="ui large form ">
            <h1 class="ui massive image header">Dobrodošli nazad!</h1>
            <div class="ui stacked primary  segment">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" name="username" placeholder="Adresa elektroničke pošte" autofocus>
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" id="password" placeholder="Lozinka">
                    </div>
                </div>
                <div class="ui fluid large darkred submit button">Login</div>
            </div>
            <div class="ui primary message">
                <h4>Nemate račun? <a class="item" name="Usluge" href="${pageContext.request.contextPath}/users/new">Registirajte
                    se</a></h4>
            </div>
            <c:if test="${not empty errorMessage}">
                <div class="ui error message visible">${errorMessage}</div>
            </c:if>
        </form>
    </div>
</div>
<%@ include file="../partials/footer.jsp" %>
<script src="../../scripts/forms.js"></script>
t
<script src="../../scripts/login.js"></script>
</body>

</html>