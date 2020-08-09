<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>
<%@include file="./includes/menu.jsp" %>
<%@ page import="lesson04.DBWork"%>

<%
Pattern pattern = null;
Matcher matcher = null;
boolean showForm = true;
boolean isError = false;
StringBuilder errorText = new StringBuilder();
errorText.append("<ul>");

String login = request.getParameter("login");
String password = request.getParameter("password");
String rePassword = request.getParameter("rePassword");
String name = request.getParameter("name");
String gender = request.getParameter("gender");
String address = request.getParameter("address");
String comment = request.getParameter("comment");
String agree = request.getParameter("agree");

if(login!=null){
	if(login.isEmpty()){
		errorText.append("<li>The 'login' is empty.</li>");
		isError = true;
	}else{
		pattern = Pattern.compile("^(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})$");
		matcher = pattern.matcher(login);
		if(!matcher.matches()){
			errorText.append("<li>The 'login' must be an email.</li>");
			isError=true;
		}
	}
	if(password.isEmpty()){
		errorText.append("<li>The 'password' is empty.</li>");
		isError = true;
	}
	if(rePassword.isEmpty()){
		errorText.append("<li>The 'rePassword' is empty.</li>");
		isError = true;
	}else{
		if(!password.equals(rePassword)){
			errorText.append("<li>The 'password' and 'rePassword' are not equals.</li>");
			isError = true;
		}
		else{
			pattern = Pattern.compile("^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})$");
			matcher = pattern.matcher(password);
			if(!matcher.matches()){
				errorText.append("<li>Your 'password' do not fit the requirements.</li>");
				isError = true;
			}
		}
	}
	if(name.isEmpty()){
		errorText.append("<li>The 'name' is empty.</li>");
		isError = true;
	}
	if(gender.isEmpty()){
		errorText.append("<li>The 'gender' is empty.</li>");
		isError = true;
	}
	if(address.isEmpty()){
		errorText.append("<li>The 'address' is empty.</li>");
		isError = true;
	}
	if(comment.isEmpty()){
		errorText.append("<li>The 'comment' is empty.</li>");
		isError = true;
	}
	if(agree == null){
		errorText.append("<li>The 'agree' is empty.</li>");
		isError = true;
	}
	
	if(!isError){
	DBWork dbWork = new DBWork();
	dbWork.setUser(login, password, name, rePassword, gender, address, comment, agree);
	out.write("<br>Registration sucsesfull!");
	showForm = false;
}
}
errorText.append("</ul>");

//out.write("login: "+login+"<br>"+"password: "+password+"<br>"+"rePassword: "+rePassword+"<br>"+"name: "+name+"<br>"+"gender: "+gender+"<br>"
//+"address: "+address+"<br>"+"comment: "+comment+"<br>"+"agree: "+agree+"<br>");

if(showForm){%>

<body>
<form>
<div class="myblock"><%out.write(errorText.toString());%></div>
</form>
<form id="loginForm" action="" method="post">

	<div class="field">
		<label>Login:</label>
		<div class="input"><input type="text" name="login" value="<%=(login!=null?login:"")%>" id="login" /></div>
	</div>
	
	<div class="field">
		<label>Password:</label>
		<div class="input"><input type="password" name="password" value="<%=(password!=null?password:"")%>" id="password" /></div>
	</div>
	
	<div class="field">
		<label>Confirm password:</label>
		<div class="input"><input type="password" name="rePassword" value="<%=(rePassword!=null?rePassword:"")%>" id="repassword" /></div>
	</div>
	
	<div class="field">
		<label>Enter your name:</label>
		<div class="input"><input type="text" name="name" value="<%=(name!=null?name:"")%>" id="name" /></div>
	</div>
	
	<div class="radio" align="center">
		<label>Gender:</label>
		<div class="radio">M<input type="radio" name="gender" value="male" <%=(gender!=null && gender.equals("male")?"checked":"")%> checked>F<input type="radio" name="gender" value="female" <%=(gender!=null &&gender.equals("female")?"checked":"")%>/></div>
	</div>
	
	<div class="field" align="center">
		<label>Enter your address:</label>
		<div class="select"><select name ="address"><option value="lnr"<%=(address!=null && address.equals("lnr")?"selected":"")%>>LRN</option><option value="dnr"<%=(address!=null && address.equals("dnr")?"selected":"")%>>DNR</option><option value="crimea"<%=(address!=null && address.equals("crimea")?"selected":"")%>>Crimea</option></select></div>
	</div>
	
	<div class="field" align="center">
		<label>Enter your comment:</label>
		<div class="textarea"><textarea style="overflow:auto;resize:none" name = "comment" cols = "30" rows="10" value="<%=(comment!=null?comment:"")%>"></textarea></div>
	</div>
	
	<div class="checkbox" align="center">
		<label>I agree</label>
		<div class="checkbox"><input type="checkbox" name = "agree"></div>
	</div>

	<div class="submit">
		<button type="submit">Enter</button>
	</div>

</form>
</body>

<%}%>