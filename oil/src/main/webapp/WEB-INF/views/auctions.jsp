<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Seller Home</title>
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
	Auctions 
</h1>
<section>
<table>
        <tr>
        <th> Id </th>
        <th> Title </th>
        <th> Location </th>
        <th> Description </th>
        </tr>
        <c:forEach items="${auctions}" var="auction">
  <tr>
    <td>${auction.id}</td>
    <td>${auction.title}</td>
    <td>${auction.location}</td>
    <td>${auction.description}</td>
    <td> <a href="/katanemhmena/auctions/edit/${auction.id}">Edit</a></td>
    <td><a href="<c:url value='/sellerhome/remove/${auction.id}'/> " > Delete </a></td>
  </tr>
</c:forEach>
</table>
</section>
<a href="<c:url value='/sellerhome/auction'/>">Add Auction</a>
<br>
  <br>
  <a href="<c:url value='/sellerchoice'/>">Go to menu</a>
</body>
</html>
