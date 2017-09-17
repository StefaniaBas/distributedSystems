<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
	<head>
 		<title>Authenticate Customer</title>
 		<style>
	 		body {
	  		text-align: center;
		}
 		</style>
	</head>
	<body>
		 <h2>Please give customer code and afm</h2>
	      <form:form method="POST" modelAttribute="authenticateCustomer" action="/katanemhmena/authenticateCustomer/login">
			   <table>   
				     <tr>
				        <td><form:label path="customerCode">Customer code</form:label></td>
				        <td><form:input path="customerCode"/></td>
				        <td><form:errors path="customerCode" cssClass="error" /></td>
				    </tr>
				    <tr>
				        <td><form:label path="afm">Afm</form:label></td>
				        <td><form:input path="afm"/></td>
				        <td><form:errors path="afm" cssClass="error" /></td>
				    </tr>
				    <tr>
				    </tr>
				    <tr>
				    </tr>
				    <tr>
				    </tr>
				    <tr>
				    </tr>
				    <tr colspan="2">
				        <td><input type="submit" name="login" value="Submit"/></td>
				        <td><input type="submit" name="create" value="Create new customer"/></td>
				    </tr>
				</table> 
		</form:form>
		<br>
  		<br>
  		<a href="<c:url value='/sellerchoice'/>">Go to menu</a>
    </body>
</html>