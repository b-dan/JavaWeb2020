<%@ page import="java.util.Date"%>
<%@ page import="lesson04.DBWork"%>
<%@include file="./includes/menu.jsp" %>
<%!long dateStart = 0;%>
<%!int counter = 0;
int timeCount = 20000;
%>

<center>


<%
String login = request.getParameter("f1");
String password = request.getParameter("f2");
boolean isShowForm=false;
StringBuilder result = new StringBuilder(); 
if(request.getParameter("logout")!=null){
	session.setAttribute("userName", null);
}
if(session.getAttribute("userName")!=null){
	isShowForm = false;
	%>
	<form id="loginForm" action="" method="post">
	<input type = "hidden" name = "logout"/>
	<input type="submit" value="logout"/>
	</form>
	<%
}else{

if(login==null && password==null&& dateStart==0){
	isShowForm = true;
	}else{
	if(login.length()==0 && password.length()==0){
		result.append("Please input value of login and password first");
		isShowForm = true;
		counter++;
	}else{
		out.write("Login: "+login+"<br>");
		out.write("Password: "+password+"<br>");
		DBWork dbWork = new DBWork();
		String nameUser = dbWork.getName(login, password);
		if(nameUser!=null){
			session.setAttribute("userName", nameUser);
			result.append("<div style='color:green'>Access granted!</div>");
			result.append("Hello, "+nameUser+"!");
		}
		else{
			result.append("<div style='color:red'>Access denied!</div>");
			isShowForm = true;
			counter++;
		}
	}
}


if(counter>=3){
	if(dateStart==0){
		dateStart = new Date().getTime();
	}
	isShowForm = false;
	Date currentTime = new Date();	
	long timeLeft = (dateStart+timeCount) - currentTime.getTime();
	
	if(currentTime.getTime()>=dateStart+timeCount){
		isShowForm = true;
		counter = 0;
		dateStart = 0;
	}else{
		result.append("Time left: "+(timeLeft/1000));
	}
	
}
}
out.write(result.toString());

	
if(isShowForm){
	
	%>
</center>
<body>
</form>

<form id="loginForm" action="" method="post">

	<div class="field">
		<label>Enter your login:</label>
		<div class="input"><input type="text" name="f1" value="" id="login" /></div>
	</div>

	<div class="field">
		<a href="#" id="forgot">Forgot your password?</a>
		<label>Enter your password:</label>
		<div class="input"><input type="password" name="f2" value="" id="pass" /></div>
	</div>

	<div class="submit">
		<button type="submit">Enter</button>
		<label id="remember"><input name="" type="checkbox" value="" /> Remember me</label>
	</div>

</form>
</body>
<%}%>