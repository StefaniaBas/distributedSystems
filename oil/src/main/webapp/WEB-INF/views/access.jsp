<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>Access Denied</title>
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
	<form:form method="POST" action="/katanemhmena/access">
			<span><img src='http://stevegoodwill.com/images/access_denied2.png'/></span>
	</form:form>
</body>
</html>