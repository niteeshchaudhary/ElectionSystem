<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EC Dashboard</title>
<style>
form{
padding:1rem;
margin:1rem;
}
input{
padding:1rem;
margin:1rem;
border-radius:1rem;
width:10rem;
}
	body {
    background-image: url('https://cdn.dnaindia.com/sites/default/files/styles/full/public/2019/05/24/827021-election-representation-image-5.jpg');
    color: #FFFFFF;
      background-repeat: no-repeat;
      background-size: cover;
}

</style>
</head>
<body>
<form action="CreateElections">
<input type="submit" value="Create Election"/>
</form>
<form action="ActiveElections">
<input type="submit" value="Active Election"/>
</form>
<form action="PreviousElections">
<input type="submit" value="Previous Election"/>
</form>
		<form  method="post">
			<input type="submit"  formaction="Logout" value="Log Out"/>
		</form>
</body>
</html>