
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>

<script type="text/javascript">
function submit(){
	var loginForm = $('#loginForm').serialize() + '&ajax=true';
	  $.post( 'j_spring_security_check', loginForm ).always( function( response ) {
          if( response.substr( 0, 4 ) == 'url:' ) {
        	  window.location.href = "index.do"
//             window.location.href = response.substring( 4, response.length );
          } else if( response.substr( 0, 10 ) == 'error:123-' ) {
            $popup.reveal( { content : AppBundle.i18n['login.warning.maintenance'], buttons : [ { text : AppBundle.i18n['btn.label.ok'] }] } );
          } else if( response.substr( 0, 6 ) == 'error:' ) {
            
            if( response == 'error:EXEDED_TRIES' ) {
              $popup.reveal( { content : AppBundle.i18n['login.error.exceeded'], buttons : [ { text : AppBundle.i18n['btn.label.ok'] }] } );
            }else{
            	$popup.reveal( { content : AppBundle.i18n['login.error.invalid'], buttons : [ { text : AppBundle.i18n['btn.label.ok'] }] } );
            }
          }
        } );
};


</script>
<title>punto venta ::: login</title>

</head>
<body>

	<img src="https://s15.postimg.cc/ghlicj723/Logo_Muebles_Florida.png" width="550px" height="400px" class="rounded mx-auto d-block" alt="...">
	
	<div class="container">
		<c:if test="${message != null}">
			<div class="message"><c:out value="${message}"></c:out></div>
			</c:if>
<%-- 			<form:form modelAttribute="loginForm" action="loginProcess.do" method="post" name="loginForm"> --%>
		<form id="loginForm" method="POST" data-avia-form-id="1" class="loginForm avia-builder-el-2 avia-builder-el-first ">
				

			<div class="row">	
						<br>
						<div class="col-12">
							
						</div>
					<div class="col-6" >
						<label>Email: </label>
<!-- 						<input type="text" id="email" name="email" placeholder="Email" class="form-control"> -->
					<input style="width:100%;" type="text" id="usernameLogin" name="j_username"
					autocomplete="off" maxlength="255" value="" autocorrect="off" autocapitalize="off"	class="form-control" >
				
					</div>
					<div class="col-6">
<!-- 						<label>Contrase&ntilde;a: </label> -->
<!-- 						<input type="password" id="password" name="password" placeholder="Contrase&ntilde;a" class="form-control"> -->
						<input type="password" id="passwordLogin" name="j_password"
						autocomplete="off" maxlength="15" value="" autocorrect="off" autocapitalize="off" class="form-control" >
					</div>
					
					<div class="col-12">
					<br><br>
<!-- 					<button type="button" onclick="submit();" class="btn btn-primary btn-lg btn-block">Entrar</button> -->
						
					</div>
					
			</div>
			</form>
			<button type="button" onclick="submit();" class="form-control" value="">Entrar</button>
<%-- 			</form:form> --%>
			
			
		</div>
		
	
	
</body>

</html>