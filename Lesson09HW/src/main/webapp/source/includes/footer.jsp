<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page isELIgnored = "false" %>
			  </div>
				<div id="sidebar">
					<table border=1>
                    <tr>
                    <td width="252" align="left">
                    <font color=white>
                    Вы авторизировались как 
                    <c:choose>
                    	<c:when test="${userAuthorized!=null}">
                    		${userAuthorized.name}
                    	</c:when>
                    	<c:otherwise>Guest</c:otherwise>
                    </c:choose>
                    <br />
                    В вашей корзине ${fn:length(sessionScope.cart)} товаров.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Боковое меню</h2>
					<ul id="product">
						<li><a href="./products?catId=1">Фигурки и игры</a></li>
						<li><a href="./products?catId=2">Комиксы и книги</a></li>
						<li><a href="./products?catId=3">Одежда</a></li>
						<li><a href="./registration">Регистрация</a></li>
						<li><a href="./authorization">Вход</a></li>
						<li><a href="./cart">Корзина</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<p>Copyright (c) by Пшенин Богдан</p>
</div>
<!-- end #footer -->
</body>
</html>
