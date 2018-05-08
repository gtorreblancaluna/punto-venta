<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<title>Punto Venta:: Clientes</title>
</head>
<body >
	
<div class="container" >
	<form:form commandName="accoutn" action="addProveedores.do" method="post" name="addProveedores">
		
		<div>
			<label >Nombre</label>
			<input type="text" id="empresa" name="empresa" placeholder="Empresa" class="form-control">
		</div>	
		<div>
			<label>ID Cliente</label>
			
		
		
		</div>
	
	</form:form>
		
</div>
		
</body>
</html>