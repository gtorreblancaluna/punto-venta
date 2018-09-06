<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:useBean id="now" class="java.util.Date"/>    
<fmt:formatDate value="${now}" dateStyle="long"/>
<fmt:formatDate value="${now}" pattern="dd-MM-yyyy HH:mm:ss a z" />
<html>
<head>
<style type="text/css">
#modalAdd, .model-content{width:95%;margin:auto;}
#modalAddColor, .model-content{width:50%;margin:auto;}

</style>
<script type="text/javascript" src="js/admin/handleSaleNote.js?v1.1"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<title>Punto Venta:: Notas</title>
<script type="text/javascript">
var u_cont=0;
var g_colors="";

function generatePdf(id){
	window.open("generate_pdf_sale_note.do?idOp="+id+"", "Nota venta", "width=500,height=300");
};

$( document ).ready(function() {
	
	//
	if('${userSession.account.job.jobId}' == '2'){

		$( "#officeIdFilter" ).prop( "disabled", true );
	}
	
	
// 	alert('${accountSession}')
	

	$( "#addRow-add" ).click(function() {
		addRow();
	});
	$( "#addRow-update" ).click(function() {
		addRowUpdate();
	});
	var cont = 0;

	// funcion para agregar una fila a la tabla de agregar nota
	function addRow(){	
		++cont;	
		$(".tableAddNote tbody").append("<tr>"			
			+"<td style='width:2%'><span class='input-group-text tr-count-addNote"+(cont)+"'>"+ (cont) +"</span></td>"
			+"<td><input type='number' class='form-control' name='' id='txtFindItemById'></td>"
			+"<td>" +
					"<select name='items["+cont+"].itemIdForm' class='form-control selItems'>" +
					"<option value='0' data-value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listItems}' var='item'>" +
						"<option value='${item.itemId}' data-value='${item.itemId}|${item.description}|${item.salePrice}' >${item.description}</option> " +
					"</c:forEach> " +
					"</select>" +
			"</td>"
			/*
			+"<td>" +
					"<select name='items["+cont+"].color.colorId' class='form-control selColors'>" +
					"<option value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listColors}' var='color'>" +
						"<option value='${color.colorId}'>${color.description}</option> " +
					"</c:forEach> " +
					"</select>" +
			"</td>"
			*/
			+"<td><input type='text' class='form-control' name='items["+ cont +"].description' id='itemDescription' disabled></td>"
			+"<td><input type='number' class='form-control' name='items["+ cont +"].amountEntry' id='amountItem' ></td>"
			+"<td><input type='number' class='form-control' name='items["+ cont +"].salePrice' id='itemPrice'></td>"
			+"<td><input type='number' class='form-control totalItem' name='' id='totalItem' disabled></td>"
			+"<td><button type='button' class='btnDelete'>Eliminar</button></td>"
		+"</tr>");
		
		if(g_colors != ""){
			var $tableAddNote = $('.tableAddNote');
			$tr = $tableAddNote.find(".tr-count-addNote"+(cont)+"").closest('tr');	
			$tr.find('.selColors').empty();
			
			$tr.find('.selColors').append($('<option>', {
			    value: 0,
			    text: '- Seleccione -'
			}));
			$.each(g_colors, function(index, value) {	
				$tr.find('.selColors').append($('<option>', {
				    value: value.colorId,
				    text: value.description
				}));
			});	// end for each
		}
	};
	
	// funcion para agregar una fila a la tabla de actualizar nota
	function addRowUpdate(){			
		$(".tableUpdateNote tbody").append("<tr>"			
				+"<td style='width:2%'><span class='input-group-text tr-count"+(u_cont+1)+"'>"+ (u_cont+1) +"</span></td>"
			+"<td><input type='number' class='form-control' name='' id='txtFindItemById' disabled></td>"
			+"<td>" +
					"<select name='items["+u_cont+"].itemIdForm' class='form-control selItems' disabled>" +
					"<option value='0' data-value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listItems}' var='item'>" +
						"<option value='${item.itemId}' data-value='${item.itemId}|${item.description}|${item.salePrice}' >${item.description}</option> " +
					"</c:forEach> " +
					"</select>" +
			"</td>"
			/*
			+"<td> <select name='items["+u_cont+"].color.colorId' class='form-control selColors' disabled>" +
					"<option value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listColors}' var='color'>" +
						"<option value='${color.colorId}'>${color.description}</option> " +
					"</c:forEach> " +
					"</select>" +
			"</td>" */
			+"<td><input type='text' class='form-control' name='items["+ u_cont +"].description' id='itemDescription' disabled></td>"
			+"<td><input type='number' class='form-control' name='items["+ u_cont +"].amountEntry' id='amountItem' disabled></td>"
			+"<td><input type='number' class='form-control' name='items["+ u_cont +"].salePrice' id='itemPrice' disabled></td>"
			+"<td><input type='number' class='form-control totalItem' name='' id='totalItem' disabled></td>"
			+"<td><button type='button' class='btnDelete' disabled>Eliminar</button></td>"
		+"</tr>");
		
		if(g_colors != ""){
			var $tableUpdateNote = $('.tableUpdateNote');
			$tr = $tableUpdateNote.find(".tr-count"+(u_cont+1)+"").closest('tr');	
			$tr.find('.selColors').empty();
			
			$tr.find('.selColors').append($('<option>', {
			    value: 0,
			    text: '- Seleccione -'
			}));
			$.each(g_colors, function(index, value) {	
				$tr.find('.selColors').append($('<option>', {
				    value: value.colorId,
				    text: value.description
				}));
			});	// end for each
		}
		
		
		u_cont++;
	};
	
	
	
});

