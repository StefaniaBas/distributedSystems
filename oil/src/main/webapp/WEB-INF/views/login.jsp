<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
 <title>Users Login</title>
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
	 <h2>Sign in</h2>
      <form:form  modelAttribute="/"  action="/katanemhmena/" method="POST">
        <table>   
     <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
        <td><form:errors path="name" cssClass="error" /></td>
    </tr>
 
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password" type="password"/></td>
        <td><form:errors path="password" cssClass="error" /></td>
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