<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="javascript:void(0);">Punto Venta</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="javascript:void(0);">Home</a></li>
     
      <c:if test="${not empty userSession}">
       <li class="active"><a href="logout.do">Salir</a></li>
       <!-- 	      	--------------------------------------------------------------------------------------------v -->
       
	      <li class="dropdown"><!-- menu completo  -->
	       <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">Sistema<span class="caret"></span></a>
	       <ul class="dropdown-menu"><!-- baja menu a partir de aqui  -->
<!-- 	       			<li class="dropdown-menu" ><a href="showUser.do">Administrar--2</a></li> -->
	       				<li ><a href="showUser.do">Usuarios</a></li>
	       				<li ><a href="showUserUpdate.do">Perfiles Usuarios</a></li> 
<!-- 	          			aqui para meter el menu para administracion  -->
					    <li><a href="javascript:void(0);">Reporte</a></li>
<!-- 	          <li><a href="javascript:void(0);">Reportes</a></li> -->
	      	 </ul>
	      	</li>
<!-- 	      	--------------------------------------------------------------------------------------------v -->
	      <li><a href="javascript:void(0);">Page 2</a></li>
	      <li><a href="javascript:void(0);">Page 3</a></li>
      </c:if>
    </ul>
  </div>
</nav>