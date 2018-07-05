<!DOCTYPE html>
<%@page session="false"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punto venta :: bienvenido</title>
<!-- <link rel="stylesheet" href="../css/bootstrap.min.css"> -->
<style type="text/css">
.centered { text-align: center;}
.container{font-size:12px;}
.row-color{background-color: #000000ad; color:#fff;}
.bgcol{background-color: #f1c16d3d;}
/* body{background-image:url("http://www.thomaslhomme.com/wp-content/uploads/2014/03/Cool-Website-Backgrounds.jpg"); } */
body{background-image: url(images/madera.jpg);}

</style>
</head>
<body>
<div class="container " >
<div class="page ">
		<div class="row centered row-color">
			<div class="col-sm-12 ">
				<h1 >Bienvenido <c:out value="${userSession.account.email}"></c:out></h1>
			</div>
			
		</div>
	</div>
	
	<div class="row centered row-color" >
	   <div class="col-sm-6 " >
			<h2 >Venta semanal</h2>			
	   </div>
	   <div class="col-sm-6 ">
	   	 <h2 >Venta mensual</h2>	   	 
	   	</div>
	   	<div class="col-sm-12 ">
	   		<h2>Pedidos del dia</h2>
	   		<div class="container-salesToday bgcol">
	   		<c:if test="${not empty salesToday}">
	   			<table class="table">
	   				<thead>
		   				<tr>
		   					<th>Id</th>
		   					<th>Fecha registro</th>
		   					<th>Fecha entrega</th>
		   					<th>Descripci&oacute;n</th>
		   					<th>Cliente</th>
		   					<th>Sucursal</th>
		   					<th>Atendi&oacute;</th>
		   				</tr>
	   				</thead>
	   				<tbody>
	   					<c:forEach items="${salesToday}" var="sale">
		   					<tr>
		   						<td>${sale.saleId}</td>
		   						<td>${sale.saleDate}</td>
		   						<td>${sale.dateDelivery }</td>
		   						<td>${sale.description}</td>
		   						<td>${sale.nameCustomer}</td>
		   						<td>${sale.nameOffice}</td>
		   						<td>${sale.nameUser}</td>
		   					</tr>  	
	   					</c:forEach>			
	   				</tbody>
	   			</table>
	   		</c:if>
	   		<c:if test="${empty salesToday}">
	   			<p>No encontre pedidos para hoy</p>
	   		</c:if>
	   		</div>
	   	</div>
	</div>
</div>



</body>
</html>