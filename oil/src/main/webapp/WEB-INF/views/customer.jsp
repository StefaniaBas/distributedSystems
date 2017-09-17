<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Customer Handling</title>
    <style>
    body {
  		text-align: center;
	}
	.error {
	    color: #ff0000;
	    font-style: italic;
	    font-weight: bold;
	}
</style>
</head>
<body>
<h2>Customer Information</h2>
<form:form method="POST"  modelAttribute="newCustomer" action="/katanemhmena/customerhome/addCustomer">
   <table>   
      <c:if test="${newCustomer.customerCode != 0}" >
   <tr>
        <td><form:label path="customerCode">Customer code</form:label></td>
        <td><form:input path="customerCode" readonly="true"/>
        </td>
    </tr>
    </c:if>
     <tr>
        <td><form:label path="companyName">Company name</form:label></td>
        <td><form:input path="companyName" /></td>
        <td><form:errors path="companyName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="afm">Afm</form:label></td>
        <td><form:input path="afm" required=""/></td>
        <td><form:errors path="afm" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="contactPerson">Contact person</form:label></td>
        <td><form:input path="contactPerson" /></td>
        <td><form:errors path="contactPerson" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="telephoneNumber">Telephone number</form:label></td>
        <td><form:input path="telephoneNumber"/></td>
        <td><form:errors path="telephoneNumber" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="deliveryAddress">Delivery address</form:label></td>
        <td><form:input path="deliveryAddress" /></td>
        <td><form:errors path="deliveryAddress" cssClass="error" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table> 
</form:form>
</body>
</html>