<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
    <meta charset="utf-8">
    <title><%=request.getParameter("title")%>
    </title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="${pageContext.request.contextPath}/scripts/jQuery/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/semantic/dist/semantic.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/vendor/calendar/dist2/calendar.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/semantic/dist/semantic.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/calendar/dist2/calendar.min.css"/>
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/global.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/styles/<%=request.getParameter("view-name")%>.css">
</head>