<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:useBean id="now" class="java.util.Date"/>    
<fmt:formatDate value="${now}" dateStyle="long"/>
<fmt:formatDate value="${now}" pattern="dd-MM-yyyy HH:mm:ss a z" />
<html>
<head>
<script type="text/javascript" src="js/admin/handleSaleNote.js?v1.0"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<title>Punto Venta:: Notas</title>
<script type="text/javascript">
var u_cont=0;
var g_colors="";

$( document ).ready(function() {
	
// 	alert('${accountSession}')
	if('${printSaleId}' != ''){	
		var idSaleToPrint = parseFloat('${printSaleId}');
		generatePdf(idSaleToPrint);
	}

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
			+"<td>" +
					"<select name='items["+cont+"].color.colorId' class='form-control selColors'>" +
					"<option value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listColors}' var='color'>" +
						"<option value='${color.colorId}'>${color.description}</option> " +
					"</c:forEach> " +
					"</select>" +
			"</td>"
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
			+"<td><input type='number' class='form-control' name='' id='txtFindItemById'></td>"
			+"<td>" +
					"<select name='items["+u_cont+"].itemIdForm' class='form-control selItems'>" +
					"<option value='0' data-value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listItems}' var='item'>" +
						"<option value='${item.itemId}' data-value='${item.itemId}|${item.description}|${item.salePrice}' >${item.description}</option> " +
					"</c:forEach> " +
					"</select>" +
			"</td>"
			+"<td>" +
					"<select name='items["+u_cont+"].color.colorId' class='form-control selColors'>" +
					"<option value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listColors}' var='color'>" +
						"<option value='${color.colorId}'>${color.description}</option> " +
					"</c:forEach> " +
					"</select>" +
			"</td>"
			+"<td><input type='text' class='form-control' name='items["+ u_cont +"].description' id='itemDescription' disabled></td>"
			+"<td><input type='number' class='form-control' name='items["+ u_cont +"].amountEntry' id='amountItem' ></td>"
			+"<td><input type='number' class='form-control' name='items["+ u_cont +"].salePrice' id='itemPrice'></td>"
			+"<td><input type='number' class='form-control totalItem' name='' id='totalItem' disabled></td>"
			+"<td><button type='button' class='btnDelete'>Eliminar</button></td>"
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
			+"<td><input type='number' class='form-control' name='' id='txtFindItemById'></td>"
			+"<td>" +
					"<select name='items["+u_cont+"].itemIdForm' class='form-control selItems'>" +
					"<option value='0' data-value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listItems}' var='item'>" +
						"<option value='${item.itemId}' data-value='${item.itemId}|${item.description}|${item.salePrice}' >${item.description}</option> " +
					"</c:forEach> " +					
					"</select>" +
			"</td>"
			+"<td>" +
					"<select name='items["+u_cont+"].color.colorId' class='form-control selColors'>" +
					"<option value='0'>- Seleccione -</option> " +
					"<c:forEach items='${listColors}' var='color'>" +
						"<option value='${color.colorId}'>${color.description}</option> " +
					"</c:forEach> " +
					"</select>" +
			"</td>"
			+"<td><input type='text' class='form-control' name='items["+ u_cont +"].description' id='itemDescription' disabled></td>"
			+"<td><input type='number' class='form-control' name='items["+ u_cont +"].amountEntry' id='amountItem' ></td>"
			+"<td><input type='number' class='form-control' name='items["+ u_cont +"].salePrice' id='itemPrice' ></td>"
			+"<td><input type='number' class='form-control totalItem' name='' id='totalItem' disabled></td>"
			+"<td><button type='button' class='btnDelete'>Eliminar</button></td>"
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
		$tr.find('.selColors').val(value.color.colorId);
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
  			<strong>Success!</strong> ${messageSucess}
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
						<td>${saleNote.status.description}</td>
						<td><a href="javascript:void();" onclick="javascript:generatePdf(${saleNote.saleId });">Imprimir</a></td>
					</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</c:if>
	
	
<button type="button" class="btn btn-dark" data-toggle="modal" data-target="#modalAdd">Agregar nota</button>
<div id="modalAdd" class="modal fade" role="dialog" >
 <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agregar nota</h4>
      </div>
      <div class="modal-body">     
	
	<form:form modelAttribute="saleNoteForm" action="handleSaleNote.do" method="post" name="saleNoteForm" id="addSaleNoteForm">
	<div class="info-modal-header">
		<table class="table" >
			<tr>
				<td>
				<label style="cursor:pointer;">
<%-- 					<form:checkbox path="printSaleNote" checked="checked" /> --%>
					<input type="checkbox" name="printSaleNote" checked>
					<span> Deseo imprimir nota al finalizar</span>
				</label>		
				</td>
				<td colspan=4>
					<span class="input-group-text">Descripci&oacute;n:<input type="text" name="description" id="dateForm" class="form-control dateForm"> </span>
				</td>
			</tr>
			<tr>
			
				<td>
					<span class="input-group-text">Fecha :<input type="date" name="dateSaleNote" id="dateForm" class="form-control dateForm"> </span>
				</td>
				<td>
					<span class="input-group-text">Cliente : 
							<select name="userId" class="form-control userId" >
										<option value="0">- Seleccione -</option>
									<c:forEach items="${listClients}" var="client">
										<option value="${client.userId}">${client.name} ${client.firstName}</option>
									</c:forEach>	
							</select>	
					</span>				
				</td>
				<td>
	<%-- 				<span class="input-group-text">Vendedor :<input type="text" class="form-control" value="${sessionScope.userSession.name }" disabled></span> --%>
						<span class="input-group-text">Vendedor : 
							<select name="sellerId" class="form-control sellerId" >
										<option value="0">- Seleccione -</option>
									<c:forEach items="${listUsers}" var="user">
										<!-- Solo mostrar usuarios que sean vendedores -->
										<c:if test="${user.job.description eq 'vendedor' }">
											<option value="${user.userId}">${user.name} ${user.firstName}</option>
										</c:if>									
									</c:forEach>	
							</select>	
					</span>	
				</td>
				<td>
				<span class="input-group-text">Sucursal : 
					<select name="storeId" class="form-control storeId" id="storeId">
								<option value="0">- Seleccione -</option>
							<c:forEach items="${listOffices}" var="office">
								<option value="${office.officeId}">${office.name}</option>
							</c:forEach>	
					</select>
				</span>
				
				</td>
				<td>
					<p style="font-weight: 900;  font-size: 20px;">Total a pagar: $<span id="totalPagar"></span></p>
				</td>
			</tr>
		</table>
	</div>
	
	<div class="row">
     		<table class="table tableAddNote">
			    <thead>
			      <tr>
			      	<th>#</th>
			      	<th style="width:10%;">Buscar por id</th>
			        <th>Articulo</th>			        
			        <th>Color<p><button type="button" class="btn btn-dark" data-toggle="modal" data-target="#modalAddColor">+</button></p></th>
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
			        
			         <td>
				        <select name="items[0].color.colorId" class="form-control selColors">
						<option value="0">- Seleccione -</option>
							<c:forEach items="${listColors}" var="color">
								<option value="${color.colorId}">${color.description}</option>
							</c:forEach>	
						</select>			        
			        </td>
			        <td><input type="text" class="form-control" name="items[0].description" id="itemDescription" disabled></td>
			        <td><input type="number" class="form-control" name="items[0].amountEntry" id="amountItem"></td> 					
			        <td><input type="number" class="form-control" name="items[0].salePrice" id="itemPrice" ></td>
			        <td><input type="number" class="form-control totalItem" name="" id="totalItem" disabled></td>
			        <td><input type="button" class="btn btn-dark" id="addRow-add" value="Agregar" /></td>			        
			      </tr>			
			      	      	      
			    </tbody>			    
			  </table>
			  <input type="submit" class="btn btn-dark" name="add" value="Enviar" />	
	  	</div> <!-- end row -->
	  	</form:form>
	  	
	   <p>Agregar nota</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
    </div><!-- end modal add -->
    
    
    
    
    
    <div id="modalUpdate" class="modal fade" role="dialog" >
 <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close closeUpdateForm" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Actualizar nota</h4>
      </div>
      <div class="modal-body">     
<form:form modelAttribute="saleNoteForm" action="handleSaleNote.do" method="post" name="updateSaleNoteForm" id="updateSaleNoteForm">
	<input type="hidden" name="saleId" class="saleId" id="saleId" value="">
	<table class="table tableSaleNoteForm">
		<tr>
			<td colspan=4>
				<span class="input-group-text">Descripci&oacute;n:<input type="text" name="description" id="descriptionFormUpdate" class="form-control dateForm"> </span>
			</td>
		</tr>
		<tr>
			<td>
				<span class="input-group-text">Fecha :<input type="date" name="dateSaleNote" id="dateFormUpdate" class="form-control dateForm"> </span>
			</td>
			<td>
				<span class="input-group-text">Cliente : 
						<select name="userId" class="form-control userId" >
									<option value="0">- Seleccione -</option>
								<c:forEach items="${listClients}" var="client">
									<option value="${client.userId}">${client.name} ${client.firstName}</option>
								</c:forEach>	
						</select>	
				</span>				
			</td>
			<td>
<%-- 				<span class="input-group-text">Vendedor :<input type="text" class="form-control" value="${sessionScope.userSession.name }" disabled></span> --%>
					<span class="input-group-text">Vendedor : 
						<select name="sellerId" class="form-control sellerId" >
									<option value="0">- Seleccione -</option>
								<c:forEach items="${listUsers}" var="user">
									<!-- Solo mostrar usuarios que sean vendedores -->
									<c:if test="${user.job.description eq 'vendedor' }">
										<option value="${user.userId}">${user.name} ${user.firstName}</option>
									</c:if>									
								</c:forEach>	
						</select>	
				</span>	
			</td>
			<td>
			<span class="input-group-text">Sucursal : 
				<select name="storeId" class="form-control storeId" id="storeId">
							<option value="0">- Seleccione -</option>
						<c:forEach items="${listOffices}" var="office">
							<option value="${office.officeId}">${office.name}</option>
						</c:forEach>	
				</select>
			</span>
			
			</td>
			<td>
				<p style="font-weight: 900;  font-size: 20px;">Total a pagar: $<span id="totalPagarUpdate"></span></p>
			</td>
		</tr>
	</table>
	
	<div class="row">
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
<!-- 			     	<td style="width:2%"><span class="input-group-text" >1</span></td> -->
<!-- 			     	<td > -->
<!-- 			     		<input type="number" class="form-control" name="" id="txtFindItemById" > -->
<!-- 			     	</td>			     		 -->
<!-- 			        <td > -->
			        	
<!-- 				        <select name="items[0].itemIdForm" class="form-control selItems"> -->
<!-- 						<option value="0" data-value="0">- Seleccione -</option> -->
<%-- 							<c:forEach items="${listItems}" var="item"> --%>
<%-- 								<option value="${item.itemId}" data-value="${item.itemId}|${item.description}|${item.salePrice}" >${item.description}</option> --%>
<%-- 							</c:forEach>	 --%>
<!-- 						</select>			         -->
<!-- 			        </td>			       -->
			        
<!-- 			         <td> -->
<!-- 				        <select name="items[0].color.colorId" class="form-control selColors"> -->
<!-- 						<option value="0">- Seleccione -</option> -->
<%-- 							<c:forEach items="${listColors}" var="color"> --%>
<%-- 								<option value="${color.colorId}">${color.description}</option> --%>
<%-- 							</c:forEach>	 --%>
<!-- 						</select>			         -->
<!-- 			        </td> -->
<!-- 			        <td><input type="text" class="form-control" name="items[0].description" id="itemDescription" disabled></td> -->
<!-- 			        <td><input type="number" class="form-control" name="items[0].amountEntry" id="amountItem"></td> 					 -->
<!-- 			        <td><input type="number" class="form-control" name="items[0].salePrice" id="itemPrice" disabled></td> -->
<!-- 			        <td><input type="number" class="form-control totalItem" name="" id="totalItem" disabled></td> -->
<!-- 			        <td><input type="button" class="btn btn-info btn-lg" id="addRow-update" value="Agregar" /></td>			         -->
			      </tr>			
			      	      	      
			    </tbody>			    
			  </table>
			  <input type="submit" class="btn btn-dark" name="update" value="Enviar" />	
	  	</div> <!-- end row -->
	  	</form:form>
	  	
	   <p>Actualizar nota</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
    </div><!-- end modal update -->
    
    
    
    <!-- modal para agregar color -->
		<div id="modalAddColor" class="modal fade" role="dialog">
			<div class="modal-content"
				style="width: 1000px; height: 600px; margin: auto; margin-top: 30px; overflow: auto;">
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

					<p>Agregar color</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
		<!-- end modal add color -->



	</div> <!-- end container -->

</body>
</html>


