<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punto de venta :: usuarios</title>
</head>
<body>
 <div class="container">
 	<table class="table">
 	<thead>
	 	<tr>
	 		<td>Nombre</td>
	 		<td>Email</td>
	 		<td>Admin</td>
	 	</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${listUser}" var="user">		
 		<tr>
 			<td>${user.name}</td>
 			<td>${user.email }</td>
 			<td><c:out default="None" escapeXml="true" value="${user.fgAdmin eq '1' ? 'Admin' : '-' }" /></td>
 		</tr>
 		</c:forEach>
 	</tbody>
 	</table>
 </div>
</body>
</html>