<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : FunBuy 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20110926

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>GeekStore</title>
<link href="./source/styles/style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="jquery.poptrox-0.1.js"></script>
<style>
   body { background: #f6d654 url(./source/images/background.jpg);}
 </style>
</head>
<body>
<div id="header" class="container">
	<div id="logo">
		<h1><a href="#">geekstore</a></h1>
		<p>Отличные эмоции за деньги не купишь, но тут это реально!<a href="http://www.freecsstemplates.org"></a></p>
	</div>
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="index.jsp">Главная</a></li>
			<li><a href="./products">Товары</a></li>
			<li><a href="./registration">Регистрация</a></li>
			<li><a href="./authorization">Вход</a></li>
			<li><a href="./cart">Корзина</a></li>
		</ul>
	</div>
</div>
<!-- end #header -->
<div id="poptrox">
	<!-- start -->
	<ul id="gallery">
		<li><a href="./source/images/logo.png"><img width="210" height="150" src="./source/images/logo.png" title="Comics" alt="" /></a></li>
		<li><a href="./source/images/marvel.png"><img width="210" height="130" src="./source/images/marvel.png" title="MARVEL" alt="" /></a></li>
		<li><a href="./source/images/dc.jpg"><img width="210" height="130" src="./source/images/dc.jpg" title="DC" alt="" /></a></li>
		<li><a href="./source/images/pow.png"><img width="210" height="130" src="./source/images/pow.png" title="Comics" alt="" /></a></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<br class="clear" />
	<script type="text/javascript">
		$('#gallery').poptrox();
		</script>
	<!-- end -->
</div>
<div id="page">
	<div id="bg1">
		<div id="bg2">
			<div id="bg3">
				<div id="content">