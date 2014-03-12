	<!DOCTYPE html>
	<html>
	<head>
		<title>Team106 - Java E-commerce</title>
	</head>

	<body>
		<h1>E- Commerce Site</h1>
		<h2>Login System</h2>
		<form method="POST" name="connect" id="connect" action="/ECommerce/ecommserv" onSubmit="welcome.jsp"> 
			<label for="username" >User name: </label>
			<input type="text" name="username" size="30" required>
			<label for="password" >Password: </label>
			<input type="password" name="password" size="30" required>
			
			<input type="submit" name="login" />
		</form>
		
		<a href="register.jsp">Register</a>
		
		<div id="results">
			<% if (request.getAttribute("results") != null) { %>
				<%= request.getAttribute("results") %>
			<% } %>
		</div>
	</body>
	</html>