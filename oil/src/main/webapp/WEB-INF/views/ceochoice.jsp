<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<title>Ceo Choice</title>
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
	<a href="<c:url value='/ceohome'/>">Edit Products</a>
	</th>
	</tr>
	<tr>
	<th>
	<a href="<c:url value='/ceoOrders'/>">Check Orders</a>
	</th>
	</tr>
	 </table>
</body>
</html>