<!DOCTYPE html>
<html>
<head>
	<title>Team106 - Java E-commerce</title>
</head>
	<body>
		<h1>E- Commerce Site</h1>
		<h2>Welcome</h2>
		
		<div id="results">
			<% if (request.getAttribute("results") != null) { %>
				<%= request.getAttribute("results") %>
			<% } %>
		</div>
	</body>
</html>