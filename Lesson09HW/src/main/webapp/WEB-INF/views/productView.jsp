<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/source/includes/header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<body>
<center>
	<c:forEach items="${products}" var="product">
	<table id="product" border="0">
	<tr><td width="210"><a href="./product?prodId=${product.id}" style="text-decoration:none;"><div class="title">${product.name}</div></td></a><td width="100"></a></td></tr>
	<tr><td><a href="./product?prodId=${product.id}"><img width="220" src="./source/images/${product.id}.jpg"></a></td><td><a href="./product?prodId=${product.id}" style="text-decoration:none;"><div class="info">${product.getCutDescription()}</div></a></td></tr>
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