//agregar los detalles de la venta
function addSaleDetailNoteForm(items){
//	$('.tableUpdateNote').remove();
	var $tableUpdateNote = $('.tableUpdateNote');
	
	$.each(items, function(index, value) {		
		$(".tableUpdateNote tbody").append("<tr>"			
			+"<td style='width:2%'><span class='input-group-text tr-count"+(u_cont+1)+"'>"+ (u_cont+1) +"</span></td>"
			+"<td><input type='number' class='form-control' name='' id='txtFindItemById' disabled></td>"
			+"<td>" +
					"<select name='items["+u_cont+"].itemIdForm' class='form-control selItems' disabled>" +
					"<option value='0' data-value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listItems}' var='item'>" +
						"<option value='${item.itemId}' data-value='${item.itemId}|${item.description}|${item.salePrice}' >${item.description}</option> " +
					"</c:forEach> " +					
					"</select>" +
			"</td>"+
			/*
			+"<td>" +
					"<select name='items["+u_cont+"].color.colorId' class='form-control selColors' disabled>" +
					"<option value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listColors}' var='color'>" +
						"<option value='${color.colorId}'>${color.description}</option> " +
					"</c:forEach> " +
					"</select>" +
			"</td>"
			*/
			+"<td><input type='text' class='form-control' name='items["+ u_cont +"].description' id='itemDescription' disabled></td>"
			+"<td><input type='number' class='form-control' name='items["+ u_cont +"].amountEntry' id='amountItem' disabled ></td>"
			+"<td><input type='number' class='form-control' name='items["+ u_cont +"].salePrice' id='itemPrice' disabled></td>"
			+"<td><input type='number' class='form-control totalItem' name='' id='totalItem' disabled></td>"
			+"<td><button type='button' class='btnDelete' disabled>Eliminar</button></td>"
		+"</tr>");
		
		if(g_colors != ""){
			$tr = $tableUpdateNote.find(".tr-count"+(u_cont+1)+"").closest('tr');	
			$tr.find('.selColors').empty();
			
			$tr.find('.selColors').append($('<option>', {
			    value: 0,
			    text: '- Seleccione -'
			}));
			$.each(g_colors, function(index, value) {	
				$tr.find('.selColors').append($('<option>', {
				    value: value.colorId,
				    text: value.description
				}));
			});	// end for each
		}
		
		// ahora colocamos el valor del articulo en las filas	
		var $tr="";
		$tr = $tableUpdateNote.find(".tr-count"+(u_cont+1)+"").closest('tr');
		$tr.find('.selItems').val(value.item.itemId);				
// 		var val = $('option:selected', $tr.find('.selItems')).attr('data-value').split("|");	
// 		var val = $tr.find('.selItems option').val(value.item.itemId).attr('data-value').split("|");
// 		$tr.find('#itemDescription').val(val[1]);
// 		$tr.find('#itemPrice').val(val[2]);
		$tr.find('#itemDescription').val(value.item.description);
		$tr.find('#itemPrice').val(value.item.salePrice);
		$tr.find('#amountItem').val(value.amount);
// 		$tr.find('.selColors').val(value.color.colorId);
		var amount = parseFloat(value.amount);
		var salePrice = parseFloat(value.item.salePrice);
		$tr.find('#totalItem').val(amount*salePrice);
		u_cont++;
		
	});	// end for each
	
	totalUpdateForm();	
	
}

