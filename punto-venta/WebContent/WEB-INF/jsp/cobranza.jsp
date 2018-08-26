<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punto de venta :: admin usuarios</title>
<style>		    
    

</style>
<script type="text/javascript">
$( document ).ready(function() {
		$('.btnAbonar').click(function(){
			var saleId = $(this).attr('data-value');
			var $form = $('#formAbono');
			$form.find('#saleId').val(saleId);
			$('#modalAbonar').modal('show');
		});
		
		// validando formulario
		$('form[name="formAbono"]').submit(function (e) {
			var valid = true;
			var msg = '';
			var cont = 0;
			var $form = $('#formAbono');
			var cantidad = $form.find('#cantidadAbono').val();
			var tipo = $form.find('.tipoAbono').val();
			
			if(cantidad == '')
				msg += ++cont + '. Ingresa una cantidad a abonar\n';
			if(tipo == '0')
				msg += ++cont + '. Ingresa un tipo de abono\n';
			
			if(msg != ''){
				valid = false;
				alert(msg);
			}
			
			return valid;
		});
		
		
	 	
}); // end document ready

//2018.08.25 obtener los abonos
function getSaleNoteById(id){
	var data = {}
	var x = id;
	if(id != ''){			
			
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getSaleNoteById.do",
			data : x,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {					
				mostrarAbonos(data.noteForm);
				console.log(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}else{
		alert("No se recibio el parametro, porfavor recarga la pagina e intentalo de nuevo :( ")
	}
}

function mostrarAbonos(data){
	var $form = $('#formMostrarAbonos');
	var cont = 0;
	$form.find('.tablaAbonos tbody tr td').remove();
	$form.find('#descripcionVenta').text(data.description);
	$.each(data.abonos, function(index, value) {	
		
		$form.find(".tablaAbonos tbody").append("<tr>"
				+"<td>"+ ++cont +"</td>"
				+"<td>"+ value.cantidadAbono +"</td>"
				+"<td>"+ value.descripcion +"</td>"
				+"<td>"+ value.feRegistroFormat +"</td>"
				+"<td>"+ value.tipoAbono.descripcion +"</td>"
				+"<td>"+ (value.usuario.name+" "+value.usuario.firstName) +"</td>"
				+"<td><a href='#' onclick='eliminarAbono("+value.abonoId+");'>Eliminar</a></td>"
		+"</tr>");
	});	// end for each
	
	$('#modalMostrarAbonar').modal('show');
}

function eliminarAbono(abonoId){
	if(confirm('Confirma para eliminar abono')){
		var data = {}
		var x = abonoId;
		if(abonoId != ''){				
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "eliminarAbono.do",
				data : x+"",
				dataType : 'json',
				timeout : 100000,
				success : function(data) {					
					console.log(data.mensaje);
					alert(data.mensaje);
					$('#modalMostrarAbonar').modal('hide');
				},
				error : function(e) {
					console.log("ERROR: ", e);				
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		}else{
			alert("No se recibio el parametro, porfavor recarga la pagina e intentalo de nuevo :( ")
		}
		
	}
}

</script>
</head>
<body>
 <div class="container" style="">

		<c:if test="${messageView != null}">
		<div class="alert alert-success">
  			<strong>Success!</strong> 
  			<c:set var = "message" value = "${messageView}"/>
  			<c:forEach var="item" items="${fn:split(message,'|')}">
        		<p>${item}</p>
			</c:forEach>
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
				<h1>Cobranza</h1>
			</div>			
		</div>
	</div>
	
		<!--2018.08.25 Formulario para aplicar busqueda por filtro -->
	<form:form modelAttribute="cobranzaFilter" action="cobranza.do" method="post" name="cobranza" id="cobranza" >
		<div class="container-result">	
			<p>Aplicar filtro</p>
			<table class="table tableFilter bgcol">
			<tbody>
				<tr>
					<td style="width:6%;">
						<span class="input-group-text">Folio:<input type="text" name="saleIdFilter" id="saleIdFilter" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Nombre del cliente<input type="text" name="customerNameFilter" id="customerNameFilter" class="form-control"></span>
					</td>
					<td>
						<span class="input-group-text">Fecha inicial:<input type="date" name="iniDateFilter" id="iniDateFilter" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Fecha final:<input type="date" name="endDateFilter" id="endDateFilter" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Estado venta: 
							<select name="statusFilter" class="form-control" id="statusFilter">
										<option value="0">- Seleccione -</option>
									<c:forEach items="${listStatus}" var="status">
										<option value="${status.statusId}">${status.description}</option>
									</c:forEach>	
							</select>
						</span>
					</td>	
					<td>
						<span class="input-group-text">Es a credito: 
							<select name="credit" class="form-control" id="credit">
										<option value="">- Seleccione -</option>									
										<option value="0">Nota sin cr&eacute;dito</option>
										<option value="1">Nota a cr&eacute;dito</option>									
							</select>
						</span>
					</td>							
				</tr>
				<tr>
					<td colspan=6>
					 <input type="submit" class="btn btn-dark" name="filter" value="Enviar" />	
					</td>
				</tr>
			</tbody>
			</table>	
		</div>
	</form:form>
	
	
		<!-- Mostramos las ventas a credito -->
		<c:if test="${not empty ventas}">
		<div class="containerShowResultQuery container-result">
		<table class="table tableShowResultQuery">
		<thead>
			<tr>
				<th>id</th>
				<th>Nombre cliente</th>
				<th>Descripci&oacute;n nota</th>
				<th>Total a pagar</th>
				<th>Abonos</th>
				<th>Resta</th>
				<th>Estado</th>
				<th>Fecha registro</th>
				<th>Fecha vencimiento</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ventas}" var="venta">		
		 		<tr>
		 			<td><a href="javascript:void(0);" onclick="getSaleNoteById('${venta.saleId}')">${venta.saleId}</a></td>
		 			<td>${venta.nameCustomer}</td>
		 			<td>${venta.description}</td>
					<td><fmt:formatNumber value="${venta.totalAmount}" type="currency" currencySymbol=""/></td>
					<td><fmt:formatNumber value="${venta.totalAbonos}" type="currency" currencySymbol=""/></td>
					<td><fmt:formatNumber value="${venta.resta}" type="currency" currencySymbol=""/></td>
					<td><c:out value="${venta.resta eq '0' ? 'Pagado' : 'Pendiente' }"></c:out></td>
					<td>${venta.saleDate}</td>
					<td>${venta.fechaVencimientoCredito}</td>
		 			<td><button type="button" class="btn btn-dark btnAbonar" id="btnAbonar" data-toggle="modal" data-value="${venta.saleId}">Abonar</button></td>
		 		</tr>	 	
	 		</c:forEach>
	 	</tbody>
	 		</table>
	 		</div>
		</c:if>


 <div id="modalAbonar" class="modal fade" role="dialog" >
 <div class="modal-content" style="width:50%;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agregar Abono</h4>
      </div>
      <div class="modal-body">     
		<form:form modelAttribute="abono" action="cobranza.do" method="post" name="formAbono" id="formAbono">
				<input type="hidden" name="saleNote.saleId" id="saleId">
					<div class="form-group row">
						<div class="col-xs-6">	
							<label>Cantidad a abonar: </label>
							<input type="number" id="cantidadAbono" name="cantidadAbono" min="1" max="1000000" class="form-control">
						</div>
						<div class="col-xs-6">	
						<label>Tipo de abono:</label>			
							<select name="tipoAbono.tipoAbonoId" class="form-control tipoAbono">
										<option value="0">- Seleccione -</option>
									<c:forEach items="${tiposAbonos}" var="tipo">								
										<option value="${tipo.tipoAbonoId}">${tipo.descripcion}</option>																
									</c:forEach>	
							</select>	
						</div>
					</div>
					<div class="form-group row">
						<div class="col-xs-12">				
							<label>Descripci&oacute;n:</label>
							<input type="text" name="descripcion" id="descripcionAbono" class="form-control">
						</div>
	  				</div>
			
											
				<div class="form-group">
					<input type="submit" class="btn btn-dark login-button" name="agregarAbono" value="Enviar" />					
				</div>
				
		</form:form>
      </div>      
    </div>
    </div> <!-- end agregar abono -->
    
    
    <div id="modalMostrarAbonar" class="modal fade" role="dialog" >
 <div class="modal-content" style="width:50%;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Abonos</h4>
      </div>
      <div class="modal-body">     
		<div id="formMostrarAbonos">
				
					<div class="form-group row">
						<div class="col-xs-12">	
							<label>Descripci&oacute;n venta: <span id="descripcionVenta"></span></label>
						</div>
					</div>
						<div class="form-group row">
							<table class="table tablaAbonos">
								<thead>
									<tr>
										<th>#</th>
										<th>Cantidad</th>
										<th>Descripci&oacute;n</th>
										<th>Fecha ingreso</th>
										<th>Tipo abono</th>
										<th>Usuario</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr>

									</tr>

								</tbody>
							</table>
						</div>


					</div>
      </div>      
    </div>
    </div> <!-- end mostrar abonos -->
  
    
    </div>
    
    


</body>
</html>