<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<footer class="" style="color:white;background-color: #000;padding: 25px;">
<div class="">
	<div class="row">
		
		<c:if test="${not empty userSession.account.email}">
<!-- 		Mostrar informacion del usuario -->
			<div style="">
				<div class="row">
					<div class="col-md-3 text-center">
						<label>NOMBRE: ${userSession.account.name} ${userSession.account.firstName} ${userSession.account.secondName}</label>
					</div>
					<div class="col-md-3 text-center">
						<label>EMAIL: ${userSession.account.email}</label>
					</div>
					<div class="col-md-3 text-center">
						<label>PUESTO: ${userSession.account.job.description}</label>
					</div>
					<div class="col-md-3 text-center">
						<label>SUCURSAL: ${userSession.account.office.name}</label>
					</div>
				</div>		
			</div>
		</c:if>
	</div>
</div>



</footer>