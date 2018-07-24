<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punto de venta :: admin clientes</title>
<style>		    
    

</style>
<script type="text/javascript">
$( document ).ready(function() {
	//confirmar eliminar	
	$('form[name="deleteUserForm"]').submit(function() {
	   return confirm("confirma para continuar");	   
	});	
	
	$( '.btnUpdateUser' ).click(function() {
		var $updateForm = $("#updateUserForm");
		 var $row = jQuery(this).closest('tr');
		    var $columns = $row.find('td');

		    $columns.addClass('row-highlight');
		    var values = "";

		    jQuery.each($columns, function(i, item) {
		        values = values + 'td' + (i + 1) + ':' + item.innerHTML + '<br/>';
		        if(i===0)
		        	$updateForm.find('#userId').val(item.innerHTML);
		        if(i===1)
		        	$updateForm.find('#name').val(item.innerHTML);
		        if(i===2)
		        	$updateForm.find('#apPaterno').val(item.innerHTML);
		        if(i===3)
		        	$updateForm.find('#apMaterno').val(item.innerHTML);
		        if(i===4)
		        	$updateForm.find('#email').val(item.innerHTML);
		        if(i===5)
		        	$updateForm.find('#tel1').val(item.innerHTML);
		        if(i===6)
		        	$updateForm.find('#tel2').val(item.innerHTML);
		        
		    });		   
	});	 	
});
</script>
</head>
<body>
 <div class="container" style="">

		<c:if test="${message != null}">
		<div class="alert alert-success texto" style="width:100%;">
  			<strong>Success!</strong> ${message}
		</div>

		</c:if>
		<c:if test="${messageError != null}">
		<div class="alert alert-danger" style="width:100%;">
  			<strong>Error! </strong> ${messageError}
		</div>

		</c:if>
		<div class="page-header">
		<div class="row">
			<div class="col">
				<h1>Clientes</h1>
			</div>
			
		</div>
		</div>
		
		<!--2018.07.21 Formulario para aplicar busqueda por filtro -->
	<form:form modelAttribute="customerFilter" action="handleCustomer.do" method="post" name="handleCustomer" id="getCustomerByFilter" >
		<div class="container-result">	
			<p>Consultar clientes por filtro</p>
			<table class="table tableFilter bgcol">
			<tbody>
				<tr>
					<td>
						<span class="input-group-text">Nombre:<input type="text" name="name" id="" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Apellido Paterno:<input type="text" name="firstName" id="" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Apellido Materno:<input type="text" name="lastName" id="" class="form-control"></span>
					</td>
					<td>
						<span class="input-group-text">Email:<input type="text" name="email" id="" class="form-control"></span>
					</td>
				</tr>
				<tr>
					<td colspan=5>
					 <input type="submit" class="btn btn-dark" name="filter" value="Enviar" />	
					</td>
				</tr>
			</tbody>
			</table>	
		</div>
	</form:form>
		
		
		
		<!-- Mostramos los clientes por filtro-->
		<c:if test="${not empty customersByFilter}">
		<div class="containerShowResultQuery container-result">
		<div class="bgcol">
			<table class="table tableShowResultQuery">
				<thead>
					<tr>
						<th>id</th>
						<th>Nombre</th>
						<th>Ap Paterno</th>
						<th>Ap Materno</th>
						<th>Email</th>
						<th></th>
						<th></th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customersByFilter}" var="user">		
				 		<tr>
				 			<td>${user.userId}</td>
				 			<td>${user.name}</td>
				 			<td>${user.firstName}</td>
				 			<td>${user.secondName}</td>
				 			<td>${user.email}</td>
				 			<td><button type="button" class="btn btn-dark btnUpdateUser" id="btnUpdateUser" data-toggle="modal" data-target="#modalUpdateUser">Editar</button></td>
				 			<td>		 			
				 			<form:form action="handleUser.do" method="post" name="deleteUserForm" id="deleteUserForm">
								<input type="hidden" name="userId" id="deleteUserId" value="${user.userId}">			 	
				 			 	<input type="submit" class="btn btn btn-dark" name="delete" value="Eliminar" />	
				 			 </form:form>
				 			</td>
				 		</tr>	 	
			 		</c:forEach>
			 	</tbody>
	 		</table>
	 	</div>
	 	</div>
		</c:if>
		<button type="button" class="btn btn-dark" data-toggle="modal" data-target="#modalAddUser">Agregar cliente</button>





 <div id="modalAddUser" class="modal fade" role="dialog" >
 <div class="modal-content" style="width: 700px; height: 600px; margin: auto; margin-top: 30px;overflow: auto;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agregar Cliente</h4>
      </div>
      <div class="modal-body">     
		<form:form modelAttribute="clientDTO" action="handleCustomer.do" method="post" name="addUserForm">
				<div class="form-group">
					<label>Nombre: </label>
					<input type="text" id="name" name="name" placeholder="Nombre" class="form-control">
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
					<label>Tel1: </label>
					<input type="number" id="tel1" name="tel1" placeholder="Telefono celular" class="form-control">
				</div>
				<div class="form-group">	
					<label>Tel2:</label>
					<input type="number" id="tel2" name="tel2" placeholder="Telefono casa" class="form-control">
				</div>
				<div class="form-group">	
					<label>Direccion:</label>
					<input type="text" id="adress" name="adress" placeholder="Direccion" class="form-control">
				</div>						
								
				<div class="form-group">
					<input type="submit" class="btn btn-dark login-button" name="add" value="Enviar" />					
				</div>
				
		</form:form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
    </div>
    
    
    
    
 <div id="modalUpdateUser" class="modal fade" role="dialog" >
 <div class="modal-content" style="width: 700px; height: 600px; margin: auto; margin-top: 30px;overflow: auto;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Editar Cliente</h4>
      </div>
      <div class="modal-body">     
		<form:form modelAttribute="clientDTO" action="handleCustomer.do" method="post" name="addUserForm">
				<div class="form-group">
					<label>Nombre: </label>
					<input type="text" id="name" name="name" placeholder="Nombre" class="form-control">
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
					<label>Tel1: </label>
					<input type="number" id="tel1" name="tel1" placeholder="Telefono celular" class="form-control">
				</div>
				<div class="form-group">	
					<label>Tel2:</label>
					<input type="number" id="tel2" name="tel2" placeholder="Telefono casa" class="form-control">
				</div>
				<div class="form-group">	
					<label>Direccion:</label>
					<input type="text" id="adress" name="adress" placeholder="Direccion" class="form-control">
				</div>						
								
				<div class="form-group">
					<input type="submit" class="btn btn-dark login-button" name="update" value="Enviar" />					
				</div>
				
		</form:form>
		   <p>Editar cliente</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
      
    </div>
    </div>
    
    </div>
    
    


</body>
</html>