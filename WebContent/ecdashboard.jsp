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
</body>
</html>