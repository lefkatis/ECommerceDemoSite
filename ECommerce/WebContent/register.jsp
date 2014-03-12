	<!DOCTYPE html>
	<html>
	<head>
		<title>Team106 - Java E-commerce</title>
	</head>

	<body>
		<h1>E- Commerce Site</h1>
		<h2>Register System</h2>
		<form method="POST" name="connect" id="connect" action="/ECommerce/ecommserv">
			<label for="name" >First name: </label>
			<input type="text" name="name" size="30" required><br/>
			<label for="surname" >Last name: </label>
			<input type="text" name="surname" size="30" required><br/>
			<label for="username" >username: </label>
			<input type="text" name="username" size="30" required><br/>
			<label for="password" >password: </label>
			<input type="password" name="password" size="30" required><br/>
			<label for="confirm_password" >Confirm password: </label>
			<input type="password" name="confirm_password" size="30" required><br/>
			<input type="submit" name="register"/>
		</form>
		
		<div id="results">
			<% if (request.getAttribute("results") != null) { %>
				<%= request.getAttribute("results") %>
			<% } %>
		</div>
	</body>
	</html>