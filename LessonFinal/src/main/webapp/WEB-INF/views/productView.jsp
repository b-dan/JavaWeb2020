<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/resources/includes/header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<script src="./resources/scripts/jquery-3.5.1.js"></script>
<body>
<center>
	<c:forEach items="${products}" var="product">
	<table id="product" border="0">
	<tr><td width="210"><a href="/LessonFinal/oneproduct?prodId=${product.id}" style="text-decoration:none;"><div class="title">${product.name}</div></td><td width="100"></a></td></tr>
	<tr><td><a href="/LessonFinal/oneproduct?prodId=${product.id}"><img width="220" src="./resources/images/${product.id}.jpg"></a></td>
	<td><a href="/LessonFinal/oneproduct?prodId=${product.id}" style="text-decoration:none;"><div class="info">${product.getCutDescription()}</div></a></td></tr>
	<tr><td>${product.price} грн</td>
	<td>
	<div>
		<input type="hidden" name="prodId" value="${product.id}"/>
		<img id="imgMinus" src="./resources/images/minus.png" width="25" onclick="minus(${product.id})"/>
		<input type="text" name="amount" id="qnt${product.id}" value="1" size="2"/>
		<img id="imgPlus" src="./resources/images/plus.png" width="25" onclick="plus(${product.id})"/>
		<input type="button" class="myButton" value="Buy" onclick="show(${product.id})"/>
	</div>
	</td></tr>
	</table>
	<br/>
	<br/>
	</c:forEach>
</center>
</body>
<%@include file="/resources/includes/footer.jsp" %>


<script>
	function minus(numb){
		var qnt = document.getElementById("qnt"+numb);
		qnt.value = +qnt.value-1;
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