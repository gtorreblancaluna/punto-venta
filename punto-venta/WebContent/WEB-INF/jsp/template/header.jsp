<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="javascript:void(0);">Punto Venta</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="homeStart">Home</a></li>
     
      <c:if test="${not empty userSession}">
       <li class="active"><a href="logout.do">Salir</a></li>
       
	      <li class="dropdown">
	       <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">Sistema<span class="caret"></span></a>
	       <ul class="dropdown-menu">
	       				<li ><a href="showUser.do">Usuarios</a></li>
	       				<li ><a href="showUserUpdate.do">Actualizar</a></li> 
	       				<li><a href="showAddClnt.do">Clientes</a></li>
					    <li><a href="showAddProved.do">Proveedores</a></li>
					    <li><a href="showAddInvent.do">Inventarios</a></li>
					    
	      	 </ul>
	      	</li>
	      	       
	      <li><a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">Punto Venta<span class="caret"></span></a>
	   				<ul class="dropdown-menu">
	   							<li><a> Pedidos</a></li>
	   							<li><a href="showPV.do"> Notas Venta</a></li>
	   							<li><a> Administracion</a></li>
	   							<li><a> Reportes</a></li>
	   				</ul></li>
	      
	      <li><a class="dropdown-toggle" data-toggle="dropdown">Provedores<span class="caret"></span></a>
	      <ul class="dropdown-menu">
	      
	      	<li><a>LLegadas</a></li>
	      	<li><a>Reporte diario</a></li>
	      	
	      </ul>
	      
	      </li>
      
      
      </c:if>
    </ul>
  </div>
</nav>