</script>
</head>
<body>
<br>
<br>
<body>

<div class="container " style="">
	<c:if test="${messageSucess != null}">
		<div class="alert alert-success">
  			<strong>Success!</strong> 
  			<c:set var = "message" value = "${messageSucess}"/>
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
				<h1>Venta-dia</h1>
			</div>
			
		</div>
	</div>
	
	<!--2018.06.05 Formulario para aplicar busqueda por filtro -->
	<form:form modelAttribute="saleNoteFilter" action="handleSaleNote.do" method="post" name="saleNoteFilter" id="getSaleNoteByFilter" >
		<div class="container-result">	
			<p>Consultar nota de venta</p>
			<table class="table tableFilter bgcol">
			<tbody>
				<tr>
					<td style="width:6%;">
						<span class="input-group-text">Folio:<input type="text" name="saleIdFilter" id="saleIdFilter" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Fecha inicial:<input type="date" name="iniDateFilter" id="iniDateFilter" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Fecha final:<input type="date" name="endDateFilter" id="endDateFilter" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Descripci&oacute;n:<input type="text" name="descriptionFilter" id="descriptionFilter" class="form-control"></span>
					</td>
					<td>
						<span class="input-group-text">Sucursal : 
							<select name="officeIdFilter" class="form-control" id="officeIdFilter">
										<option value="0">- Seleccione -</option>
									<c:forEach items="${listOffices}" var="office">
										<option value="${office.officeId}">${office.name}</option>
									</c:forEach>	
							</select>
						</span>
					</td>
					<td>
						<span class="input-group-text">Nombre del cliente<input type="text" name="customerNameFilter" id="customerNameFilter" class="form-control"></span>
					</td>
					<td>
						<span class="input-group-text">Estatus: 
							<select name="statusFilter" class="form-control" id="statusFilter">
										<option value="0">- Seleccione -</option>
									<c:forEach items="${listStatus}" var="status">
										<option value="${status.statusId}">${status.description}</option>
									</c:forEach>	
							</select>
						</span>
					</td>
					
				</tr>
				<tr>
					<td colspan=5>
					 <input type="submit" class="btn btn-dark" name="filter" value="Enviar" />	
					</td>
					<td colspan=2>
						<button type="button" class="btn btn-dark" data-toggle="modal" data-target="#modalAdd">Agregar nota</button>
					</td>
				</tr>
			</tbody>
			</table>	
		</div>
	</form:form>
	
	<c:if test="${not empty listSaleNoteByFilter}">
	<!-- Mostramos el resultado de la consulta -->
		<div class="containerShowResultQuery container-result">
			<div class="bgcol">
				<table class="table tableShowResultQuery">
				<thead>
				<tr>
					<th>id</th>
					<th>Fecha registro</th>
					<th>Fecha de entrega</th>
					<th>Descripci&oacute;n</th>
					<th>Cliente</th>
					<th>Sucursal</th>
					<th>Usuario</th>
					<th>Estatus</th>
					<th></th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${listSaleNoteByFilter}" var="saleNote">
					<tr>
						<td><a href="javascript:void();" onclick="getSaleNoteById('${saleNote.saleId}');return false;" >${saleNote.saleId }</a></td>
						<td>${saleNote.saleDate}</td>
						<td>${saleNote.dateDelivery}</td>
						<td>${saleNote.description}</td>
						<td>${saleNote.nameCustomer}</td>
						<td>${saleNote.nameOffice}</td>
						<td>${saleNote.nameUser}</td>
						<td><a href="javascript:void();" onclick="javascript:changeStatus(${saleNote.saleId});">${saleNote.status.description}</a></td>
						<td><a href="javascript:void();" onclick="javascript:generatePdf(${saleNote.saleId });">Imprimir</a></td>
					</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</c:if>
	
	

