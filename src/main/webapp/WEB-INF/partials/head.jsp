<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
	<meta charset="utf-8">
	<title><%=request.getParameter("title")%></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

	<script
	src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
	<!-- css -->
	<link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/styles/global.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/<%=request.getParameter("view-name")%>.css">
	<script>

		if(window.location.pathname == "${pageContext.request.contextPath}"+"/" || window.location.pathname == "/index"){

			if (screen.width <= 800  || $(window).width() <= 800) {
            window.location.href="${pageContext.request.contextPath}/mobile"; 

       		}
		}
        
	</script>
</head>