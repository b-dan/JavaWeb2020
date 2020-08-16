<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/source/includes/header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<body>
<center>
	<c:forEach items="${products}" var="product">
	<table id="product" border="0">
	<tr><td width="210" class="title">${product.name}</td><td width="100"></td></tr>
	<tr><td><img width="200" height="300" src="./source/images/${product.id}.jpg"></td><td  class="info" valign="middle">${product.description}</td></tr>
	<tr><td>${product.price} грн</td><td align="right">
		<form action="./cart" method="post">
			<input type="hidden" name="prodId" value="${product.id}"/>
			<input type="submit" class="myButton" value="Buy"/>
		</form>
	</td></tr>
	</table>
	<br/>
	<br/>
	</c:forEach>
</center>
</body>
<%@include file="/source/includes/footer.jsp" %>