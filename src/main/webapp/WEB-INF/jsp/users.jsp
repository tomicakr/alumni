<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Users</title>
</head>
<body>
	<h1>Lista svih korisnika: </h1>

	<br><br>
	
	<ul>
	 	<c:forEach var="user" items="${users}">
	 		<li>${user.name }	${user.surname }</li>
		</c:forEach>
	</ul>

	
</body>
</html>