<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<title>Seller Choice</title>
<style>
body {
  		text-align: center;
	}
</style>
<body>
<h2>Choose between:</h2>
	<table>
	<tr>
	<th>
	<a href="<c:url value='/authenticateCustomer'/>">Authenticate customer</a>
	</th>
	</tr>
	
	<tr>
	<th>
	<a href="<c:url value='/customerhome'/>">Customers</a>
	</th>
	</tr>
	<tr>
	<th>
	<a href="<c:url value='/auctions'/>">Auctions</a>
	</th>
	</tr>
	<tr>
	<th>
	<a href="<c:url value='/sellerhome'/>">Orders</a>
	</th>
	</tr>
	 </table>
</body>
</html>