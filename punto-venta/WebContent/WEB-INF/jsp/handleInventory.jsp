<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<script type="text/javascript" src="js/admin/inventory.js?v1.1"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punto de venta :: Inventario</title>
<style>

</style>

</head>
<body>
<div data-spy="scroll" data-target="#navbar-example3" data-offset="0">
 <div class="container" >

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
		<div class="page-header">
		<div class="row">
			<div class="col">
				<h1>Inventario</h1>
			</div>
			
		</div>
		</div>
		
	<!--2018.07.21 Formulario para aplicar busqueda por filtro -->
	<form:form modelAttribute="saleNoteFilter" action="handleInventory.do" method="post" name="handleInventory" id="getItemsByFilter" >
		<div class="container-result">	
			<p>Consultar productos por filtro</p>
			<table class="table tableFilter bgcol">
			<tbody>
				<tr>
					<td>
						<span class="input-group-text">Fecha de alta inicial:<input type="date" name="iniDateFilter" id="iniDateFilter" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Fecha alta final:<input type="date" name="endDateFilter" id="endDateFilter" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Descripci&oacute;n:<input type="text" name="descriptionFilter" id="" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Almacen: 
							<select name="storeIdFilter" class="form-control" id="storeIdFilter">
										<option value="">- Seleccione -</option>
									<c:forEach items="${listStore}" var="store">
										<option value="${store.storeId}">${store.description}</option>
									</c:forEach>	
							</select>
						</span>
					</td>
<!-- 					<td> -->
<!-- 					<span class="input-group-text">Color:  -->
<!-- 				        <select name="colorIdFilter" class="form-control selColorId"> -->
<!-- 						<option value="">- Seleccione -</option> -->
<%-- 							<c:forEach items="${listColors}" var="color"> --%>
<%-- 								<option value="${color.colorId}">${color.description}</option> --%>
<%-- 							</c:forEach>	 --%>
<!-- 						</select>	 -->
<!-- 					</span>		         -->
<!-- 			        </td> -->
			        <td>
					<span class="input-group-text">Sucursal: 
				        <select name="officeIdFilter" class="form-control">
						<option value="">- Seleccione -</option>
							<c:forEach items="${listOffices}" var="office">
								<option value="${office.officeId}">${office.name}</option>
							</c:forEach>	
						</select>	
					</span>		        
			        </td>						
				</tr>
				<tr>
					<td colspan=4>
					 	<input type="submit" class="btn btn-dark" name="filter" value="Enviar" />	
					</td>
					<td colspan=2>
						<button type="button" class="btn btn-dark btnAddProduct" data-toggle="modal" data-target="">Agregar producto</button>
					</td>
<!-- 					<td colspan=1> -->
<!-- 						<button type="button" class="btn btn-dark btnAddColor" data-toggle="modal"  data-target="">Agregar Color</button> -->
<!-- 					</td> -->
				</tr>
			</tbody>
			</table>	
		</div>
	</form:form>

		
		<!-- Mostramos los productos -->
		<c:if test="${not empty listItems}">
		<div class="containerShowResultQuery container-result">
		<div class="bgcol">
<!-- 		<div class="container-items" style="height: 400px; position: relative; overflow: auto;"> -->
			<table class="table tableShowResultQuery">
			<thead>
				<tr>
					<td>Id</td>
					<td>Fecha alta</td>
					<td>almacen</td>
					<td>Sucursal</td>
					<td>Descripci&oacute;n</td>	
<!-- 					<td>Color</td>				 -->
					<td>U.M.</td>
<!-- 					<td>cantidad entrada</td> -->
<!-- 					<td>cantidad salida</td> -->
					<td>Precio venta</td>				
					<td>Cantidad existente</td>							
				</tr>
			</thead>
				<c:forEach items="${listItems}" var="item">		
			 		<tr>
			 			<td>${item.itemId}</td>
			 			<td>${item.date}</td>
			 			<td>${item.storeDTO.description}</td>
			 			<td>${item.officeDTO.name}</td>			 					 			
			 			<td>${item.description}</td>
