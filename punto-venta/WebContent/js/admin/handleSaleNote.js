var g_saleId;

$( document ).ready(function() {
	
	
	
	$(".tableAddNote tbody").on('keypress', function(e){
		 var code = (e.keyCode ? e.keyCode : e.which);
	        if(code==13)
	        	event.preventDefault();  
	 });
	
	// 2018.05.22 GTL funcion para calcular el total a pagar por articulo
	$(".tableAddNote tbody").on('change','#amountItem', function(){	
		var amount = $(this).val();	
		var $tr = $(this).closest('tr');		
		var price = $tr.find('#itemPrice').val();
		$tr.find('#totalItem').val(amount*price);
		total();	
	});
	
	$( '.buscarCliente' ).keyup(function(){
		var valor = $(this).val();
		if(valor != '')
			obtenerClientes(valor,1);
	});
	
	$( '#cantidadAbono' ).keyup(function(){
		var valor = $(this).val();
		if(valor != ''){
			$('#totalAbono').html(new Intl.NumberFormat('es-MX').format(valor));
			total();
		}
	});
	
	$( '.btnHabilitarFormCliente' ).click(function(){
		var $form = $('#addSaleNoteForm');		
		$form.find('#name,#apPaterno,#apMaterno,#email,#tel1,#tel2,#direccion').prop( "disabled", false );
		$form.find('#customerId').val(0);
		$form.find('#spanNombreCliente').text("");
	});
	
	// 2018.05.22 GTL funcion para calcular el total a pagar por articulo
	$(".tableAddNote tbody").on('change','#itemPrice', function(){	
		var price = $(this).val();	
		var $tr = $(this).closest('tr');		
		var amount = $tr.find('#amountItem').val();
		$tr.find('#totalItem').val(amount*price);
		total();	
	});
	
	function total(){
		var total=0;
		var $form = $('#addSaleNoteForm');
		var abono = $form.find('#cantidadAbono').val();
		  $(".tableAddNote tbody tr").each(function () {
            stotal = $(this).find("td").eq(7).find(".totalItem").val();
            if(stotal != undefined && stotal != "")
          	  total += parseFloat(stotal);
        })
      if(total >0){
	      $('#totalPagar').html(new Intl.NumberFormat('es-MX').format(total));
		  $('#total').html(new Intl.NumberFormat('es-MX').format(total-abono));
      }
	}
		
	// 2018.05.22 GTL Funcion para colocar los demas valores en los inputs del articulo
	$(".tableAddNote tbody").on('change','.selItems', function(){	
//		var val = $(this).val().split("|");		
//		var val = $(this).attr("data-item").split("|");	
		var val = $('option:selected', this).attr('data-value').split("|");
		var $tr = $(this).closest('tr');		
		$tr.find('#itemDescription').val(val[1]);
		$tr.find('#itemPrice').val(val[2]);	
		$tr.find('#amountItem').val(1);	
		var amount = $tr.find('#amountItem').val();
		$tr.find('#totalItem').val(amount*val[2]);
		total();
	});
	
	
	// funcion para eliminar una fila de la tabla
	$(".tableAddNote tbody").on('click','.btnDelete', function(){
		if(confirm("\u00BFEliminar fila?"))
			$(this).closest('tr').remove();		
	});		
//	document.getElementById("dateForm").valueAsDate = new Date()	
	
	// funcion para buscar por id el articulo
	$(".tableAddNote tbody").on('keypress','#txtFindItemById', function(e){
		 var code = (e.keyCode ? e.keyCode : e.which);
	        if(code==13){
	        	event.preventDefault(); 
	        	var $tr = $(this).closest('tr');
	            getItemById($tr);
	        }	
	});	
	
	// funcion para enviar por ajax y retornar un resultado
	function getItemById($tr){
		var data = {}
//		data["id"] = $("#txtFindItemById").val();
		var x = $tr.find("#txtFindItemById").val();
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getItemById.do",
//			data : JSON.stringify(data),
			data : x,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if(data.itemId === 0){
					alert("no se encontro articulo");
					return false;
				}
				console.log("SUCCESS: ", data);
				// colocamos el valor del articulo y llenamos los demas inputs
				$tr.find('.selItems').val(data.itemId);				
				var val = $('option:selected', $tr).attr('data-value').split("|");						
				$tr.find('#itemDescription').val(val[1]);
				$tr.find('#itemPrice').val(val[2]);
				$tr.find('#amountItem').val(1);	
				var amount = $tr.find('#amountItem').val();
				$tr.find('#totalItem').val(amount*val[2]);
				total();				
			},
			error : function(e) {
				console.log("ERROR: ", e);				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	};// end ajax
	
	// validando formulario
	$('form[name="saleNoteForm"]').submit(function (e) {
		return validateFormAddNote();	
	});
	
	// validando formulario actualizar
	$('form[name="updateSaleNoteForm"]').submit(function (e) {
		return validateFormUpdateNote();	
	});
	
	// validar formulario cambiar estatus
	$('form[name="changeStatusForm"]').submit(function (e) {
		var $form = $('#changeStatusForm');
		$form.find('input[name="saleId"]').val(g_saleId);
		return true;	
	});
	
	function validateFormAddNote(){
			var $form = $('#addSaleNoteForm');
			var msgError="",msgDate="";
		    var valid=true,fgDate=false;
		    var count=0;
		    var msgCliente = "";
			
		    var x =  $('#dateForm').val().split('-');
			var date = new Date ( x[0],(x[1] - 1),x[2] ,0,0,0) ;
			var customerId = $form.find('#customerId').val();
			var sellerId = $form.find('.sellerId').val();
//			var storeId = $('#storeId').val();
			var d = new Date();
			var today = d.getFullYear()+'/'+ (d.getMonth<10 ? '0' : '') + d.getMonth + '/' 
			+ (d.getDate()<10 ? '0' : '') + d.getDate();
			d.setHours(0,0,0,0);
			
			if(customerId == '' || customerId == '0'){
				 // no eligio cliente de la lista, vamos a validar datos usuario				
				var nombre = $form.find('#name').val();
				var apPaterno = $form.find('#apPaterno').val();
				var apMaterno = $form.find('#apMaterno').val();
				var email = $form.find('#email').val();
				var tel1 = $form.find('#tel1').val();
				var tel2 = $form.find('#tel2').val();
				
				if(nombre == '')
					msgCliente += ++count +". Nombre del cliente es requerido \n";
				if(apPaterno == '')
					msgCliente += ++count +". Apellido paterno del cliente es requerido \n";
				if(apMaterno == '')
					msgCliente += ++count +". Apellido materno del cliente es requerido \n";
				if(email == '')
					msgCliente += ++count +". Email del cliente es requerido \n";
				if(tel1 == '' && tel2 == '')
					msgCliente += ++count +". Al menos un telefono debes ingresar para el cliente \n";
			}
			
			if($('#dateForm').val() === '' || sellerId === '0' )				
				msgError += ++count + ". Faltan valores para agregar a la venta\n";					
				
			if(d.getTime() == date.getTime())					
				msgDate = 'Se descontar\u00E1n los articulos de la bd en este momento, confirma para continuar';					
				
			if(date < d)					
				msgError += ++count + ". La fecha debe ser mayor o igual a hoy\n";
				
				
				
				
			// recorrremos la tabla para validar sus valores
			  $(".tableAddNote tbody tr").each(function () {
		            var itemId = $(this).find("td").eq(2).find(".selItems").val();
		            var colorId = $(this).find("td").eq(3).find(".selColors").val();
		            var amountItem = $(this).find("td").eq(5).find("#amountItem").val();
		            var descripcion = $(this).find("td").eq(4).find("#itemDescription").val();
		            
		            if(itemId === '0' || colorId === '0' || amountItem === '')		            	
		            	msgError += ++count + ". Faltan valores para el articulo "+descripcion+" \n";
		            
		           
		      });
			    
		    
		    if(msgError!='' || msgCliente != ''){
		    	alert(msgCliente+msgError);
		    	valid = false;
		    }else{
		    	if(msgDate!=''){
		    		if(confirm(msgDate)){
		    			valid = confirm("Confirma para continuar");
		    		}else{
		    			valid = false;
		    		}
		    	}else{
		    		valid = confirm("Confirma para continuar");
		    	}
		    }
		    
		    return valid;
	}// end valid form
	
	// ----------------------------------------------- funciones para actualizar la nota
	
	
	//funcion para eliminar una fila de la tabla
	$(".tableUpdateNote tbody").on('click','.btnDelete', function(){
		if(confirm("\u00BFEliminar fila?"))
			$(this).closest('tr').remove();		
	});	
	
	
	//funcion para calcular el total a pagar por articulo
	$(".tableUpdateNote tbody").on('change','#amountItem', function(){	
		var amount = $(this).val();	
		var $tr = $(this).closest('tr');		
		var price = $tr.find('#itemPrice').val();
		$tr.find('#totalItem').val(amount*price);
		totalUpdateForm();	
	});
	
	$(".tableUpdateNote tbody").on('change','#itemPrice', function(){	
		var price = $(this).val();	
		var $tr = $(this).closest('tr');		
		var amount = $tr.find('#amountItem').val();
		$tr.find('#totalItem').val(amount*price);
		totalUpdateForm();	
	});

	//Funcion para colocar los demas valores en los inputs del articulo
	$(".tableUpdateNote tbody").on('change','.selItems', function(){	
		var val = $('option:selected', this).attr('data-value').split("|");
		var $tr = $(this).closest('tr');		
		$tr.find('#itemDescription').val(val[1]);
		$tr.find('#itemPrice').val(val[2]);	
		$tr.find('#amountItem').val(1);	
		var amount = $tr.find('#amountItem').val();
		$tr.find('#totalItem').val(amount*val[2]);
		totalUpdateForm();
	});
	
}); // end document ready


