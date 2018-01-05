
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><%=request.getParameter("title")%></title>
	<script
	src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
	<link rel = "stylesheet" type = "text/css" href = "/css/style.css" />
</head>
<body>
	<header>
		<div class="ui centered grid" style="margin-top: 10px">
			<img src="/images/logo1.png" height="100px" width="350px">
		</div>
	</header>

	<nav>
		<div class="ui secondary pointing menu" id="customMenu">
			<a class="item" name="Home" href="/">
				Home
			</a>
			<a class="item" name="Usluge">
				Usluge
			</a>
			<a class="item" name="Cjenik">
				Cjenik
			</a>
			<a  class="item" name="Forum">
				Forum
			</a>
			<div class="right menu">
				<a  class="item" name="Login" href="/sessions/new">
					Login
				</a>
				<a  class="item" name="Logout">
					Logout
				</a>
			</div>   	
		</div>
	</nav>

	