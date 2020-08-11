<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="/source/includes/menu.jsp" %>
<%@ page isELIgnored = "false" %>
</center>
<body>
	<form>
	<div class="myblock">
	<ul>
 	<c:forEach items="${errorText}" var="value">
	<li><c:out value="${value}"/></li>
	</c:forEach>
	</ul>
	</div>
	</form>
	<form id="loginForm" action="./registration" method="post">

	<div class="field">
		<label>Login:</label>
		<div class="input"><input type="text" name="login" value="${regUser.login}" id="login" /></div>
	</div>
	
	<div class="field">
		<label>Password:</label>
		<div class="input"><input type="password" name="password" value="${regUser.password}" id="password" /></div>
	</div>
	
	<div class="field">
		<label>Confirm password:</label>
		<div class="input"><input type="password" name="rePassword" value="${regUser.rePassword}" id="repassword" /></div>
	</div>
	
	<div class="field">
		<label>Enter your name:</label>
		<div class="input"><input type="text" name="name" value="${regUser.name}" id="name" /></div>
	</div>
	
	<div class="radio" align="center">
		<label>Gender:</label>
		<div class="radio">M<input type="radio" name="gender" value="male" ${genderArr[0]} checked>F<input type="radio" name="gender" value="female" ${genderArr[1]} /></div>
	</div>
	
	<div class="field" align="center">
		<label>Enter your address:</label>
		<div class="select"><select name ="address"><option value="lnr" ${addressArr[0]} >LRN</option><option value="dnr" ${addressArr[1]} >DNR</option><option value="crimea" ${addressArr[2]} >Crimea</option></select></div>
	</div>
	
	<div class="field" align="center">
		<label>Enter your comment:</label>
		<div class="textarea"><textarea style="overflow:auto;resize:none" name = "comment" cols = "30" rows="10" value="${regUser.comment}" ></textarea></div>
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
</html>
