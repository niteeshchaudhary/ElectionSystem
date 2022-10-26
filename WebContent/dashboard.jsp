<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dash board</title>
		<style>
			input{
			margin:1rem;
			padding:1rem;
			}
		</style>
	</head>
	<body>
		<%
		String userid = (String)session.getAttribute("userid");
		String username = (String)session.getAttribute("name");
		if(username==null || userid==null) 
			response.sendRedirect("home.jsp");
		%>
		<h1>Welcome! ${userid}</h1>
		<form action="UpcomingElections" method="post">
			<input type="submit" formaction="UpcomingElections" value="Upcoming Elections"/>
			<input type="submit" formaction="LiveElections" value="Live Elections"/>
			<input type="submit" formaction="PreviousElections" value="Past Elections"/>
			<input type="submit" value="Log Out"/>
		</form>
	</body>
</html>