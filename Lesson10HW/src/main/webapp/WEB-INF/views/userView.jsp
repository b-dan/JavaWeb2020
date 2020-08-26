<%@ page isELIgnored = "false" %>
Hello, ${userAuthorized.name}!
<form  action="./authorization" method="post">
	<input type="hidden" name="LogOut" />
	<input type="submit" value="logout"/>
</form>