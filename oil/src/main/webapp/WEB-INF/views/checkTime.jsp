<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Check Time</title>
<style>
body {
  		text-align: center;
	}
</style>
<h1>
	Check Time
</h1>
<p>Check the time of the order or if you want to make a settlement call the customer and decide for a new time
</p>
</head>
<body>	
		<form:form method="POST" modelAttribute="Time" action="/katanemhmena/sellerhome/checkTime">
   		<table>  
    	<tr>
        <td><form:label path="deliveryTime">Delivery time</form:label></td>
        <td><form:input path="deliveryTime"/></td>
        <td><form:errors path="deliveryTime" cssClass="error" /></td>
    </tr>
    	<tr>
        <td colspan="2">
		<input type="submit" name="approve" value="Accept"/>
		<input type="submit" name="settlement" value="Reject"/>
		</td>
    	</tr>
		 </table>
		 </form:form>
</body>
</html>