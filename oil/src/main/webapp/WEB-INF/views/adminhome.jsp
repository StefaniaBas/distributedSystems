<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Admin Home</title>
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
	Employees 
</h1>
<section>
<table>
        <tr>
        <th> id </th>
        <th> name </th>
        <th> role </th>
        <th> password </th>
        </tr>
        <c:forEach items="${employees}" var="employee">
  		<tr>
    		<td>${employee.id}</td>
    		<td>${employee.name}</td>
    		<td>${employee.role}</td>
    		<td>${employee.password}</td>
   			<td> <a href="/katanemhmena/adminhome/edit/${employee.id}">Edit employee</a></td>
    		<td><a href="<c:url value='/adminhome/remove/${employee.id}'/> " > Delete </a></td>
  		</tr>
		</c:forEach>
</table>
</section>
<a href="<c:url value='/adminhome/employee'/>">Add Employee</a>
</body>
</html>
