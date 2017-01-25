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
</head>
<body>
	<input type=hidden name="flow_controller" value="Login">
	<div id="header">
		<h1>
			<a href="index.html">Mango Bank</a>
		</h1>
		<ul id="navigation">
			<li class="current"><a href="homepage.jsp">Home</a></li>
			<li><a href=#>Banking</a>
				<ul>
					<li><a href="StarFlag?flowcontroller=Deposit">Deposit</a></li>
					<li><a href="StarFlag?flowcontroller=Withdraw">Withdrawal</a></li>
					<li><a href="StarFlag?flowcontroller=Balance">CheckBalance</a></li>
				</ul></li>
			<li><a href="StarFlag?flowcontroller=logout">Logout</a></li>
		</ul>
	</div>
	<div id="body">
		<%
			if(session.getAttribute("account")==null){response.sendRedirect("Login.jsp");}
			if (request.getAttribute("result") == null) {
		%>
		<div id="tagline">
			<h1>Banking</h1>
			<p>For Better savings</p>
		</div>
		<img src="images/banking.jpg" alt="Banking" class="figure">
		<%
			} else if ("Balance".equals(request.getAttribute("result"))) {
		%><h1><%=request.getAttribute("message")+" "+request.getAttribute("balance") %></h1>
		<%
			}else if("withdraw".equals(request.getAttribute("result"))|| "deposit".equals(request.getAttribute("result")) ){
		%><form name=<%=request.getAttribute("result") %> method="post" action="StarFlag">
			<label for=<%=request.getAttribute("result") %>> <span><%=request.getAttribute("result") %> Amount</span> <input type="text"
				name="amount" id="amount"></label>
				 <input type="submit" id="submit" name="submit" value=<%=request.getAttribute("result")%>>
				</form><%} %>

	</div>
	<div id="footer">
		<div>
			<span>303 E Kearsley St, Flint, MI 48502 </span> <span><p>
					&copy; 2023 by StarFlag Bank. All rights reserved.</p></span>
		</div>
	</div>
</body>
</html>