<%-- 			 			<td>${item.color.description}</td>		 			 --%>
			 			<td>${item.unitMeasurement}</td>
<%-- 			 			<td>${item.amountEntry}</td> --%>
<%-- 			 			<td>${item.amountOutput}</td>		 			 --%>
			 			<td>${item.salePrice}</td>
			 			<td>${item.stock}</td>		 			
			 			<td><button type="button" class="btn btn-dark btnUpdate" id="btnUpdate" data-toggle="modal" data-target="#modalUpdate">Editar</button></td>
			 			<td>		 			
			 			<form:form action="handleInventory.do" method="post" name="deleteForm" id="deleteForm">
							<input type="hidden" name="itemId" id="deleteId" value="${item.itemId}">			 	
			 			 	<input type="submit" class="btn btn-dark" name="delete" value="Eliminar" />	
			 			 </form:form>
			 			</td>
			 		</tr>	 	
		 		</c:forEach>
		 		</table>
		 		</div>
	 		</div>
		</c:if>
		
		

 <div id="modalAdd" class="modal fade" role="dialog" >
 <div class="modal-content" style="width: 700px; height: 600px; margin: auto; margin-top: 30px;overflow: auto;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agregar producto</h4>
      </div>
      <div class="modal-body">     
		<form:form modelAttribute="ItemDTO" action="handleInventory.do" method="post" name="addForm" id="addForm">				
				
				<div class="form-group">
					<label>Almacen: </label>
					<select name="storeDTO.storeId" id="selStoreFilter" class="form-control">
					<option value="0">- Seleccione -</option>
						<c:forEach items="${listStore}" var="store">
							<option value="${store.storeId}">${store.description}</option>
						</c:forEach>	
					</select>
				</div>
				
				<div class="form-group">
					<label>Sucursal: </label>
					<select name="officeDTO.officeId" id="selOffice" class="form-control">
					<option value="0">- Seleccione -</option>
						<c:forEach items="${listOffices}" var="office">
							<option value="${office.officeId}">${office.name}</option>
						</c:forEach>	
					</select>
				</div>
				
<!-- 				<div class="form-group"> -->
<!-- 					<label>Color: </label> -->
<!-- 				<select name="color.colorId" id="selColorId" class="form-control selColorId"> -->
<!-- 				<option value="0">- Seleccione -</option> -->
<%-- 					<c:forEach items="${listColors}" var="color"> --%>
<%-- 						<option value="${color.colorId}">${color.description}</option> --%>
<%-- 					</c:forEach>	 --%>
<!-- 				</select> -->
<!-- 				</div> -->
				<div class="form-group">
					<label>Descripci&oacute;n: </label>
					<input type="text" id="description" name="description" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Unidad de Medida: </label>
					<input type="text" id="unitMeasurement" name="unitMeasurement" placeholder="" class="form-control">
				</div>
<!-- 				<div class="form-group"> -->
<!-- 					<label>Cantidad de entrada: </label> -->
<!-- 					<input type="number" id="amountEntry" name="amountEntry" placeholder="" class="form-control"> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label>Cantidad de salida: </label> -->
<!-- 					<input type="number" id="amountOutput" name="amountOutput" placeholder="" class="form-control"> -->
<!-- 				</div> -->
				<div class="form-group">
					<label>Precio de venta: </label>
					<input type="number" id="salePrice" name="salePrice" class="form-control">
				</div>
				<div class="form-group">
					<label>Cantidad existente: </label>
					<input type="number" id="stock" name="stock" class="form-control">
				</div>
				
				<div class="form-group">
					<input type="submit" class="btn btn-dark" name="add" value="Enviar" />					
				</div>
		</form:form>
<!-- 		   <p>Agregar producto</p> -->
      </div>
