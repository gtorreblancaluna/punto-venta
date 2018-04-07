<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="javascript:void(0);">Punto venta</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="javascript:void(0);">Home</a></li>
     
      <c:if test="${not empty userSession}">
       <li class="active"><a href="logout.do">Salir</a></li>
	      <li class="dropdown">
	        <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">Page 1
	        <span class="caret"></span></a>
	        <ul class="dropdown-menu">
	          <li><a href="javascript:void(0);">Page 1-1</a></li>
	          <li><a href="javascript:void(0);">Page 1-2</a></li>
	          <li><a href="javascript:void(0);">Page 1-3</a></li>
	        </ul>
	      </li>
	      <li><a href="javascript:void(0);">Page 2</a></li>
	      <li><a href="javascript:void(0);">Page 3</a></li>
      </c:if>
    </ul>
  </div>
</nav>