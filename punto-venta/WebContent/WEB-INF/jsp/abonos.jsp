<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punto de venta :: abonos</title>
<style>		    

</style>
<link rel="stylesheet" type="text/css" href="css/data-tables.css">
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
				<h1>Reporte de abonos</h1>
			</div>			
		</div>
	</div>
	
		<!--2018.08.25 Formulario para aplicar busqueda por filtro -->
	<form:form modelAttribute="filtroAbonos" action="reporteAbonos.do" method="post" name="reporteAbonos" id="reporteAbonos" >
		<div class="container-result">	
			<p>Aplicar filtro</p>
			<table class="table tableFilter bgcol">
			<tbody>
				<tr>
					<td>
						<span class="input-group-text">Nombre cliente:<input type="text" name="nombreCliente" id="nombreCliente" class="form-control"> </span>
					</td>									
					<td>
						<span class="input-group-text">Fecha inicial:<input type="date" name="fechaInicial" id="fechaInicial" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Fecha final:<input type="date" name="fechaFinal" id="fechaFinal" class="form-control"> </span>
					</td>
					<td>
						<span class="input-group-text">Tipo de pago: 
							<select name="tipoAbonoId" class="form-control" id="tipoAbonoId">
										<option value="0">- Seleccione -</option>
									<c:forEach items="${tipoAbonos}" var="tipo">
										<option value="${tipo.tipoAbonoId}">${tipo.descripcion}</option>
									</c:forEach>	
							</select>
						</span>
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
				</tr>
				<tr>
					<td colspan=1>
					 <input type="submit" class="btn btn-dark" name="filter" value="Enviar" />	
					</td>				
					<td colspan=2>			
						 <input type="button" class="btn btn-dark" name="" onclick="generatePdf('reporte_abonos_pdf.do');" value="Generar PDF" />	
					</td>
				</tr>
			</tbody>
			</table>	
		</div>
	</form:form>
	
	
		<!-- abonos -->
		<c:if test="${not empty abonos}">
		<div class="containerShowResultQuery container-result">
		<table class="table tableShowResultQuery ">
		<thead>
			<tr>
				<th>id</th>
				<th>Descripci&oacute;n nota</th>
				<th>Cantidad</th>
				<th>Tipo de pago</th>
				<th>Descripci&oacute;n pago</th>
				<th>Nombre cliente</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${abonos}" var="abono">		
		 		<tr>
		 			<td>${abono.abonoId}</td>
		 			<td>${abono.saleNote.description}</td>
		 			<td><fmt:formatNumber value="${abono.cantidadAbono}" type="currency" currencySymbol=""/></td>
		 			<td>${abono.tipoAbono.descripcion}</td>
		 			<td>${abono.descripcion}</td>
		 			<td>${abono.cliente.name} ${abono.cliente.firstName}</td>
		 		</tr>	 	
	 		</c:forEach>
	 	</tbody>
	 		</table>
	 		</div>
		</c:if>


    </div>
    
<script type="text/javascript" src="js/data-tables.js"></script>    
<script type="text/javascript">
$( document ).ready(function() {
	$('.tableShowResultQuery').DataTable();
	$("#statusFilter").val("1").change();
	 	
}); // end document ready

function generatePdf(url){
	var $form = $('#reporteAbonos');
	var fechaInicial = $form.find('#fechaInicial').val();
	var fechaFinal = $form.find('#fechaFinal').val();
	var tipoAbonoId = $form.find('#tipoAbonoId').val();
	var idEstatus = $form.find('#statusFilter').val();
	var msgError = '';
	
	var parametros='';
	var valid=true;
	var cont=0;
	var x = 0;
	
	if(fechaInicial == '' || fechaFinal == '' )
		msgError += ++cont + '. Ingresa fecha inicial y final\n'
		
	if(fechaInicial != '' && fechaFinal != '' ){
		x ++;
		if(x == 1)
			parametros += "?fechaInicial="+fechaInicial+"&fechaFinal="+fechaFinal+"";
		else
			parametros += "&fechaInicial="+fechaInicial+"&fechaFinal="+fechaFinal+"";
	}
	if(tipoAbonoId != 0){
		x ++;
		if(x == 1)
			parametros += "?tipoAbonoId="+tipoAbonoId+"";
		else
			parametros += "&tipoAbonoId="+tipoAbonoId+"";
	}else{
		msgError += ++cont + '. Ingresa tipo de abono\n'
	}
	
	if(idEstatus != '0'){
		x++;
		if(x == 1)
			parametros += "?idEstatus="+idEstatus+"";
		else
			parametros += "&idEstatus="+idEstatus+"";
		valid=true;
	}
	
	if(msgError != ''){
		valid = false;
		alert(msgError);
	}else{
		window.open(url+parametros+"", "Reporte abonos", "width=500,height=300");
	}

		
		
}
</script>

</body>
</html>