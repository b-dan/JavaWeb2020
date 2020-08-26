<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/source/includes/header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<script src="./source/scripts/jquery-3.5.1.js"></script>
<body>
<center>
	<c:forEach items="${sessionScope.cart}" var="product">
	<table id="product" border="0">
	<tr><td width="110"><a href="./product?prodId=${product.key.id}"><img width="110" src="./source/images/${product.key.id}.jpg"></a></td>
		<td width="260"><a href="./product?prodId=${product.key.id}" style="text-decoration:none;"><div class="title">${product.key.name}</div></a></td>
		<td width = "300">
		<div id="formLine">
			<img id="imgMinus" src="./source/images/minus.png" width="25" height = "25" onclick="minus(${product.key.id})"/>
			<input type="text" name="amount" id="qnt${product.key.id}" value="${product.value}" size="2" />
			<img id="imgPlus" src="./source/images/plus.png" width="25" height = "25" onclick="plus(${product.key.id})"/>
		</div>
		</td>
	<td align="right">
		<form action="./cart" method="post">
			<input type="hidden" name="deleteById" value="${product.key.id}"/>
			<input type="image" src="./source/images/trash.png" width="70" height = "70" alt="">
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
<script>
	function minus(numb){
		var qnt = document.getElementById("qnt"+numb);
		qnt.value = +qnt.value-1;
		if(qnt.value<0){
			qnt.value=0;
		}
	} 
	function plus(numb){
		var qnt = document.getElementById("qnt"+numb);
		qnt.value = +qnt.value+1;
	}
	
	function show(numb){
		var qnt = document.getElementById("qnt"+numb);
		$.ajax({ //$. вызов любой ф-и из библиотеки jquery в этом случае ajax
			url: './cart',
			type: 'POST',  					// http method
			data: {prodId: numb, amount: qnt.value},  // data to submit
			success: function (data) {
				document.getElementById("amountField").innerHTML = data;
				
			}
		});
		}
</script>