<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punto de venta :: admin usuarios</title>
</head>
<body>

<div class="container">
		<c:if test="${message != null}">
			<div class="message"><c:out value="${message}"></c:out></div>
		</c:if>
		<form:form commandName="account" action="addUser.do" method="post" name="addUserForm">
				<div class="form-group">
					<label>Nombre: </label>
					<input type="text" id="name" name="name" placeholder="Email" class="form-control">
				</div>
				<div class="form-group">
					<label>Apellido Paterno: </label>
					<input type="text" id="apPaterno" name="firstName" placeholder="Apellido Paterno" class="form-control">
				</div>
				<div class="form-group">
					<label>Apellido Materno: </label>
					<input type="text" id="apMaterno" name="secondName" placeholder="Apellido Materno" class="form-control">
				</div>
				<div class="form-group">
					<label>Email: </label>
					<input type="text" id="email" name="email" placeholder="Email" class="form-control">
				</div>
				<div class="form-group">
					<label>Password: </label>
					<input type="password" id="password" name="password" placeholder="Password" class="form-control">
				</div>
				<div class="form-group">
					<label>Admin: </label>
					<input type="checkbox" id="fgAdmin" name="fgAdmin" class="form-control">
				</div>
				
					<div class="form-group">
					<button type="submit" class="btn btn-primary btn-lg btn-block login-button">Enviar</button>
				</div>
		</form:form>
		</div>


</body>
</html>