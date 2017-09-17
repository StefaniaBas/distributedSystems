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
		    	transform: translatex(-50%);
			}
			ul li {display: block;position: relative;float: left;border:1px solid #330000}
			li ul {display: none;}
			ul li a {display: block;background: #1a0000;padding: 5px 10px 5px 10px;text-decoration: none; white-space: nowrap;color: #FFFFFF;height:28px;font-size:25px}
			ul li a:hover {background: #4d0000;}
			li:hover ul {display: block; position: absolute;}
			li:hover li {float: none;}
			li:hover a {background: #1a0000;}
			li:hover li a:hover {background: #4d0000;}
			#drop-nav li ul li {border-top: 0px;}
			.welcome{background: #FFFFFF; color: #787878; font-style:italic;}
			.menu{background: #003366;}
			.text{background: FFFFCC;}
			input {margin-top:5px;}
			.first{
				background:#ddd;
				line-height: 10%;
			}
			.hh{background: #FFFFCC; color: #787878; font-style:italic;}
			.head1{background: #E0E0E0; line-height: 10%;}
			.head2{background: #ddd; line-height: 10%;}
			.newsletter{right: 0; top: 0; height: 125px; width: 125px; left: 300px;display: block;}
			.action{
			    background-color: #660000; 
			    border: none;
			    color: white;
			    padding: 5px 12px;
			    text-align: center;
			    text-decoration: none;
			    display: inline-block;
			    font-size: 16px;
			    border: 1px solid #ddd ;
			}
			.bottom2{background: #ddd  ; line-height: 10%;}
			.same{display: block;}
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
		<div align="center" class="text">
			<br>
			<div class="hh">
			<div class="head1">
				<br>
			</div>
			<h2 style="background: #FFFFFF;">Please insert your data</h2>
			<div class="head2">
				<br>
			</div>
			</div>
			<br>
			<div class="newsletter">
				<form:form name="newsletter" action="/net/loginCustomer" method="get" modelAttribute="/loginCustomer" accept-charset="utf-8" >
			    <ul>
			    	<div class="same">
			        <li style="background-color:#1a0000; color: #FFFFFF;"><label>Username</label>
			        </div>
			        <div class="same">
			        <input name="username" style="padding: 7px 30px;" placeholder="username" required></li>
			        </div>
			        <div class="same">
			        <br>
			        <li style="background-color:#1a0000; color: #FFFFFF;"><label>Password</label>
			        </div>
			        <div class="same">
			        <input type="password" pattern="[0-9]*" inputmode="numeric" style="padding: 7px 30px;"name="password" placeholder="Password" required></li>
			        </div>
			        <div class="same">
			        <br>
			        <input style="background-color:#1a0000;padding: 5px 86px;border: 1px solid #ddd ; color: #FFFFFF;" type="submit" name="login" value="Login">
			    	</div>
			    </ul>
			</form:form>
			</div>
			<br>
			<br>
			<br>
			<br>
			<div class="bottom2">
				<br>
			</div>
			<br>
			<br>
			<form:form action="/net/activationCustomer" method="get" >
				<input type="submit" name="activate" style="padding: 10px 30px;" class="action" value="Activate online order"/>
			</form:form>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		</div>	
	</body>
</html>

