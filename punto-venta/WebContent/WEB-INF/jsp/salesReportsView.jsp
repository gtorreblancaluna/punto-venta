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
<script type="text/javascript" src="js/admin/salesReports.js?v1.1"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<title>Punto Venta:: Notas</title>
<script type="text/javascript">

$( document ).ready(function() {
	
	
});
</script>
</head>
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
				<h1>Reportes de venta</h1>
			</div>
			
		</div>
	</div>
	
	<!--Formulario para aplicar busqueda por filtro -->
	<form:form modelAttribute="saleNoteFilter" action="salesReports.do" method="post" name="salesReportsFilter" id="salesReportsFilter" >
		<div class="container-result">	
			<p>Aplicar filtro</p>
			<table class="table tableFilter bgcol">
			<tbody>
				<tr>
					<td style="width: 9%;">
						<span class="input-group-text">Folio<input type="text" name="saleIdFilter" id="saleIdFilter" class="form-control"> </span>
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
					<td>
						<div class="col-xs-3">	
							<label>Tipo de pago:</label>			
							<select name="tipoAbonoId" class="form-control tipoAbono">
										<option value="0">- Seleccione -</option>
									<c:forEach items="${tipoAbonos}" var="tipo">								
										<option value="${tipo.tipoAbonoId}">${tipo.descripcion}</option>																
									</c:forEach>	
							</select>	
						</div>
					</td>
				</tr>
				<tr>
					<td colspan=5>
						 <input type="submit" class="btn btn-dark" name="filter" value="Enviar" />	
					</td>
					<td colspan=1>
						 <input type="button" class="btn btn-dark" name="" onclick="generatePdf('generate_pdf_sale_reports.do');" value="Generar PDF" />	
					</td>
					<td colspan=1>
						 <input type="button" class="btn btn-dark" name="" onclick="generatePdf('generate_pdf_detail_sale_reports.do');" value="Generar detalle PDF" />	
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
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${listSaleNoteByFilter}" var="saleNote">
					<tr>
						<td>${saleNote.saleId }</td>
						<td>${saleNote.saleDate}</td>
						<td>${saleNote.dateDelivery}</td>
						<td>${saleNote.description}</td>
						<td>${saleNote.nameCustomer}</td>
						<td>${saleNote.nameOffice}</td>
						<td>${saleNote.nameUser}</td>
						<td>${saleNote.status.description}</td>						
					</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</c:if><!-- end listSaleNoteByFilter -->
</div> <!-- end container -->

</body>
</html>