<div id="modalAdd" class="modal fade" role="dialog" >
 <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agregar nota</h4>
      </div>
      <div class="modal-body">
	
	<form:form modelAttribute="saleNoteForm" action="handleSaleNote.do" method="post" name="saleNoteForm" id="addSaleNoteForm">
		
		<ul class="nav nav-tabs">
		  <li class="active"><a data-toggle="tab" href="#tabClientes">Clientes</a></li>
		  <li><a data-toggle="tab" href="#tabVentas">Nota</a></li>
		  <li><a data-toggle="tab" href="#tabAbono">Abono</a></li>
		</ul>
<!-- 		<div class="form-group row"> -->
<!-- 				<div class="col-xs-3"> -->
<!-- 					<label style="padding-left:12px;">Subtotal: $<span id="totalPagar"></span></label> -->
<!-- 					<label style="padding-left:12px;">Abono: $<span id="totalAbono"></span></label> -->
<!-- 					<label style="padding-left:12px;">Total: $<span id="total"></span></label> -->
<!-- 				</div> -->
<!-- 		</div> -->
		<div class="tab-content">
		
			<div id="tabClientes" class="tab-pane fade in active">
				<div class="form-group row">
					<input type="hidden" id="customerId" name="customer.userId" value="0">
					
					<div class="col-xs-3">
						<label>Nombre: </label>
						<input type="text" id="name" name="customer.name" placeholder="Nombre" class="form-control">
					</div>
					<div class="col-xs-3">
						<label>Apellido paterno: </label>
						<input type="text" id="apPaterno" name="customer.firstName" placeholder="Apellido Paterno" class="buscarCliente form-control">
					</div>
					<div class="col-xs-3">
						<label>Apellido materno: </label>
						<input type="text" id="apMaterno" name="customer.secondname" placeholder="Apellido Materno" class="form-control">
					</div>
<!-- 					<div class="col-xs-3"> -->
<!-- 						<label>Email: </label> -->
<!-- 						<input type="text" id="email" name="customer.email" placeholder="Email" class="form-control"> -->
<!-- 					</div> -->
					<div class="col-xs-3">
						<label>Tel&eacute;fono 1: </label>
						<input type="text" id="tel1" name="customer.tel1" placeholder="Telefono celular" class="form-control">
					</div>
					<div class="col-xs-3">
						<label>Tel&eacute;fono 2: </label>
						<input type="text" id="tel2" name="customer.tel2" placeholder="Telefono casa" class="form-control">
					</div>
					<div class="col-xs-3">
						<label>Direcci&oacute;n: </label>
						<input type="text" id="direccion" name="customer.adress" placeholder="Direcci&oacute;n" class="form-control">
					</div>
					</div>
					<div class="form-group row">			
						<div class="col-xs-6">
							<input type="button" class="btn btn-dark login-button btnContinuarVenta"  value="Continuar" />					
						</div>
					
						<div class="form-group row">			
							<div class="col-xs-6">
								<input type="button" class="btn btn-dark login-button btnHabilitarFormCliente"  value="Habilitar formulario" />					
							</div>
						</div>
					</div>
					
					<div class="form-group row ">
						
						<table class="table tablaClientes">
						    <thead>
						      <tr>
						      	<th>#</th>
						      	<th>Id</th>
						        <th>Nombre</th>			        
						        <th>Ap paterno</th>
						        <th>Ap materno</th>