<!--       <div class="modal-footer" style="bottom: 20px;"> -->
<!--         <button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button> -->
<!--       </div> -->
    </div>
    </div>
    
    
 
 <div id="modalUpdate" class="modal fade" role="dialog" >
 <div class="modal-content" style="width: 700px; height: 600px; margin: auto; margin-top: 30px;overflow: auto;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Editar producto</h4>
      </div>
      <div class="modal-body">     
		<form:form modelAttribute="ItemDTO" action="handleInventory.do" method="post" name="updateForm" id="updateForm">
				
				<input type="hidden" name="itemId" id="itemId">
					
				<div class="form-group">
					<label>Almacen: </label>
					<select name="storeDTO.storeId" id="selStoreId" class="selStoreId form-control">
					<option value="0">- Seleccione -</option>
						<c:forEach items="${listStore}" var="store">
							<option value="${store.storeId}">${store.description}</option>
						</c:forEach>	
					</select>
				</div>


				<div class="form-group">
					<label>Sucursal: </label>
					<select name="officeDTO.officeId" id="selOffice" class="selOffice form-control">
					<option value="0">- Seleccione -</option>
						<c:forEach items="${listOffices}" var="office">
							<option value="${office.officeId}">${office.name}</option>
						</c:forEach>	
					</select>
				</div>
				
<!-- 				<div class="form-group"> -->
<!-- 					<label>Color: </label> -->
<!-- 					<select name="color.colorId" id="selOffice" class="form-control selColorId"> -->
<!-- 					<option value="0">- Seleccione -</option> -->
<%-- 						<c:forEach items="${listColors}" var="color"> --%>
<%-- 							<option value="${color.colorId}">${color.description}</option> --%>
<%-- 						</c:forEach>	 --%>
<!-- 					</select> -->
<!-- 				</div> -->
				
				
				<div class="form-group">
					<label>Descripci&oacute;n: </label>
					<input type="text" id="description" name="description" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Unidad de Medida: </label>
					<input type="text" id="unitMeasurement" name="unitMeasurement" placeholder="" class="form-control">
				</div>
<!-- 				<div class="form-group"> -->
<!-- 					<label>Cantidad de entrada: </label> -->
<!-- 					<input type="text" id="amountEntry" name="amountEntry" placeholder="" class="form-control"> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label>Cantidad de salida: </label> -->
<!-- 					<input type="text" id="amountOutput" name="amountOutput" placeholder="" class="form-control"> -->
<!-- 				</div> -->
				<div class="form-group">
					<label>Precio de venta: </label>
					<input type="number" id="salePrice" name="salePrice" class="form-control">
				</div>
				<div class="form-group">
					<label>Cantidad existente: </label>
					<input type="number" id="stock" name="stock" class="form-control">
				</div>
				
					<div class="form-group">
					<input type="submit" class="btn btn-dark" name="update" value="Enviar" />					
				</div>
		</form:form>
<!-- 		   <p>Editar producto</p> -->
      </div>
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button> -->
<!--       </div> -->
    </div>
    </div> <!-- fin modal editar -->   
    
     <!-- modal para agregar color -->
		<div id="modalAddColor" class="modal fade" role="dialog">
			<div class="modal-content"		style="width: 50%;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Agregar color</h4>
				</div>
				<div class="modal-body">
					<form name="addColorForm" id="addColorForm">
						<p>
							<input type="text" class="form-control" id="colorDescription">
						</p>

						<input type="button" onclick="addColor();" class="btn btn-dark" value="Enviar" />
					</form>

<!-- 					<p>Agregar color</p> -->
				</div>
<!-- 				<div class="modal-footer"> -->
<!-- 					<button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button> -->
<!-- 				</div> -->
			</div>
		</div><!-- end modal add color -->
		
    
    
    </div>

</div>

