<%@ page contentType="text/html; charset=UTF-8" %>
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
                    В вашей корзине 0 товаров.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Боковое меню</h2>
					<ul id="product">
						<li><a href="./products">Фигурки и игры</a></li>
						<li><a href="./products">Комиксы и книги</a></li>
						<li><a href="./products">Одежда</a></li>
						<li><a href="./registration">Регистрация</a></li>
						<li><a href="./authorization">Вход</a></li>
						<li><a href="cart.php">Корзина</a></li>
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