<!-- 						        <th>email</th> -->
						        <th>tel1</th>
								<th>tel2</th>
						        <th>Direcci&oacute;n</th>
						      </tr>
						    </thead>
						     <tbody>
						     <tr></tr>
						     </tbody>
					     </table>
					</div>
				
			</div><!-- end tab clientes -->
		
		
		
			<div id="tabVentas" class="tab-pane fade in ">
			
			<div class="form-group row">
			
				<div class="col-xs-2">
					<label >Fecha entrega:</label>
					<input type="date" name="dateSaleNote" id="dateForm" class="form-control dateForm">
				</div>
				
<!-- 				<div class="col-xs-3"> -->
<!-- 					<label >Cliente : </label> -->
<!-- 							<select name="userId" class="form-control userId" > -->
<!-- 										<option value="0">- Seleccione -</option> -->
<%-- 									<c:forEach items="${listClients}" var="client"> --%>
<%-- 										<option value="${client.userId}">${client.name} ${client.firstName}</option> --%>
<%-- 									</c:forEach>	 --%>
<!-- 							</select>				 -->
<!-- 				</div> -->
				<div class="col-xs-2">
						<label>Vendedor :</label> 
							<select name="sellerId" class="form-control sellerId" >
										<option value="0">- Seleccione -</option>
									<c:forEach items="${listUsers}" var="user">
										<!-- Solo mostrar usuarios que sean vendedores -->
										<c:if test="${user.job.description eq 'vendedor' }">
											<option value="${user.userId}">${user.name} ${user.firstName}</option>
										</c:if>									
									</c:forEach>	
							</select>					
				</div>
<!-- 				<td> -->
<!-- 				<span class="input-group-text">Sucursal :  -->
<!-- 					<select name="storeId" class="form-control storeId" id="storeId"> -->
<!-- 								<option value="0">- Seleccione -</option> -->
<%-- 							<c:forEach items="${listOffices}" var="office"> --%>
<%-- 								<option value="${office.officeId}">${office.name}</option> --%>
<%-- 							</c:forEach>	 --%>
<!-- 					</select> -->
<!-- 				</span> -->
				
<!-- 				</td> -->
				
				<div class="col-xs-8">				
					<label style="padding-left:12px;">Cliente: <span id="spanNombreCliente"></span></label>
<!-- 				</div> -->
<!-- 				<div class="col-xs-3">	 -->
					<label style="padding-left:12px;">Subtotal: $<span id="totalPagar"></span></label>
					<label style="padding-left:12px;">Abono: $<span id="totalAbono"></span></label>
					<label style="padding-left:12px;">Total: $<span id="total"></span></label>
				</div>
			</div>
			<div class="form-group row">	
								
				<div class="col-xs-6">				
					<label>Descripci&oacute;n:</label>
					<input type="text" name="description" id="description" class="form-control">
				</div>		
				<div class="col-xs-6">				
					<label style="cursor:pointer;">
						<input type="checkbox" name="printSaleNote" class="" checked>
						<span> Deseo imprimir nota al finalizar</span>
					</label>	
				</div>	
				
			</div>
		
	
		<div class="form-group row">
     		<table class="table tableAddNote">
			    <thead>
			      <tr>
			      	<th>#</th>
			      	<th style="width:10%;">Buscar por id</th>
			        <th>Articulo</th>			        
<!-- 			        <th ><a href="javascript:void();" onclick="agregarColor();">Color</a></th> -->
			        <th>Descripci&oacute;n</th>
			        <th>Cantidad</th>
			        <th>Precio</th>
			        <th>Total</th>
			        <th></th>			        
			      </tr>
			    </thead>
			    <tbody>
			     <tr>
			     	<td style="width:2%"><span class="input-group-text" >1</span></td>
			     	<td >
			     		<input type="number" class="form-control" name="" id="txtFindItemById" >
			     	</td>			     		
			        <td >
			        	
				        <select name="items[0].itemIdForm" class="form-control selItems">
						<option value="0" data-value="0">- Seleccione -</option>
							<c:forEach items="${listItems}" var="item">
								<option value="${item.itemId}" data-value="${item.itemId}|${item.description}|${item.salePrice}" >${item.description}</option>
							</c:forEach>	
						</select>			        
			        </td>			      
			        
