<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
	String error = (String) session.getAttribute("msg");
	String createError = (String) session.getAttribute("createMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
	font-family: Verdana;
	color: black;
}

.split {
	height: 100%;
	width: 50%;
	position: absolute;
	z-index: 1;
	top: 0;
	overflow-x: hidden;
}

.left {
	left: 0;
}

.right {
	right: 0;
}

.centered {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	text-align: left;
}

.vline {
	position: absolute;
	top: 10%;
	left: 50%;
	border-right: 4px solid grey;
	height: 80%;
}
</style>
<title>Battleship! - Login</title>
</head>
<body>
	<!-- button >Home</button> -->

	<div class="split left">
	<p><a href="/">Battleship</a></p>
		<div class="centered">
			<h1>Login</h1>
			<h2>Username</h2>
			<input style="border-color: black;" type="text" name="username" />
			<h2>Password</h2>
			<input style="border-color: black;" type="password" name="password" />
			<br> 
			<input type="submit" name="login" value="Login" />
			<% if(error != null) { %>
					<div style="color:red"><%= error %></div>
				<% session.removeAttribute("msg");				
			}%>
		</div>
	</div>

	<div class="vline"></div>

	<div class="split right">
		<div class="centered">
			<h1>Create Account</h1>
			<h2>Username</h2>
			<input style="border-color: black;" type="text" name="username" />
			<h2>Password</h2>
			<input style="border-color: black;" type="text" name="password" /> 
			<input type="submit" name="create" value="Create Account" />
			<% if(createError != null) {
				%>
					<div style="color:red"><%= createError %></div>
				<% session.removeAttribute("createMsg");
				}%>
		</div>
	</div>
</body>
</html>