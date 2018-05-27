<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:useBean id="now" class="java.util.Date"/>    
<fmt:formatDate value="${now}" dateStyle="long"/>
<fmt:formatDate value="${now}" pattern="dd-MM-yyyy HH:mm:ss a z" />
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<title>Punto Venta:: Notas</title>
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
	
<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modalAdd">Agregar nota</button>
<div id="modalAdd" class="modal fade" role="dialog" >
 <div class="modal-content" style="width: 1000px; height: 600px; margin: auto; margin-top: 30px;overflow: auto;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Agregar nota</h4>
      </div>
      <div class="modal-body">     
<form:form modelAttribute="saleNoteForm" action="handleSaleNote.do" method="post" name="saleNoteForm" id="addSaleNoteForm">
	<table>
		<tr>
			<td>
				<span class="input-group-text">Fecha :<input type="date" name="dateSaleNote" id="dateForm" class="form-control dateForm"> </span>
			</td>
			<td>
				<span class="input-group-text">Cliente : 
						<select name="userId" class="form-control">
									<option value="0">- Seleccione -</option>
								<c:forEach items="${listClients}" var="client">
									<option value="${client.userId}">${client.name} ${client.firstName}</option>
								</c:forEach>	
						</select>	
				</span>				
			</td>
			<td>
<%-- 				<span class="input-group-text">Vendedor :<input type="text" class="form-control" value="${sessionScope.accountSession.name }" disabled></span> --%>
					<span class="input-group-text">Vendedor : 
						<select name="sellerId" class="form-control">
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
				<select name="storeDTO.storeId" class="form-control">
							<option value="0">- Seleccione -</option>
						<c:forEach items="${listOffices}" var="office">
							<option value="${office.officeId}">${office.name}</option>
						</c:forEach>	
				</select>
			</span>
			
			</td>
		</tr>
	</table>
	
	<div class="row">
     		<table class="table">
			    <thead>
			      <tr>
			      	<th>#</th>
			        <th>Articulo</th>			        
			        <th>Color</th>
			        <th>Descripci&oacute;n</th>
			        <th>Cantidad</th>
			        <th>Precio</th>
			        <th>Total</th>
			        <th></th>
			        <th></th>
			      </tr>
			    </thead>
			    <tbody>
			     <tr>
			     	<td style="width:2%"><span class="input-group-text" >1</span></td>			     		
			        <td>
				        <select name="items[0].itemIdForm" class="form-control selItems">
						<option value="0">- Seleccione -</option>
							<c:forEach items="${listItems}" var="item">
								<option value="${item.itemId}|${item.description}|${item.salePrice}">${item.description}</option>
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
			        <td><input type="number" class="form-control" name="items[0].salePrice" id="itemPrice" disabled></td>
			        <td><input type="number" class="form-control" name="" id="totalItem" disabled></td>
			        <td><input type="button" class="btn btn-info btn-lg" id="addRow" value="Agregar" /></td>			        
			      </tr>				      	      
			    </tbody>			    
			  </table>
			  <input type="submit" class="btn btn-primary btn-lg btn-block login-button" name="add" value="Enviar" />	
	  	</div> <!-- end row -->
	  	</form:form>
	  	
	   <p>Agregar nota</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
    </div><!-- end modal -->
	</div> <!-- end container -->
	
	
	<script type="text/javascript">


$( document ).ready(function() {
	
	// 2018.05.22 GTL funcion para calcular el total a pagar por articulo
	$(".table tbody").on('change','#amountItem', function(){	
		var amount = $(this).val();	
		var $tr = $(this).closest('tr');		
		var price = $tr.find('#itemPrice').val();
		$tr.find('#totalItem').val(amount*price);
		
	});
	
	// 2018.05.22 GTL Funcion para colocar los demas valores en los inputs del articulo
	$(".table tbody").on('change','.selItems', function(){	
		var val = $(this).val().split("|");		
		var $tr = $(this).closest('tr');		
		$tr.find('#itemDescription').val(val[1]);
		$tr.find('#itemPrice').val(val[2]);		
	});
	var cont = 0;
	
	
	$( "#addRow" ).click(function() {
		addRow();
	});
	

	// funcion para agregar una fila a la tabla
	function addRow(){	
		++cont;	
		$(".table tbody").append("<tr>"
			+"<td style='width:2%'><span class='input-group-text'>"+ (cont+1) +"</span></td>"
			+"<td><select name='items["+cont+"].itemIdForm' class='form-control selItems'><option value='0'>- Seleccione -</option><c:forEach items='${listItems}' var='item'><option value='${item.itemId}|${item.description}|${item.salePrice}'>${item.description}</option></c:forEach></select></td>"
			+"<td><select name='items["+cont+"].color.colorId' class='form-control selColors'><option value='0'>- Seleccione -</option><c:forEach items='${listColors}' var='color'><option value='${color.colorId}'>${color.description}</option></c:forEach></select></td>"
			+"<td><input type='text' class='form-control' name='items["+ cont +"].description' id='itemDescription' disabled></td>"
			+"<td><input type='number' class='form-control' name='items["+ cont +"].amountEntry' id='amountItem' ></td>"
			+"<td><input type='number' class='form-control' name='items["+ cont +"].salePrice' id='itemPrice' disabled></td>"
			+"<td><input type='number' class='form-control' name='' id='totalItem' disabled></td>"
			+"<td><button type='button' class='btnDelete'>Eliminar</button></td>"
		+"</tr>");
	};	
	
	// function para eliminar una fila de la tabla
	$(".table tbody").on('click','.btnDelete', function(){
		if(confirm("\u00BFEliminar fila?"))
			$(this).closest('tr').remove();		
	});		
	document.getElementById("dateForm").valueAsDate = new Date()		
	
});


</script>

</body>
</html>


