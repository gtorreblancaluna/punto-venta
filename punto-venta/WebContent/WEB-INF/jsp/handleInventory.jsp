<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punto de venta :: Inventario</title>
<script type="text/javascript">
$( document ).ready(function() {
	//confirmar eliminar	
	$('form[name="deleteForm"]').submit(function() {
	   return confirm("confirma para continuar");	   
	});
	
	
	$( '.btnUpdate' ).click(function() {
		var $updateForm = $("#updateForm");
		 var $row = jQuery(this).closest('tr');
		    var $columns = $row.find('td');

		    $columns.addClass('row-highlight');
		    var values = "";

		    jQuery.each($columns, function(i, item) {
		        values = values + 'td' + (i + 1) + ':' + item.innerHTML + '<br/>';
		        if(i===0)
		        	$updateForm.find('#itemId').val(item.innerHTML);
		        if(i===2)
		        	$updateForm.find('#storeDescription').val(item.innerHTML);
		        if(i===3)
		        	$updateForm.find('#unitMeasurement').val(item.innerHTML);
		        
		        if(i===4)
		        	$updateForm.find('#amountEntry').val(item.innerHTML);
		        if(i===5)
		        	$updateForm.find('#amountOutput').val(item.innerHTML);
		        if(i===6)
		        	$updateForm.find('#salePrice').val(item.innerHTML);
		        
		        if(i===7)
		        	$updateForm.find('#stock').val(item.innerHTML);
		       
		        
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
		<c:if test="${not empty listItems}">
		<table class="table">
		<thead>
			<tr>
				<td>id</td>
				<td>fecha alta</td>
				<td>almacen</td>
				<td>descripcion</td>				
				<td>U.M.</td>
				<td>cantidad entrada</td>
				<td>cantidad salida</td>
				<td>precio venta</td>				
				<td>cantidad existente</td>							
			</tr>
		</thead>
			<c:forEach items="${listItems}" var="item">		
		 		<tr>
		 			<td>${item.itemId}</td>
		 			<td>${item.date}</td>
		 			<td>${item.storeDTO.description}</td>	
		 			<td>${item.description}</td>		 			
		 			<td>${item.unitMeasurement}</td>
		 			<td>${item.amountEntry}</td>
		 			<td>${item.amountOutput}</td>		 			
		 			<td>${item.salePrice}</td>
		 			<td>${item.stock}</td>		 			
		 			<td><button type="button" class="btn btn-info btn-lg btnUpdate" id="btnUpdate" data-toggle="modal" data-target="#modalUpdate">Editar</button></td>
		 			<td>		 			
		 			<form:form action="handleInventory.do" method="post" name="deleteForm" id="deleteForm">
						<input type="hidden" name="itemId" id="deleteId" value="${item.itemId}">			 	
		 			 	<input type="submit" class="btn btn-info btn-lg" name="delete" value="Eliminar" />	
		 			 </form:form>
		 			</td>
		 		</tr>	 	
	 		</c:forEach>
	 		</table>
		</c:if>
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modalAdd">Agregar producto</button>





 <div id="modalAdd" class="modal fade" role="dialog" >
 <div class="modal-content" style="width: 700px; height: 600px; margin: auto; margin-top: 30px;overflow: auto;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agregar producto</h4>
      </div>
      <div class="modal-body">     
		<form:form commandName="ItemDTO" action="handleInventory.do" method="post" name="addForm">				
				
				<div class="form-group">
					<label>Almacen: </label>
<!-- 					<input type="text" id="storeDescription" name="storeDTO.description" placeholder="" class="form-control"> -->
				<select name="storeDTO.storeId">
				<option value="0">- Seleccione -</option>
					<c:forEach items="${listStore}" var="store">
						<option value="${store.storeId}">${store.description}</option>
					</c:forEach>	
				</select>
				</div>
				<div class="form-group">
					<label>Descripci&oacute;n: </label>
					<input type="text" id="description" name="description" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Unidad de Medida: </label>
					<input type="text" id="unitMeasurement" name="unitMeasurement" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Cantidad de entrada: </label>
					<input type="text" id="amountEntry" name="amountEntry" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Cantidad de salida: </label>
					<input type="text" id="amountOutput" name="amountOutput" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Precio de venta: </label>
					<input type="number" id="salePrice" name="salePrice" class="form-control">
				</div>
				<div class="form-group">
					<label>Cantidad existente: </label>
					<input type="number" id="stock" name="stock" class="form-control">
				</div>
				
					<div class="form-group">
					<input type="submit" class="btn btn-primary btn-lg btn-block login-button" name="add" value="Enviar" />					
				</div>
		</form:form>
		   <p>Agregar producto</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
    </div>
    
    
 
 <div id="modalUpdate" class="modal fade" role="dialog" >
 <div class="modal-content" style="width: 700px; height: 600px; margin: auto; margin-top: 30px;overflow: auto;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Editar producto</h4>
      </div>
      <div class="modal-body">     
		<form:form commandName="ItemDTO" action="handleInventory.do" method="post" name="updateForm">
				<input type="hidden" name="itemId" id="itemId">
				
				<div class="form-group">
					<label>Almacen: </label>
					<input type="text" id="storeDescription" name="storeDTO.description" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Descripci&oacute;n: </label>
					<input type="text" id="description" name="description" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Unidad de Medida: </label>
					<input type="text" id="unitMeasurement" name="unitMeasurement" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Cantidad de entrada: </label>
					<input type="text" id="amountEntry" name="amountEntry" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Cantidad de salida: </label>
					<input type="text" id="amountOutput" name="amountOutput" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Precio de venta: </label>
					<input type="number" id="salePrice" name="salePrice" class="form-control">
				</div>
				<div class="form-group">
					<label>Cantidad existente: </label>
					<input type="number" id="stock" name="stock" class="form-control">
				</div>
				
					<div class="form-group">
					<input type="submit" class="btn btn-primary btn-lg btn-block login-button" name="add" value="Enviar" />					
				</div>
		</form:form>
		   <p>Editar producto</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
    </div> <!-- fin modal editar -->   
    
    
    </div>
    
    


</body>
</html>