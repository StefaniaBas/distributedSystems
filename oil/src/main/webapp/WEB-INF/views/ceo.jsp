<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Products Storage</title>
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
<h2>Products Information</h2>
<form:form method="POST"  modelAttribute="newProduct" action="/katanemhmena/ceohome/AddProduct">
   <table>   
   <c:if test="${newProduct.productCode != 0}" >
   	<tr>
        <td><form:label path="productCode">Product Code</form:label></td>
        <td><form:input path="productCode" readonly="true"/>
        </td>
    </tr>
    </c:if>
    <tr>
        <td><form:label path="quantity">Quantity</form:label></td>
        <td><form:input path="quantity" /></td>
        <td><form:errors path="quantity" cssClass="error" /></td>
    </tr>
   <tr>
        <td><form:label path="oilVariety">Oil Variety</form:label></td>
        <td><form:input path="oilVariety" /></td>
        <td><form:errors path="oilVariety" cssClass="error" /></td>
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