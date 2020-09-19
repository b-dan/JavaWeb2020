<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/resources/includes/header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<body>
<center>
	<table id="product" border="0">
	<tr><td width="210" class="title">${product.name}</td><td width="100"></td></tr>
	<tr><td><img width="300" height="450" src="./resources/images/${product.id}.jpg"></td><td  class="info" valign="middle">${product.description}</td></tr>
	<tr><td>${product.price} грн</td><td align="right">
		<div>
		<input type="hidden" name="prodId" value="${product.id}"/>
		<input type="hidden" name="amount" id="qnt${product.id}" value="1"/>
		<input type="button" class="myButton" value="Buy" onclick="show(${product.id})"/>
		</div>
	</td></tr>
	</table>
	<br/>
	<br/>
</center>
</body>

<%@include file="/resources/includes/footer.jsp" %>

<script>
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