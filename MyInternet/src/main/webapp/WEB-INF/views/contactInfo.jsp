<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>

<html>
	<head>
		<title>Oil producers</title>
		<style type="text/css">
			body,head {
		  		text-align: center;
		  		background-color: #FFFFCC;
			}	
			table {
				width: 70%;
		     	border-collapse: collapse;
		 	}
			table, th, td {
		     	border: 1px solid #ddd;
		 	}
		 	th,td{
		 		 padding: 8px;
		 	}
		 	th {
			    background-color: #4CAF50;
			    color: white;
			}
			ul {list-style: none;padding: 0px;margin: 0px; display: inline;
			 position: absolute;
		    left: 50%;
		    transform: translatex(-50%);}
			ul li {display: block;position: relative;float: left;border:1px solid #330000}
			li ul {display: none;}
			ul li a {display: block;background: #1a0000;padding: 5px 10px 5px 10px;text-decoration: none;white-space: nowrap;color: #FFFFFF;height:28px;font-size:25px}
			ul li a:hover {background: #4d0000;}
			li:hover ul {display: block; position: absolute;}
			li:hover li {float: none;}
			li:hover a {background: #1a0000;}
			li:hover li a:hover {background: #4d0000;}
			#drop-nav li ul li {border-top: 0px;}
			.welcome{background: #FFFFFF; color: #787878; font-style:italic;}
			.menu{background: #003366;}
			.text{background: FFFFCC;}
			.button1{display: inline;}
			input {margin-top:5px;}
			.first{
				background:#ddd;
				line-height: 10%;
			}
			.hh{background: #FFFFCC; color: #787878; font-style:italic;}
			.head1{background: #ddd; line-height: 10%;}
			.head2{background: #ddd; line-height: 10%;}
		</style>
	</head>
	<body>
		<div class="first">
				<br>
		</div>
		<p class="welcome">Welcome to greek oil producers</p>
		<div class="first">
				<br>
		</div>
		<br>
		<div style="width: 100%;" class="menu" id="menu">
			<ul id="drop-nav" style="width: 100%;">
			  <li style="width:25%"><a href="/net/">Home</a></li>
			  <li style="width:25%"><a href="/net/ContactInfos">Contact Information</a>
			  </li>
			  <li style="width:25%"><a href="/net/viewAuctions">Auctions</a>
			  </li>
			  <li style="width:24%"><a href="/net/OnlineOrders">Online Order</a>
			  </li>
			</ul>
		</div>
		<br>
		<br>
		<br>
		<br>
		<img style="width: 70%;height:200px;" src='http://www.easmessaras.gr/images/anadromi4.jpg'/>
		<br>
		<br>
		<div class="text">
			<br>
			<div class="hh">
			<div class="head1">
				<br>
			</div>
			<h2 style="background: #FFFFFF;">Contact with us</h2>
			<div class="head2">
				<br>
			</div>
			</div>
			<br>
			<br>
			<p>Contact phone: 210 5755285</p>
			<p>Contact phone: 697 8333557</p>
			<p>Email: it21343@hua.gr</p>
			<p>You can also contact in another email addresses: it21328@hua.gr or it21398@hua.gr </p>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		</div>
	</body>
</html>
