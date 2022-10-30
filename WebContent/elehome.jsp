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
}
</style>
</head>

<body>
<div>
<form class="form" id="signinf" action="AuthenticateEle" method="post" style="display:block">
			<h2 class="form__title">Election Commission Login</h2>
			
				EC Id:  <input class="input" type="email" name="userid" /><br>

				Password: <input class="input" type="password" name="password" /><br>

				<input class="btn" type="submit" value="Submit" /><br>
				
		</form>
<a href="ForgotPassword">Forgot Password ?</a>
</div>

		
</body>
</html>