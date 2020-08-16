<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/source/includes/header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<body>
<center>
	<c:forEach items="${sessionScope.cart}" var="product">
	<table id="product" border="0">
		<tr><td width="110"><a href="./product?prodId=${product.id}"><img width="110" src="./source/images/${product.id}.jpg"></a></td><td width="260"><a href="./product?prodId=${product.id}" style="text-decoration:none;"><div class="title">${product.name}</div></a></td>
		</tr>
	<br/>
	<tr>
	<td>
	</td>
	<td align="right">
		<form action="./cart" method="post">
			<input type="hidden" name="deleteById" value="${product.id}"/>
			<input type="image" src="./source/images/trashbin.png" width="50" height = "50" alt="">
		</form>
	</td>
	</tr>
	</table>
	<br/>
	<br/>
	</c:forEach>
</center>
</body>
<%@include file="/source/includes/footer.jsp" %>