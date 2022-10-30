<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login/Sign Up</title>

<link href="home_style.css" rel='stylesheet'/>
<style>
form{
padding:1rem;
margin:1rem;
background-color: rgba(230,200,200,0.6);
height:40rem;
border-radius:1rem;
}
input{
padding:1rem;
margin:1rem;
width:20rem;
border-radius:0.2rem;
}
input[type="submit"]{
padding:1rem;
margin:1rem;
width:22.1rem;
border-radius:0.2rem;
}
	body {
    background-image: url('https://wallpaperaccess.com/full/101140.jpg');
      background-repeat: no-repeat;
      background-size: cover;
}
label{
padding:1rem;
font-size:1.3rem;
color:rgb(20,20,20);
  -webkit-text-stroke: 1px black;
}
</style>
</head>

<body>
<div style="display:flex;padding:1rem;gap:3rem">
<form class="form" id="signinf" action="Authenticate" method="post" style="display:block">
			<h2 class="form__title">User Login</h2><br><br>
			
				<label>User Id:</label>  <br><input class="input" type="email" name="userid" /><br>

				<label>Password:</label> <br><input class="input" type="password" name="password" /><br>

				<input class="btn" type="submit" value="Submit" /><br>
				<a href="ForgotPassword">Forgot Password ?</a>
		</form>
<form class="form"  id="signupf" action="Signup" method="post" style="display:block">
			<h2 class="form__title">User SignUp</h2><br><br>
			
				<label>User Id:</label>  <br><input class="input" type="email" name="userid" /><br>

				<label>Password:</label><br> <input class="input" type="password" name="password" /><br>
				<label>Confirm Password:</label><br> <input class="input" type="password" name="cpassword" /><br><br>

				<input class="btn" type="submit" value="Submit" /><br><br><br><br>
		</form>
</div>
		<button onclick="toggler()" id="changer">Sign In-></button>
		<script type="text/javascript">
			var cnt=1;
			const sni=document.getElementById("signinf");
			const snu=document.getElementById("signupf");
			const changer=document.getElementById("changer");
			function toggler(){
				cnt+=1;
				if(cnt%2==0){
					sni.style.display="block";
					snu.style.display="none";
					changer.innerHTML="Sign Up ->";
				}
				else{
					sni.style.display="none";
					snu.style.display="block";
					changer.innerHTML="Sign In ->";
				}
			}
		</script>
		
</body>
</html>