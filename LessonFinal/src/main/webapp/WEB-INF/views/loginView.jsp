<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/resources/includes/header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
</center>
<body>
	<c:choose>
	<c:when test="${userAuthorized==null}">
	<form id="product" action="" method="post">

		<div class="field">
			<label>Enter your login:</label>
			<div class="input"><input type="text" name="f1" value="" id="login" /></div>
		</div>

		<div class="field">
			<a href="#" id="forgot">Forgot your password?</a><br>
			<label>Enter your password:</label>
			<div class="input"><input type="password" name="f2" value="" id="pass" /></div>
		</div>

		<div class="submit">
			<button type="submit" class="myButton" >Enter</button>
			<label id="remember"><input name="" type="checkbox" value="" /> Remember me</label>
		</div>
${errorText}
	</form>
	</c:when>
	<c:otherwise>
		<form  action="" method="post">
			<input type="hidden" name="LogOut" value="logout" />
			<input type="submit" class="myButton" value="logout"/>
		</form>
	</c:otherwise>
	</c:choose>
</body>
</html>
<%@include file="/resources/includes/footer.jsp" %>