<!-- 			         <td> -->
<!-- 				        <select name="items[0].color.colorId" class="form-control selColors"> -->
<!-- 						<option value="0">- Seleccione -</option> -->
<%-- 							<c:forEach items="${listColors}" var="color"> --%>
<%-- 								<option value="${color.colorId}">${color.description}</option> --%>
<%-- 							</c:forEach>	 --%>
<!-- 						</select>			         -->
<!-- 			        </td> -->
			        <td><input type="text" class="form-control" name="items[0].description" id="itemDescription" disabled></td>
			        <td><input type="number" class="form-control" name="items[0].amountEntry" id="amountItem"></td> 					
			        <td><input type="number" class="form-control" name="items[0].salePrice" id="itemPrice" ></td>
			        <td><input type="number" class="form-control totalItem" name="" id="totalItem" disabled></td>
			        <td><input type="button" class="btn btn-dark" id="addRow-add" value="Agregar" /></td>			        
			      </tr>			
			      	      	      
			    </tbody>			    
			  </table>
			 	
	  	</div> <!-- end row -->
	  	
	  	</div> <!-- end tab ventas -->
	  	
	  	<!-- formulario para el abono -->
	  	<div id="tabAbono" class="tab-pane fade in ">
	  		<div class="form-group row">
	  			<div class="col-xs-3">				
					<label>Pago:</label>
					<input type="number" name="abono.cantidadAbono" id="cantidadAbono" class="form-control" min="1" max="1000000">
				</div>
				<div class="col-xs-3">	
					<label>Tipo de pago:</label>			
					<select name="abono.tipoAbono.tipoAbonoId" class="form-control tipoAbono">
								<option value="0">- Seleccione -</option>
							<c:forEach items="${tipoAbonos}" var="tipo">								
								<option value="${tipo.tipoAbonoId}">${tipo.descripcion}</option>																
							</c:forEach>	
					</select>	
				</div>
					
				<div class="col-xs-3">
					<label >Fecha vencimiento:</label>
					<input type="date" name="fechaVencimientoCredito" id="fechaVencimientoCredito" class="form-control dateForm">
				</div>
				<div class="col-xs-3">				
					<span class="input-group-text">Es a credito: 
							<select name="credit" class="form-control" id="credit">
										<option value="">- Seleccione -</option>									
										<option value="0">Nota sin cr&eacute;dito</option>
										<option value="1">Nota a cr&eacute;dito</option>									
							</select>
						</span>	
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-12">				
					<label>Descripci&oacute;n:</label>
					<input type="text" name="abono.descripcion" id="descripcionAbono" class="form-control">
				</div>
	  		</div>
	  	</div> <!-- end tab abono -->
	  	
	  	</div> <!-- end tab general -->
	  	 <div class="form-group row">
			  	<div class="col-xs-12">		
			  		<input type="submit" class="btn btn-dark" name="add" value="Enviar" style="width: 100%;"/>
			  	</div>
			  </div>
	  	</form:form>
	  	
<!-- 	   <p>Agregar nota</p> -->
      </div>
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button> -->
<!--       </div> -->
    </div>
    </div><!-- end modal add -->
    
    
    
    
    
    <div id="modalUpdate" class="modal fade" role="dialog" >
 <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close closeUpdateForm" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Nota</h4>
      </div>
      <div class="modal-body">     
