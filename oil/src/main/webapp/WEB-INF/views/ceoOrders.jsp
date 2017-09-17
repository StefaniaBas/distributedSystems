<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Seller Home</title>
	<style>
	body {
  		text-align: center;
	}
	table {
     border-collapse: collapse;
 	}
 	table, th, td {
     	border: 1px solid black;
 	}
 </style>
</head>
<body>
<h1>
	Orders 
</h1>
<section>
<table>
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
		    <td> <a href="/katanemhmena/ceoOrders/accept/${order.orderCode}">Accept</a></td> 
		    <td> <a href="/katanemhmena/ceoOrders/reject/${order.orderCode}">Reject</a></td>
	  	</tr>
		</c:forEach>
</table>
</section>
<br>
  <br>
  <a href="<c:url value='/ceochoice'/>">Go to menu</a>
</body>
</html>