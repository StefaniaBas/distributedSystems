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
			    background-color: #660000;
			    color: white;
			}
			ul {list-style: none;padding: 0px;margin: 0px; display: inline;
			 position: absolute;
		    left: 50%;
		    transform: translatex(-50%);}
			ul li {display: block;position: relative;float: left;border:1px solid #330000}
			li ul {display: none;}
			ul li a {display: block;background: #1a0000;padding: 5px 10px 5px 10px;text-decoration: none;white-space: nowrap;color: #FFFFFF;height:28px;font-size:25px;}
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
			.action{
			    background-color: #1a0000; 
			    border: none;
			    color: white;
			    padding: 5px 12px;
			    text-align: center;
			    text-decoration: none;
			    display: inline-block;
			    font-size: 16px;
			    border: 1px solid #ddd ;
			}
			.log{
			    background-color: #660000; 
			    border: none;
			    color: white;
			    padding: 5px 12px;
			    position:left;
			    font-size: 16px;
			    border: 1px solid #ddd ;
			}
			.logout{display:inline; margin-left:50%;}
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
		<div class="logout" style="display:inline;margin-left:50%;">
		<form:form action="/net/logout" method="get" >
				<label style="color: #660000;font-size: 20px;">Welcome to our site</label>
				<input type="submit" class="log" value="Logout"/>
			</form:form>
		</div>
		<br>
		<div class="text">
			<br>
			<div class="hh">
			<div class="head1">
				<br>
			</div>
			<h2 style="background: #FFFFFF;">Your orders</h2>
			<div class="head2">
				<br>
			</div>
			</div>
			<br>
			<br>
			<table align="center">
			    <tr>
			        <th> Order code </th>
			        <th> Customer code </th>
			         <th> Quantity of products </th>
			         <th> Delivery time </th>
			         <th> Status </th>
			         <th> Product code </th>
			         <th> Type </th>
			        </tr>
			        <c:forEach items="${orders}" var="order">
				  <tr>
				    <td>${order.orderCode}</td>
				    <td>${order.customerCode}</td>
				    <td>${order.quantityOfProducts}</td>
				    <td>${order.deliveryTime}</td>
				    <td>${order.status}</td>
				    <td>${order.productCode}</td>
				    <td>${order.type}</td>
				    <c:if test="${order.status == 'accepted' && order.type == 'online'}" >
				    <td style="border: 1px solid #ddd;"> <a href="/net/checkOnlineOrder/${order.orderCode}"><input style="background-color:#660000;padding: 7px 10px;border: 1px solid #ddd ; color: #FFFFFF;" class="check" type="submit" name="login" value="Accept this order"></a></td>
				    <td style="border: 1px solid #ddd;"> <a href="/net/rejectOnlineOrder/${order.orderCode}"><input style="background-color:#660000;padding: 7px 10px;border: 1px solid #ddd ; color: #FFFFFF;" class="check" type="submit" name="login" value="Reject this order"></a></td>  
				  	</c:if>
				  </tr>
				</c:forEach>
			</table>
			<br>
			<br>
			<br>
			<form:form action="/net/makeOrder" method="get" >
				<input type="submit" name="activate" style="padding: 10px 30px;" class="action" value="Add new order"/>
			</form:form>
			<br>
			<br>
			<br>
			<br>
			<br>
		</div>
	</body>
</html>

