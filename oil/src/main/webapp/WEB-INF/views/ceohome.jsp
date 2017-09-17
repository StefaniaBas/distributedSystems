<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Ceo Home</title>
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
	Ceo  Application 
</h1>
<section>
<table>
        <tr>
        <th> ProductCode </th>
        <th> Quantity </th>
        <th> Oil Variety </th>
        </tr>
        <c:forEach items="${products}" var="product">
  <tr>
    <td>${product.productCode}</td>
    <td>${product.quantity}</td>
    <td>${product.oilVariety}</td>
    <td> <a href="/katanemhmena/ceohome/edit/${product.productCode}">Edit product</a></td>
    <td> <a href="/katanemhmena/ceohome/remove/${product.productCode}">Delete</a></td>
  </tr>
</c:forEach>
</table>
</section>
<a href="<c:url value='/ceohome/ceo'/>">AddProduct</a>
<br>
 <br>
 <a href="<c:url value='/ceochoice'/>">Go to menu</a>
</body>
</html>
