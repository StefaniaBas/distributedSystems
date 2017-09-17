<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Orders Handling</title>
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
<h2>Order Information</h2>
<form:form method="POST"  modelAttribute="newOrder" action="/katanemhmena/sellerhome/addOrder">
   <table>   
      <c:if test="${newOrder.orderCode != 0}" >
   	<tr>
        <td><form:label path="orderCode">Order code</form:label></td>
        <td><form:input path="orderCode" readonly="true"/>
        </td>
    </tr>
    </c:if>
     <tr>
        <td><form:label path="customerCode">Customer code</form:label></td>
        <td><form:input path="customerCode"/></td>
        <td><form:errors path="customerCode" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="quantityOfProducts">Quantity of products</form:label></td>
        <td><form:input path="quantityOfProducts" /></td>
        <td><form:errors path="quantityOfProducts" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="deliveryTime">Delivery time</form:label></td>
        <td><form:input path="deliveryTime" /></td>
        <td><form:errors path="deliveryTime" cssClass="error" /></td>
    </tr>
     <tr>
        <td><form:label path="productCode">Product code</form:label></td>
        <td><form:input path="productCode" /></td>
        <td><form:errors path="productCode" cssClass="error" /></td>
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