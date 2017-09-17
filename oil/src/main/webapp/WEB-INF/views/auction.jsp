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
<form:form method="POST"  modelAttribute="newAuction" action="/katanemhmena/sellerhome/addAuction">
   <table>   
   <c:if test="${newAuction.id != 0}" >
   	<tr>
        <td><form:label path="id">Id</form:label></td>
        <td><form:input path="id" readonly="true"/>
        </td>
    </tr>
    </c:if>
    <tr>
        <td><form:label path="title">Title</form:label></td>
        <td><form:input path="title" /></td>
        <td><form:errors path="title" cssClass="error" /></td>
    </tr>
   <tr>
        <td><form:label path="location">Location</form:label></td>
        <td><form:input path="location" /></td>
        <td><form:errors path="location" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="description">Description</form:label></td>
        <td><form:input path="description" /></td>
        <td><form:errors path="description" cssClass="error" /></td>
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