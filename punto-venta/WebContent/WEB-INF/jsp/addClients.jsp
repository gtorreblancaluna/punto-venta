<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
<title>Punto Venta:: Clientes</title>


	
</head>
<body >
	
<div class="container" style=" box-shadow: 0 0 30px black;" >
	<c:if test="${not empty messageView }">
		<c:out value="${messageView}"></c:out>
	</c:if>

	<form:form commandName="clientDTO" action="addClient.do" method="post" name="addClient">

				<div class="page-header">
					<div	class=".row-btsp">
						<div class="col-xs-3">
								<h1><span class="badge badge-secondary">CLIENTES</span></h1>
						</div>
						<div class="col-5">    
						</div>
						<div class="col-2">
							<span class="badge badge-secondary">Actualizar</span>
							<button type="button" class="btn btn-primary btn-sm"  data-toggle="modal" data-target="#exampleModal">
 							 Busqueda
							</button>

						</div>
					</div>
				</div>

           <div class="row">	
			
					<div class="col" >
					
						
					
						<span class="input-group-text">Nombre : 
						<input type="text" name="name" id="name" class="form-control" >
						</span>
						<br>
						<span class="input-group-text" >Calle :
							<input type="text" name="street"	id="calle" class="form-control">
						</span>
						<br>
						<span class="input-group-text">Colonia :
						<input type="text" name="colony" id="colonia" class="form-control">
						</span>
						<br>
						<span class="input-group-text">Ciudad 
							<input type="text" name="city" id="city" class="form-control">
						</span>
						
					</div>
					
					
					
					<div class="col-4">
				
						<span class="input-group-text mb-3">Apellido Paterno :
							<input type="text" name="firstName" id="apPaterno" class="form-control">
						</span>
						
						
					<!-- -->
					<!--DELEGACION INPUT	 -->
<!-- 						<span class="input-group-text">Delegacion : -->
<!-- 							<input type="text" name="delegation"  id="delegacion" class="form-control"> -->
									
									<div class="input-group-text mb-3">
										  <div class="input-group-append">
					   					 	<label class="input-group-text" for="inputGroupSelect01">Estado</label>
					 					 </div>
										  <select class="custom-select" name="state" id="inputGroupSelect02">
						    					<option selected>Selecciona</option>
						   	    				<option	 value="	1	"	>	Aguascalientes	</option>
												<option	 value="	2	"	>	Baja California	</option>
												<option	 value="	3	"	>	Baja California Sur	</option>
												<option	 value="	4	"	>	Campeche	</option>
												<option	 value="	5	"	>	Coahuila de Zaragoza	</option>
												<option	 value="	6	"	>	Colima	</option>
												<option	 value="	7	"	>	Chiapas	</option>
												<option	 value="	8	"	>	Chihuahua	</option>
												<option	 value="	9	"	>	Distrito Federal	</option>
												<option	 value="	10	"	>	Durango	</option>
												<option	 value="	11	"	>	Guanajuato	</option>
												<option	 value="	12	"	>	Guerrero	</option>
												<option	 value="	13	"	>	Hidalgo	</option>
												<option	 value="	14	"	>	Jalisco	</option>
												<option	 value="	15	"	>	México	</option>
												<option	 value="	16	"	>	Michoacán de Ocampo	</option>
												<option	 value="	17	"	>	Morelos	</option>
												<option	 value="	18	"	>	Nayarit	</option>
												<option	 value="	19	"	>	Nuevo León	</option>
												<option	 value="	20	"	>	Oaxaca	</option>
												<option	 value="	21	"	>	Puebla	</option>
												<option	 value="	22	"	>	Querétaro	</option>
												<option	 value="	23	"	>	Quintana Roo	</option>
												<option	 value="	24	"	>	San Luis Potosí	</option>
												<option	 value="	25	"	>	Sinaloa	</option>
												<option	 value="	26	"	>	Sonora	</option>
												<option	 value="	27	"	>	Tabasco	</option>
												<option	 value="	28	"	>	Tamaulipas	</option>
												<option	 value="	29	"	>	Tlaxcala	</option>
												<option	 value="	30	"	>	Veracruz de Ignacio de la Llave	</option>
												<option	 value="	31	"	>	Yucatán	</option>
												<option	 value="	32	"	>	Zacatecas	</option>	
					 					 </select>
					 					
									</div>
									
										
										<br>
					
						<span class="input-group-text"> Municipio :
								<input type="text" name="municipality" id="municipio" class="form-control">
						</span>
<!-- 						</span>	 -->
											
					</div>
					
					<div class="col-4">
						<span class="input-group-text" >Apellido Materno :
							<input type="text" name="secondName" id="apMaterno" class="form-control">
						</span>
						<br>
					
						<span class="input-group-text"> C.P.  :
								<input type="text" name="cp" id="cp" class="form-control">
						</span>
						<br>
					
						<span class="input-group-text"> Delegacion :
							<input type="text" name="delegation"	id="delegation" class="form-control">
						</span>
						
					</div>
					
			
					
		
<!-- 			<p>Calle</p>				 -->
<!-- 			<p>Colonia</p>				 -->
<!-- 			<p>Delegacion</p>			 -->
<!-- 			<p>Estado</p>				 -->
<!-- 			<p>Ciudad</p>				<input type="text" name="city"> -->
<!-- 			<p>C.P.</p>					 -->
<!-- 			<p>Telefono</p>				<input type="number" name="tel1"> -->
<!-- 			<p>Telefono 2do</p>			<input type="number" name="tel2"> -->
			
			
		  	
		  	
		  		</div>
		<button type="submit" class="btn btn-button-primary">Enviar</button>	

		
	</form:form>
		
</div>
		
</body>
</html>