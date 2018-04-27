<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Punto Venta:: Clientes</title>
</head>
<body>
	
<div class="container" >
	<form:form commandName="accoutn" action="addProveedores.do" method="post" name="addProveedores">
		
		<div>
			<label >Nombre</label>
			<input type="text" id="empresa" name="empresa" placeholder="Empresa" class="form-control">
		</div>	
		<div>
			<label>ID Cliente</label>
			
		</div>		
			<label>Telefono Fijo</label>
			<label>Telefono Celular</label>
			<label>Direccion</label>
			<label>Colonia</label>
			<label>C.P.</label>
			<label>Estado</label>
			<label>Municipio</label>
			<label>Observaciones</label>
		</div>
	
	</form:form>
		
</div>
		
</body>
</html>