<form:form modelAttribute="saleNoteForm" action="handleSaleNote.do" method="post" name="updateSaleNoteForm" id="updateSaleNoteForm">
	<input type="hidden" name="saleId" class="saleId" id="saleId" value="">
	
			<div class="form-group row">			
				<div class="col-xs-8">
					<label >Descripci&oacute;n:</label>
					<input type="text" name="description" id="descriptionFormUpdate" class="form-control" disabled>
				</div>
				<div class="col-xs-4">
					<label>Total a pagar: $<span id="totalPagarUpdate"></span></label>
					<label>Pago: $<span id="totalAbonosUpdate"></span></label>
					<label>Resta: $<span id="restaUpdate"></span></label>
				</div>
			</div>
			<div class="form-group row">
			<div class="col-xs-3">
				<label>Fecha:</label>
				<input type="date" name="dateSaleNote" id="dateFormUpdate" class="form-control" disabled>
			</div>
			<div class="col-xs-3">
				<label>Cliente:</label>
				<select name="userId" class="form-control userId" disabled>
						<option value="0">- Seleccione -</option>
					<c:forEach items="${listClients}" var="client">
						<option value="${client.userId}">${client.name} ${client.firstName}</option>
					</c:forEach>	
				</select>	
			</div>	
			<div class="col-xs-3">
				<label>Vendedor:</label>
				<select name="sellerId" class="form-control sellerId" disabled>
						<option value="0">- Seleccione -</option>
					<c:forEach items="${listUsers}" var="user">
						<!-- Solo mostrar usuarios que sean vendedores -->
						<c:if test="${user.job.description eq 'vendedor' }">
							<option value="${user.userId}">${user.name} ${user.firstName}</option>
						</c:if>									
					</c:forEach>	
				</select>
			</div>
			<div class="col-xs-3">
				<label>Sucursal:</label>
				<select name="storeId" class="form-control storeId" id="storeId" disabled>
						<option value="0">- Seleccione -</option>
					<c:forEach items="${listOffices}" var="office">
						<option value="${office.officeId}">${office.name}</option>
					</c:forEach>	
				</select>
			</div>
			
		</div> <!-- end row -->
	
	<div class="form-group row">
     		<table class="table tableUpdateNote">
			    <thead>
			      <tr>
			      	<th>#</th>
			      	<th style="width:5%;">Buscar por id</th>
			        <th>Articulo</th>			        
			        <th>Color</th>
			        <th>Descripci&oacute;n</th>
			        <th>Cantidad</th>
			        <th>Precio</th>
			        <th>Total</th>
			        <th><input type="button" class="btn btn-dark" id="addRow-update" value="Agregar" /></th>			        
			      </tr>
			    </thead>
			    <tbody>
			     <tr>

			      </tr>			
			      	      	      
			    </tbody>			    
			  </table>
			   <div class="form-group row">
				  	<div class="col-xs-12">	
				  		<input type="submit" class="btn btn-dark" name="update" value="Enviar" style="width:100%;" disabled/>
					</div>
				</div>
	  	</div> <!-- end row -->
	  	</form:form>
	  	
<!-- 	   <p>Actualizar nota</p> -->
      </div>
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button> -->
<!--       </div> -->
    </div>
    </div><!-- end modal update -->
    
    
    
    <!-- modal para agregar color -->
		<div id="modalAddColor" class="modal fade" role="dialog">
			<div class="modal-content">				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Agregar color</h4>
				</div>
				<div class="modal-body">
					<form name="saleNoteForm" id="addColorForm">
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
		</div>
		<!-- end modal add color -->



<!-- 2018.08.01 - modal para cambiar estatus a la venta -->
		<div id="modalChangeStatus" class="modal fade" role="dialog">
			<div class="modal-content"
				style="width: 500px; height: 300px; margin: auto; margin-top: 30px; overflow: auto;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Cambiar estatus</h4>
				</div>
				<div class="modal-body">
					<form:form modelAttribute="saleForm" action="handleSaleNote.do" method="POST" name="changeStatusForm" id="changeStatusForm" >
						<input type="hidden" name="saleId" class="saleId" id="saleId">						
						<p>
							<span class="input-group-text">Cambiar a: 
							<select name="statusId" class="form-control" id="changeStatusSelect">
										<option value="0">- Seleccione -</option>
									<c:forEach items="${listStatus}" var="status">
										<option value="${status.statusId}">${status.description}</option>
									</c:forEach>	
							</select>
							</span>
						</p>

						<input type="submit" class="btn btn-dark" name="change" value="Enviar" />
					</form:form>

<!-- 					<p>Cambiar estatus</p> -->
				</div>
<!-- 				<div class="modal-footer"> -->
<!-- 					<button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button> -->
<!-- 				</div> -->
			</div>
		</div>
		<!-- end modal add color -->

	</div> <!-- end container -->

</body>
</html>


