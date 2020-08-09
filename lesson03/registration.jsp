<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>

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
	out.write("Registration sucsesfull!");
	showForm = false;
}
}
errorText.append("</ul>");

//out.write("login: "+login+"<br>"+"password: "+password+"<br>"+"rePassword: "+rePassword+"<br>"+"name: "+name+"<br>"+"gender: "+gender+"<br>"
//+"address: "+address+"<br>"+"comment: "+comment+"<br>"+"agree: "+agree+"<br>");

if(showForm){%>

<center>
<table>
<tr><td>
<form>
<table>
<tr><td>login: </td><td><input type="text" name="login" value="<%=(login!=null?login:"")%>"></td></tr>
<tr><td>password: </td><td><input type="password" name="password"></td></tr>
<tr><td>retype password: </td><td><input type="password" name="rePassword"></td></tr>
<tr><td>name: </td><td><input type="text" name="name"></td></tr>
<tr><td>gender: </td><td>M<input type="radio" name="gender" value="male" <%=(gender!=null && gender.equals("male")?"checked":"")%> checked>F<input type="radio" name="gender" value="female" <%=(gender!=null &&gender.equals("female")?"checked":"")%>></td></tr>
<tr><td>address: </td><td><select name ="address"><option value="lnr"<%=(address!=null && address.equals("lnr")?"selected":"")%>>LRN</option><option value="dnr"<%=(address!=null && address.equals("dnr")?"selected":"")%>>DNR</option><option value="crimea"<%=(address!=null && address.equals("crimea")?"selected":"")%>>Crimea</option></select></td></tr>
<tr><td>comment: </td><td><textarea name = "comment" cols = "15" rows="10"></textarea></td></tr>
<tr><td>I agree: </td><td><input type="checkbox" name = "agree"></td></tr>
<tr><td></td><td><input type="submit" value="send"></td></tr>
</table>
</form>
</td>
<td>
<%out.write(errorText.toString());%>
</td>
</tr>
</table>
<%}%>