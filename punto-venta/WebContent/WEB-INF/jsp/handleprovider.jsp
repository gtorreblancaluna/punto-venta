<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Punto de Venta :: Provedores</title>
<script>
// codigo para la validacion de campos Input 
function Solo_Numerico(variable){
        Numer=parseInt(variable);
        if (isNaN(Numer)){
            return "";
        }
        return Numer;
    }
    function ValNumero(Control){
        Control.value=Solo_Numerico(Control.value);
    }
    
function Valida_cinco(variable){
	if(variable.lenght>0 && variable.lenght<=5){
		Num=parseInt(variable);
		return Num;
	}else{
		return ="";
		alert("verificar Codigo Postal");
	}
} 

function Valida_ci(Control2){
	Control2.value=Valida_cinco(Control2.value);
}

</script>

</head>

<body>
	<div class="container">
			
			<c:if test="${message != null}">
			<div class="alert alert-success">
				<strong>Success</strong>${messageError}
			</div>
			</c:if>
			
		<c:if test="${messageError !=null }">
		 <div class="alert alert-danger">
		 	<strong>Error! </strong> ${messagerError}
		 </div>
		</c:if>
		
		<!-- Mostramos proveedores  -->
		
	<c:if test="${not empty listProvider} }">
		<table class="table">
			<thead>
				<tr>
					<td>id</td>
					<td>Proveedor</td>
					<td>Calle</td>
					<td>Colonia</td>
					<td>Estado</td>
					<td>Municipio</td>
					<td>CP</td>
					<td>Telefono 1</td>
					<td>Telefono 2</td>
					
				</tr>
			</thead>
			<c:forEach items="${listProviders}" var="item">
				<tr>
					<td>${item.providerId}</td>
					<td>$(item.nProvider)</td>
					<td>${item.street}</td>
					<td>${item.colony}</td>
					<td>${item.state}</td>
					<td>${item.municipality}</td>
					<td>${item.cp}</td>
					<td>${item.tel1}</td>
					<td>${item.tel2}</td>
					<td><button type="button" class="btn btn-info btn-lg btnUpdate" id="btnUpdate" data-toogle="modal" data-target="#modalUpdate">Editar</button>  </td>	
					<td>
						<form:form action="handleprovider.do" method="post" name="deleteform" id="deleteform">
							<input type="hidden" name="itemId" id="deleteId" value="${item.itemId}">
							<input type="submit" class="btn btn-info btn-lg" name="delete" value="Eliminar">
							
						</form:form>
					</td>
				</tr>

			</c:forEach>
		</table>
	</c:if>
		<button type="button" class="btn btn-info btnl-lg" data-toggle="modal" data-target="#modalAdd">Agregar Proveedor</button>
	
<!-- 	Modulo despliegue agregar PRoveedor  -->
	 <div id="modalAdd" class="modal fade" role="dialog" >
 <div class="modal-content" style="width: 700px; height: 600px; margin: auto; margin-top: 30px;overflow: auto;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Proveedores</h4>
      </div>
      <div class="modal-body">     
		<form:form commandName="ItemDTO" action="handleProvider.do" method="post" name="addForm">				
				
				
				<div class="form-group">
					<label>Nombre - Proveedor</label>
					<input type="text" id="nProvider" name="nProvider" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Calle</label>
					<input type="text" id="street" name="street"  onkeyUp="return ValNumero(this);" placeholder="" class="form-control"/>	
				</div>
				<div class="form-group">
					<label>Colonia</label>
					<input type="text" id="colony" name="colony" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Estado</label>
					<input type="text" id="state" name="state" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>Municipio</label>
					<input type="text" id="municipality" name="municipality" placeholder="" class="form-control">
				</div>
				<div class="form-group">
					<label>C.P.</label>
					<input type="text" name="cp" maxlength="5" class="form-control">
				</div>
				<div class="form-group">
					<label>Telefono Primario</label>
					<input type="text" id="tel1" name="tel1" placeholder="" maxlength="10" class="form-control">
				</div>
				<div class="form-group">
					<label>Telefono Secundario</label>
					<input type="text" id="tel2" name="tel2" placeholder="" maxlength="10" class="form-control">
				</div>
				
					<div class="form-group">
					<input type="submit" class="btn btn-primary btn-lg btn-block login-button" name="add" value="Enviar" />					
				</div>
		</form:form>
		   <p>Agregar Proveedor</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
    </div>

	
	
	
	
	</div>
</body>
</html>