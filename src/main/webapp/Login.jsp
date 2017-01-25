<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="user-scalable=0, width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>StarFlag Bank</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/mobile.css">
<script type='text/javascript' src='js/mobile.js'></script>
<script type='text/javascript' src='js/validate.js'></script>
</head>
<body>
	<div id="header">
		<h1>
			<a href="#">Mango Bank</span></a>
		</h1>

	</div>
	<div id="body">
		<h3><% if(request.getAttribute("error")!=null)
		{ 	out.println((String)request.getAttribute("error"));}
		%></h3>
		<h2>Login</h2>
		<form name="Login" method="post" action="StarFlag"
			onsubmit="return validateLoginForm()">
			<label for="username"> <span>username</span> <input
				type="text" name="username" id="username">
			</label> <label for="password"> <span>password</span> <input
				type="password" name="password" id="password">
			</label> <input type="submit" id="submit" name="submit" value="Login">
		</form>
		<h2>Register</h2>
		<form name="Register" method="post" action="StarFlag"
			onsubmit="return validateRegisterForm()">
			<label for="name"> <span>Name</span> <input type="text"
				name="name" id="name">
			</label> <label for="email"> <span>Email</span> <input type="email"
				name="email" id="email">
			</label> <label for="password"> <span>password</span> <input
				type="password" name="password" id="password">
			</label> <label for="confirmpassword"> <span>confirm password</span>
				<input type="password" name="confirmpassword" id="confirmpassword">
			</label> <label for="Address"> <span>Address</span> <input
				type="text" name="address" id="address">
			</label> <label for="openBalance"> <span>open Balance</span> <input
				type="text" name="open" id="openBalance">
			</label> <input type="submit" id="submit" name="submit" value="Register">
		</form>
	</div>
	<div id="footer">
		<div>
			<span>303 E Kearsley St, Flint, MI 48502 </span> <span><p>
					&copy; 2023 by StarFlag Bank. All rights reserved.</p></span>
		</div>
	</div>
</body>
</html>