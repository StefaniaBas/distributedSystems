<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>Failure</title>
		<style>
			body {
			  		text-align: center;
				}
				.submit-button {
   					color: black;
   				}
		</style>
	</head>
	<body>
		<form:form method="POST" action="/katanemhmena/failure">
			<span><img src='https://media.licdn.com/mpr/mpr/p/5/005/099/1e1/11bcd87.jpg'/></span>
	   		<br>
	   		<input class="submit-button" type="submit" value="Back"/>
		</form:form>
	</body>
</html>