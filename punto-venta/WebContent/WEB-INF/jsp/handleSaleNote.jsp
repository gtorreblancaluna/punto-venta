<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	<title>Punto Venta:: Notas</title>
			
		
</head>
<body>
<br>
<br>
<body>

<div class="container " style=" box-shadow: 0 0 30px black;">
	<div class="page-header">
		<div class="row">
			<div class="col">
				<h1>Venta-dia</h1>
			</div>
			
		</div>
	</div>
	
	<!-- inicio de Body despues de header de venta-dia -->
	<div class="row">
		<div class="col-4">
		 	<span class="input-group-text">Fecha :<input type="date" class="form-control"> </span>
			<br>
		<span class="input-group-text">Cliente :<input type="text" class="form-control"><button>?</button></span>	
			<br>
		<span class="input-group-text">Vendedor :<input type="text" class="form-control"></span>
		</div>
		<div class="col-6">
			
		</div>
		<div class="col-2">
			<span class="input-group-text" >Folio <input type="number" class="form-control" disabled="disabled"></span>
		</div>
		
	</div>
	<div class="row">
		      
  			<table class="table">
			    <thead>
			      <tr>
			      	<th>#</th>
			        <th>Articulo</th>
			        <th>Descripcion</th>
			        <th>Cantidad</th>
			        <th>Precio</th>
			        <th>Total</th>
			      </tr>
			    </thead>
			    <tbody>
			     <tr>
			     	<td style="width:2%"><span class="input-group-text" >1</span></td>
			        <td><input type="number" class="form-control" name="articulo"></td>
			        <td><input type="text" class="form-control" name="Descripcion"></td>
			        <td><input type="number" class="form-control" name="Cantidad"></td>
			        <td><input type="number" class="form-control" name="Precio"></td>
			        <td><input type="number" class="form-control" name="Total"></td>
			      </tr>
			       <tr>
			       <td style="width:2%"><span class="input-group-text" >2</span></td>
			        <td><input type="number" class="form-control" name="articulo"></td>
			        <td><input type="text" class="form-control" name="Descripcion"></td>
			        <td><input type="number" class="form-control" name="Cantidad"></td>
			        <td><input type="number" class="form-control" name="Precio"></td>
			        <td><input type="number" class="form-control" name="Total"></td>
			      </tr>
			       <tr>
			       <td style="width:2%"><span class="input-group-text" >3</span></td>
			        <td><input type="number" class="form-control" name="articulo"></td>
			        <td><input type="text" class="form-control" name="Descripcion"></td>
			        <td><input type="number" class="form-control" name="Cantidad"></td>
			        <td><input type="number" class="form-control" name="Precio"></td>
			        <td><input type="number" class="form-control" name="Total"></td>
			      </tr>
			       <tr>
			       <td style="width:2%"><span class="input-group-text" >4</span></td>
			        <td><input type="number" class="form-control" name="articulo"></td>
			        <td><input type="text" class="form-control" name="Descripcion"></td>
			        <td><input type="number" class="form-control" name="Cantidad"></td>
			        <td><input type="number" class="form-control" name="Precio"></td>
			        <td><input type="number" class="form-control" name="Total"></td>
			      </tr>
			       <tr>
			       <td style="width:2%"><span class="input-group-text" >5</span></td>
			        <td><input type="number" class="form-control" name="articulo"></td>
			        <td><input type="text"   class="form-control" name="Descripcion"></td>
			        <td><input type="number" class="form-control" name="Cantidad"></td>
			        <td><input type="number" class="form-control" name="Precio"></td>
			        <td><input type="number" class="form-control" name="Total"></td>
			      </tr>
			       <tr>
			       <td style="width:2%"><span class="input-group-text" >6</span></td>
			        <td><input type="number" class="form-control" name="articulo"></td>
			        <td><input type="text"   class="form-control" name="Descripcion"></td>
			        <td><input type="number" class="form-control" name="Cantidad"></td>
			        <td><input type="number" class="form-control" name="Precio"></td>
			        <td><input type="number" class="form-control" name="Total"></td>
			      </tr>
			       <tr>
			       <td style="width:2%"><span class="input-group-text" >7</span></td>
			        <td><input type="number" class="form-control" name="articulo"></td>
			        <td><input type="text" class="form-control" name="Descripcion"></td>
			        <td><input type="number" class="form-control" name="Cantidad"></td>
			        <td><input type="number" class="form-control" name="Precio"></td>
			        <td><input type="number" class="form-control" name="Total"></td>
			      </tr>
			       <tr>
			       <td style="width:2%"><span class="input-group-text" >8</span></td>
			        <td><input type="number" class="form-control" name="articulo"></td>
			        <td><input type="text" class="form-control" name="Descripcion"></td>
			        <td><input type="number" class="form-control" name="Cantidad"></td>
			        <td><input type="number" class="form-control" name="Precio"></td>
			        <td><input type="number" class="form-control" name="Total"></td>
			      </tr>
			      
			    </tbody>
			  </table>
			
		</div>
	</div>

	
	
	




</body>
</body>
</html>


