<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>
<title>punto venta ::: login</title>
<link rel="stylesheet" href="../css/bootstrap-grid.css">
		<script language="JavaScript" type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
				<script language="JavaScript" type="text/javascript" src="../js/popper.min.js"></script>
				<script language="JavaScript" type="text/javascript" src="../js/bootstrap.min.js"></script>
		
<style>

.form-control{
width: auto;
}
.login-button{
width:200px;
}
.row > div {
        background:#f2f2f2;
        margin: 10px 0;
   }
   
   .container2{width:100%;
   				padding-right:45px;
   				padding-left:45px;
   				margin-right:auto;
   				margin-left:auto
   				}@media (min-width:576px){
   				.container{max-width:540px}}
   				@media (min-width:768px){
   				.container{max-width:720px}}
   				@media (min-width:992px){
   				.container{max-width:960px}}
   				@media (min-width:1200px){
   				.container{max-width:1140px}}
   				.container-fluid{width:100%;
   				padding-right:15px;
   				padding-left:15px;
   				margin-right:auto;
   				margin-left:auto}
   </style>

</head>
<body>
	<div class="container2" style="padding-right: 15px;padding-left: 15px;margin-right:auto;
   				margin-left:auto;background:#f2f2f2;">
		<c:if test="${message != null}">
			<div class="message"><c:out value="${message}"></c:out></div>
			</c:if>
			
		<div class="formulario">
			<form:form commandName="loginForm" action="loginProcess.do" method="post" name="loginForm">
				<div class="form-group" >
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
			
			
		</div>
		
	
	
</body>
</html>