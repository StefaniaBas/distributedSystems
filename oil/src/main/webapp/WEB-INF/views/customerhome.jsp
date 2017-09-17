<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
	<title>Customer Home</title>
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
	Customers 
</h1>
<section>
<table>
        <tr>
        <th> Customer code </th>
        <th> Company name </th>
        <th> Afm </th>
         <th> Contact person </th>
         <th> Telephone number </th>
         <th> Delivery address </th>
         <th> Username </th>
         <th> Password</th>
         <th> Online Orders </th>
        </tr>
        <c:forEach items="${customers}" var="customer">
  <tr>
    <td>${customer.customerCode}</td>
    <td>${customer.companyName}</td>
    <td>${customer.afm}</td>
    <td>${customer.contactPerson}</td>
    <td>${customer.telephoneNumber}</td>
    <td>${customer.deliveryAddress}</td>
    <td>${customer.username}</td>
    <td>${customer.password}</td>
    <td>${customer.onlineOrders}</td>
    <td> <a href="/katanemhmena/customerhome/edit/${customer.customerCode}">Edit</a></td>
    <td><a href="<c:url value='/customerhome/remove/${customer.customerCode}'/> " > Delete </a></td>
  </tr>
</c:forEach>
</table>
</section>
<a href="<c:url value='/customerhome/customer'/>">Add Customer</a>
<br>
  <br>
  <a href="<c:url value='/sellerchoice'/>">Go to menu</a>
</body>
</html>
