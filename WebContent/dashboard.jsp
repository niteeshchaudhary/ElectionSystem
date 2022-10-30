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
			body {
    background-image: url('https://codeelections.com/wp-content/uploads/slider/cache/18b07f700aa800631903d52004f386fe/Img-1028-x-487-06.jpg');
    color: #FFFFFF;
      background-repeat: no-repeat;
      background-size: cover;
}
input{
border-radius:2rem;
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
		<h1>Welcome! ${name}</h1>
		<h3>your id: ${userid}</h3>
		<form  method="get">
			<input type="submit" formaction="UpcomingElections" value="Upcoming Elections"/>
			<input type="submit" formaction="LiveElections" value="Live Elections"/>
			<input type="submit" formaction="PreviousElections" value="Past Elections"/>
		</form>
		<form  method="post">
			<input type="submit"  formaction="Logout" value="Log Out"/>
		</form>
	</body>
</html>