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
	<form:form commandName="clientDTO" action="addClient.do" method="post" name="addClient">
		
		<div>
		
			<p>Nombre</p>				<input type="text" name="name" id="name">
			<p>Apellido Paterno</p>		<input type="text" name="firstName" id="apPaterno">
			<p>Apellido Materno</p>		<input type="text" name="secondName" id="apMaterno">
			<p>Calle</p>				<input type="text" name="street"	id="calle">
			<p>Colonia</p>				<input type="text" name="colony" id="colonia">
			<p>Delegacion</p>			<input type="text" name="delegation"  id="delegacion">
			<p>Estado</p>				<input type="text" name="state"	id="">
			<p>Ciudad</p>				<input type="text" name="city">
			<p>C.P.</p>					<input type="text" name="cp">
			<p>Telefono</p>				<input type="number" name="tel1">
			<p>Telefono 2do</p>			<input type="number" name="tel2">
		
		<button>Enviar</button>	
		</div>
		
	</form:form>
		
</div>
		
</body>
</html>