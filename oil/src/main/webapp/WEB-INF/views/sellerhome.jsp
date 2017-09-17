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
    <td> <a href="/katanemhmena/sellerhome/edit/${order.orderCode}">Edit</a></td> 
    <c:if test="${order.status == 'starter'}" >
    	<td> <a href="/katanemhmena/sellerhome/checkavailability/${order.orderCode}">Check availability</a></td>   
    </c:if>
    <c:if test="${order.status == 'ceoAccepted' || order.status == 'sellerAccepted'}" >
    	<td> <a href="/katanemhmena/sellerhome/checkTime/${order.orderCode}">Check time</a></td>
    </c:if>
  </tr>
</c:forEach>
</table>
</section>
<a href="<c:url value='/sellerhome/order'/>">Add Order</a>
<br>
  <br>
  <a href="<c:url value='/sellerchoice'/>">Go to menu</a>
</body>
</html>
