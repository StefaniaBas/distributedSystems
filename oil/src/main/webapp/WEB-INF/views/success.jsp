<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>Success</title>
		<style>
			body {
			  		text-align: center;
				}
			input {
   				color: black;
   			}
		</style>
	</head>
	<body>
		<form:form method="POST" action="/katanemhmena/success">
	   		<span><img style='height="330"; width="430";' src='http://previews.123rf.com/images/roxanabalint/roxanabalint1501/roxanabalint150100337/35791553-Success-grunge-rubber-stamp-on-white-background-vector-illustration-Stock-Vector.jpg'/></span>
	   		<br>
	   		<br>
	   		<br>
	   		<input type="submit" value="Back"/>
			        
		</form:form>
	</body>
</html>