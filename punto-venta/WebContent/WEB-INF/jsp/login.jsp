<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>punto venta ::: login</title>
<style type="text/css">
.message{color:red;font-weight: 900;text-align: -webkit-center;}
</style>
</head>
<body>
	<div class="container">
		<c:if test="${message != null}">
			<div class="message"><c:out value="${message}"></c:out></div>
		</c:if>

		<form:form commandName="loginForm" action="loginProcess.do" method="post" name="loginForm">
				<div class="form-group">
					<label>Email: </label>
					<input type="text" id="email" name="email" placeholder="Email" class="form-control">
				</div>
				<div class="form-group">
					<label>Contrase&ntilde;a: </label>
					<input type="password" id="password" name="password" placeholder="Contrase&ntilde;a" class="form-control">
				</div>
					<div class="form-group">
					<button type="submit" class="btn btn-primary btn-lg btn-block login-button">Entrar</button>
				</div>
			</form:form>
		</div>
	
</body>
</html>