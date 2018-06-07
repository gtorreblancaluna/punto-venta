<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punto de venta :: admin usuarios</title>
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
		        	$updateForm.find('#email').val(item.innerHTML);
		        
// 		        alert(values);
		    });
		    console.log(values);
	});
});
</script>
</head>
<body>
 <div class="container">

		<c:if test="${message != null}">
		<div class="alert alert-success">
  			<strong>Success!</strong> ${message}
		</div>

		</c:if>
		<c:if test="${messageError != null}">
		<div class="alert alert-danger">
  			<strong>Error! </strong> ${messageError}
		</div>

		</c:if>
		<!-- Mostramos los usuarios -->
		<c:if test="${not empty listUser}">
		<table class="table">
		<thead>
			<tr>
				<td>id</td>
				<td>Nombre</td>
				<td>Email</td>
				<td>Admin</td>				
			</tr>
		</thead>
			<c:forEach items="${listUser}" var="user">		
		 		<tr>
		 			<td>${user.userId}</td>
		 			<td>${user.name}</td>
		 			<td>${user.email }</td>
		 			<td><c:out default="None" escapeXml="true" value="${user.fgAdmin eq '1' ? 'Admin' : '-' }" /></td>
		 			<td><button type="button" class="btn btn-info btn-lg btnUpdateUser" id="btnUpdateUser" data-toggle="modal" data-target="#modalUpdateUser">Editar</button></td>
		 			<td>		 			
		 			<form:form action="handleUser.do" method="post" name="deleteUserForm" id="deleteUserForm">
						<input type="hidden" name="userId" id="deleteUserId" value="${user.userId}">			 	
		 			 	<input type="submit" class="btn btn-info btn-lg" name="deleteUser" value="Eliminar" />	
		 			 </form:form>
		 			</td>
		 		</tr>	 	
	 		</c:forEach>
	 		</table>
		</c:if>
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modalAddUser">Agregar usuario</button>





 <div id="modalAddUser" class="modal fade" role="dialog" >
 <div class="modal-content" style="width: 700px; height: 600px; margin: auto; margin-top: 30px;overflow: auto;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agregar Usuario</h4>
      </div>
      <div class="modal-body">     
		<form:form commandName="account" action="handleUser.do" method="post" name="addUserForm">
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
					<label>Password: </label>
					<input type="password" id="password" name="password" placeholder="Password" class="form-control">
				</div>
				<div class="form-group">
					<label>Admin: </label>
					<input type="checkbox" id="fgAdmin" name="fgAdmin" class="form-control">
				</div>
				<div class="form-group mb-3">
				 <select class="custom-select" name="job.jobId" id="job.jobId">
					
					<option selected>Puesto</option>
					<option value="1"> Administrador</option>
					<option value="2"> Vendedor</option>
					<option value="3"> Chofer</option>
					</select>
				</div>				
								
				<div class="form-group">
					<input type="submit" class="btn btn-primary btn-lg btn-block login-button" name="addUser" value="Enviar" />					
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
        <h4 class="modal-title">Editar Usuario</h4>
      </div>
      <div class="modal-body">     
		<form:form commandName="account" action="handleUser.do" method="post" name="updateUserForm" id="updateUserForm">
		<input type="hidden" name="userId" id="userId">
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
					<label>Password: </label>
					<input type="password" id="password" name="password" placeholder="Password" class="form-control">
				</div>
				<div class="form-group">
					<label>Admin: </label>
					<input type="checkbox" id="fgAdmin" name="fgAdmin" class="form-control">
				</div>
				
					<div class="form-group">
					<input type="submit" class="btn btn-primary btn-lg btn-block login-button" name="updateUser" value="Enviar" />					
				</div>
		</form:form>
		   <p>Editar usuario</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
    </div>
    
    </div>
    
    


</body>
</html>