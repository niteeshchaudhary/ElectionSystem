<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login/Sign Up</title>
<style>
form{
padding:1rem;
margin:1rem;
}
input{
padding:1rem;
margin:1rem;
border-radius:1rem;
width:30rem;
}
input[type='submit']{
width:10rem;
}
label{
padding:1rem;
margin:1rem;
width:30rem;
font-size:1.3rem;
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
<div>
<form class="form" id="signinf" action="AuthenticateEle" method="post" style="display:block">
			<h1 class="form__title">Election Commission Login</h1>
			
				<label>EC Id:</label>  <br><input class="input" type="email" name="userid" /><br>

				<label>Password:</label> <br><input class="input" type="password" name="password" /><br>

				<input class="btn" type="submit" value="Submit" /><br>
				
		</form>
<label><a href="ForgotPassword">Forgot Password ?</a></label>
</div>

		
</body>
</html>