function obtenerClientes(valor,val){
	var data = {}
	var x = valor;
	if(x != ''){			
			
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "obtenerClientes.do",
			data : x,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {				
				console.log(data.clientes);
				llenarTablaClientes(data.clientes,val);
			},
			error : function(e) {
				console.log("ERROR: ", e);				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}else{
		alert("No se recibio el parametro, porfavor recarga la pagina e intentalo de nuevo :( ")
	}
}

function llenarTablaClientes(clientes,val){
	var cont = 0;
	if(val == 1)
		var $form = $('#addSaleNoteForm');
	else
		var $form = $('#updateSaleNoteForm');
	
	$form.find('.tablaClientes tbody tr td').remove();

	$.each(clientes, function(index, value) {
		$form.find(".tablaClientes tbody").append("<tr>"	
				+"<td>"+ ++cont +"</td>"
				+"<td>"+ value.userId +"</td>"
				+"<td><a href='javascript:void(0);' onclick='elegirCliente("+JSON.stringify(value)+","+val+");'>"+ value.name +"</a></td>"
				+"<td><a href='javascript:void(0);' onclick='elegirCliente("+JSON.stringify(value)+","+val+");'>"+ value.firstName +"</a></td>"
				+"<td><a href='javascript:void(0);' onclick='elegirCliente("+JSON.stringify(value)+","+val+");'>"+ value.secondName +"</a></td>"
				+"<td>"+ value.email +"</td>"
				+"<td>"+ value.tel1 +"</td>"
				+"<td>"+ value.tel2 +"</td>"
				+"<td>"+ value.adress +"</td>"
		+"</tr>");
	
	});	// end for each
}

function elegirCliente(cliente,val){
		
	if(val == 1) // viene de agregar
		var $form = $('#addSaleNoteForm');
	else // viene de actualizar
		var $form = $('#updateSaleNoteForm');
	
	$form.find('#name,#apPaterno,#apMaterno,#email,#tel1,#tel2,#direccion').prop( "disabled", true );
	
	$form.find('#customerId').val(cliente.userId);
	$form.find('#spanNombreCliente').text(cliente.name);
	$form.find('.nav-tabs li:eq(1) a').tab('show');
	
}

//2018.06.05 GTL Obtener una nota por id
function getSaleNoteById(id){
	var data = {}
	var x = id;
	if(id != ''){			
			
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getSaleNoteById.do",
			data : x,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {				
				$(".tableUpdateNote tbody").empty();
				u_cont=0;
				console.log(data)
				document.updateSaleNoteForm.saleId.value = data.noteForm.saleId;
				$('.saleId').val(data.noteForm.saleId);
				addSaleNoteForm(data.noteForm);
				addSaleDetailNoteForm(data.noteForm.saleDetail);
				$('#modalUpdate').modal('toggle');
			},
			error : function(e) {
				console.log("ERROR: ", e);				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}else{
		alert("No se recibio el parametro, porfavor recarga la pagina e intentalo de nuevo :( ")
	}
}

// agregar los datos a la ventana de actualizacion
function addSaleNoteForm(data){
	
	var $form = $('#updateSaleNoteForm');
	$form.find('.userId').val(data.userId);
	$form.find('.sellerId').val(data.sellerId);
	$form.find('.storeId').val(data.storeId);
	$form.find('#descriptionFormUpdate').val(data.description);
	document.getElementById("dateFormUpdate").valueAsDate = new Date(data.dateDelivery)	
}

function totalUpdateForm(){
	var total=0;
	  $(".tableUpdateNote tbody tr").each(function () {
        stotal = $(this).find("td").eq(7).find(".totalItem").val();
        if(stotal != undefined && stotal != "")
      	  total += parseFloat(stotal);
    })
  $('#totalPagarUpdate').html(new Intl.NumberFormat('es-MX').format(total));
}

function validateFormUpdateNote(){
	 var msgError="";
	    var valid=true;
	    var count=0;
		
	    var $tableUpdateNote = $('.tableSaleNoteForm');
	    
		var date = $tableUpdateNote.find('#dateFormUpdate').val();
		var userId = $tableUpdateNote.find('.userId').val();
		var sellerId = $tableUpdateNote.find('.sellerId').val();
		var storeId = $tableUpdateNote.find('#storeId').val();
		
		if(date === '' || userId === '0' || sellerId === '0' || storeId === '0'){
			valid=false;
			msgError += ++count + ". Faltan valores para agregar a la venta\n";
		}else{		
		// recorrremos la tabla para validar sus valores
		  $(".tableUpdateNote tbody tr").each(function () {
	            var itemId = $(this).find("td").eq(2).find(".selItems").val();
	            var colorId = $(this).find("td").eq(3).find(".selColors").val();
	            var amountItem = $(this).find("td").eq(5).find("#amountItem").val();	  
	            
	            if(itemId === '0' || colorId === '0' || amountItem === ''){
	            	valid=false;
	            	msgError += ++count + ". Faltan valores para agregar a la venta\n";
	            }
	           
	      });
		}	    
	    
	    if(!valid){
	    	alert(msgError);
	    	return valid;
	    }else{
	    	return valid = confirm("Confirma para continuar");
	    	
	    }
}// end valid form

// funcion para mandar a imprimir
//function generatePdf(id){
//    var url = "generate_pdf_sale_note.do?idOp="+id+"";
//    var type = 'Comprobante';
//    var properties = 'toolbar=0,scrollbars=1,location=0,statusbar=1,menubar=1,resizable=1,height=450,width=600,top=200,left=200';
//    var voucher = window.open( url, type, properties );
//    voucher.opener = window;
//    voucher.focus();
//};

function generatePdf(id){
	window.open("generate_pdf_sale_note.do?idOp="+id+"", "Nota venta", "width=500,height=300");
};

//2018.06.26 GTL agregar un color via AJAX
function addColor(){
	var color = $('#colorDescription').val();
	if(color != ''){		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "addColor.do",
			data : color,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {				
				alert(data.message)
				$('#modalAddColor').modal('toggle');
				appendsColorsToSelects(data.color);
				g_colors = data.colors;
				console.log("COLORS: "+g_colors)
			},
			error : function(e) {
				console.log("ERROR: ", e);	
				alert("ERROR: "+e)
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}else{
		alert("No se recibio el parametro, porfavor recarga la pagina e intentalo de nuevo :( ")
	}
}

// funcion para agregar los colores a los select despues de realizar un insert
function appendsColorsToSelects(color){
	// actualizar la lista de la tabla agregar
	var $tableAddNote = $('.tableAddNote');
	$tableAddNote.find('.selColors').append($('<option>', {
	    value: color.colorId,
	    text: color.description
	}));
	
	// actualizar la lista de la tabla actualizar
//	var $tableUpdateNote = $('.tableUpdateNote');	
//	$tableUpdateNote.find('.selColors').empty();
//	$.each(colors, function(index, value) {	
//		$tableUpdateNote.find('.selColors').append($('<option>', {
//		    value: value.colorId,
//		    text: value.description
//		}));
//	});	// end for each
}

// 2018.01.08 GTL - abrir ventana modal para cambiar estatus a la venta

function changeStatus (saleId){
	g_saleId = saleId;
	var $form = $('#changeStatusForm');
	$form.find('#saleId' ).val(saleId);
	$('#modalChangeStatus').modal('show');
}

// 2018.01.08 GTL, funcion para cambiar el estatus a la venta
function changeStatusSubmit (){
//	var $form = $('#changeStatusForm');
//	var statusId = $form.find('#changeStatusSelect option:selected' ).val();
////	var arr = { saleId: g_saleId, status: { "statusId":parseInt(statusId) } };	
//	var arr = ""
//	if(statusId != ''){		
//		$.ajax({
//			type : "POST",
//			contentType: 'application/json; charset=utf-8',
//			url : "changeStatus.do",
////			data : color,
//			data: arr,
//			dataType : 'json',
//			timeout : 100000,
//			success : function(data) {				
//				alert(data.message)				
//			},
//			error : function(e) {
//				console.log("ERROR: ", e);	
//				alert("ERROR: "+e)
//			},
//			done : function(e) {
//				console.log("DONE");
//			}
//		});
//	}else{
//		alert("No se recibio el parametro, porfavor recarga la pagina e intentalo de nuevo :( ")
//	}
	
}

function agregarColor(){
	$('#modalAddColor').modal('show');
}