<script type="text/javascript">
$( document ).ready(function() {
	
	
	
	$( '.btnAddProduct' ).click(function() {
		if('${userSession.account.job.jobId}' == '2')
			alert("No tienes suficientes permisos para esta accion :( ")
			else
				$('#modalAdd').modal('toggle');
	});
		
	$( '.btnAddColor' ).click(function() {
		if('${userSession.account.job.jobId}' == '2')
			alert("No tienes suficientes permisos para esta accion :( ")
			else
				$('#modalAddColor').modal('toggle');
	});
	//confirmar eliminar	
	$('form[name="deleteForm"]').submit(function() {
		if('${userSession.account.job.jobId}' == '2'){
			alert("No tienes suficientes permisos para esta accion :( ")
			return false;
		}
	   return confirm("confirma para continuar");	   
	});
	
	
	$( '.btnUpdate' ).click(function() {
		if('${userSession.account.job.jobId}' == '2'){
			alert("No tienes suficientes permisos para esta accion :( ")
			return false;
		}
			
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
		        	$updateForm.find('#selStoreId').val(getValueSelect(item.innerHTML));
		       if(i===3)
		        	$updateForm.find('#selOffice').val(getValueOffice(item.innerHTML));
		        if(i===4)
		        	$updateForm.find('#description').val(item.innerHTML);
// 		        if(i===5)
// 		        	$updateForm.find('.selColorId').val(getColorSelect(item.innerHTML));
		        if(i===5)
		        	$updateForm.find('#unitMeasurement').val(item.innerHTML);
		        
// 		        if(i===6)
// 		        	$updateForm.find('#amountEntry').val(item.innerHTML);
// 		        if(i===7)
// 		        	$updateForm.find('#amountOutput').val(item.innerHTML);
		        if(i===6)
		        	$updateForm.find('#salePrice').val(item.innerHTML);
		        
		        if(i===7)
		        	$updateForm.find('#stock').val(item.innerHTML);
		       
		        
// 		        alert(values);
		    });
		    function getValueOffice(text){
		    	var value = 0;
		    	$( ".selOffice option" ).each(function( index ) {
		    		var x = $(this).text();
		    		if(x == text){
		    			value = index;
		    		}
// 		    		  console.log( index + ": " + $( this ).text() );
		    	});
		    	return value;
		    }
		    function getValueSelect(text){		    	
		    	var value = 0;
		    	$( ".selStoreId option" ).each(function( index ) {
		    		var x = $(this).text();
		    		if(x == text){
		    			value = index;
		    		}
// 		    		  console.log( index + ": " + $( this ).text() );
		    	});
		    	return value;
		    }
		    
		    function getColorSelect(text){		    	
		    	var value = 0;
		    	$( ".selColorId option" ).each(function(key, index ) {
		    		var x = $(this).text();
		    		if(x == text){
						value = index.value;
		    		}
		    	});
		    	return value;
		    }
		    console.log(values);
	});
});// end document ready


//2018.06.26 GTL agregar un color via AJAX
function addColor(){
	var color = $('#colorDescription').val();
	if(color != ''){		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "addColor.do",
			data : color,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {				
				alert(data.message)
				$('#modalAddColor').modal('toggle');
				appendsColorsToSelects(data.color);
				g_colors = data.colors;
				console.log("COLORS: "+g_colors)
			},
			error : function(e) {
				console.log("ERROR: ", e);	
				alert("ERROR: "+e)
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}else{
		alert("No se recibio el parametro, porfavor recarga la pagina e intentalo de nuevo :( ")
	}
} // end agregar color

//funcion para agregar los colores a los select despues de realizar un insert
function appendsColorsToSelects(color){
	// actualizar la lista de la tabla agregar
	var $formAdd = $('#addForm');
	var $updateForm = $('#updateForm');
	var $tableFilter = $('.tableFilter');
	$formAdd.find('.selColorId').append($('<option>', {
	    value: color.colorId,
	    text: color.description
	}));
	
	$updateForm.find('.selColorId').append($('<option>', {
	    value: color.colorId,
	    text: color.description
	}));
	
	$tableFilter.find('.selColorId').append($('<option>', {
	    value: color.colorId,
	    text: color.description
	}));
}
</script>    


</body>
</html>