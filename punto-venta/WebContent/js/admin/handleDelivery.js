

$( document ).ready(function() {
	
	// funcion para eliminar una fila de la tabla
	$(".tableAddNote tbody").on('click','.btnDelete', function(){
		if(confirm("\u00BFEliminar fila?"))
			$(this).closest('tr').remove();		
		conteoFilasArticulos(1);
	});	
	
	$( '#sucursalIdElegir' ).change(function() {
		var sucursalId = $( "#sucursalIdElegir option:selected" ).val();
		$('.tablaArticulos tbody tr td').remove();
		traerAlamacenesPorSucursal(sucursalId);
		
	});


	//buscar articulos via AJAX
	$( '#filtroDescripcionArticulo' ).keyup(function(){
		var valor = $(this).val();
//		var almacenId = $( "#storeIdFilter option:selected" ).val();
//		var sucursalId = $( "#sucursalIdElegir option:selected" ).val();
		if(valor != ''){
			// traemos todos los articulos sin aplicar filtro
			valor += "-"+0+"-"+0;
			filtroArticulos(valor,1);
		}
	});
		
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
	
	function total(){
		var total=0;
		  $(".tableAddNote tbody tr").each(function () {
            stotal = $(this).find("td").eq(7).find(".totalItem").val();
            if(stotal != undefined && stotal != "")
          	  total += parseFloat(stotal);
        })
      $('#totalPagar').html(new Intl.NumberFormat('es-MX').format(total));
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
	$('form[name="deliveryForm"]').submit(function (e) {
		if(validateFormAddNote()){
			$('.articuloId').prop( "disabled", false );
			return true;
		}
		else
			return false
	});
	
	// validando formulario actualizar
	$('form[name="updateSaleNoteForm"]').submit(function (e) {
		if(validateFormUpdateNote()){
			$('.articuloId').prop( "disabled", false );
			return true;
		}
		else
			return false
	});
	
	function validateFormAddNote(){
		 var msgError="";
		 var totalArticulos=0;		 
		    var count=0;
			var proveedorId = $('#proveedorId').val();
			var sucursalId = $('#sucursalIdElegir').val();
			var almacenId = $('#storeIdFilter').val()

			if(proveedorId == '')
				msgError += ++count +'. Falta agregar el proveedor\n';
			if(sucursalId == '')
				msgError += ++count +'. Falta agregar sucursal\n';
			if(almacenId == '')
				msgError += ++count +'. Falta agregar almacen\n';
				
			// recorrremos la tabla para validar sus valores
			  $(".tableAddNote tbody tr").each(function () {
				  	totalArticulos++;
//		            var itemId = $(this).find("td").eq(2).find(".selItems").val();
//		            var colorId = $(this).find("td").eq(3).find(".selColors").val();
		            var amountItem = $(this).find("td").eq(3).find("#amountItem").val();	  
		            
		            if(amountItem == '')  
		            	msgError += ++count +'. Ingresa una cantidad para el articulo '+$(this).find("td").eq(2).find("#itemDescription").val();+'\n';
		            if(amountItem <= 0 || amountItem > 999 )
		            	msgError += ++count +'. La cantidad para el articulo '+$(this).find("td").eq(2).find("#itemDescription").val() +' es incorrecta\n';
		      });
			  
			  if(totalArticulos <= 0)
				  msgError += ++count +'. Ingresa al menos un articulo para continuar\n';    
		    
		    if(msgError != ''){
		    	alert(msgError);
		    	return false;
		    }else	    	
		    	return confirm("Confirma para continuar");
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
				$('.saleId').val(data.saleId);
				addSaleNoteForm(data);
				addSaleDetailNoteForm(data.saleDetail);
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
	
	var $tableSaleNoteForm = $('.tableSaleNoteForm');
	$tableSaleNoteForm.find('.userId').val(data.userId);
	$tableSaleNoteForm.find('.sellerId').val(data.sellerId);
	$tableSaleNoteForm.find('.storeId').val(data.storeId);
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
function generatePdf(id){
    var url = "generate_pdf_delivery.do?idOp="+id+"";
    var type = 'Comprobante';
    var properties = 'toolbar=0,scrollbars=1,location=0,statusbar=1,menubar=1,resizable=1,height=450,width=600,top=200,left=200';
    var voucher = window.open( url, type, properties );
    voucher.opener = window;
    voucher.focus();
};


function filtroArticulos(valor,form){
	
	var data = {};
	if(valor != ''){		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "buscarArticulosPorDescripcion.do",
			data : valor,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {				
				// exito, llenamos la tabla de clientes
				console.log(data.articulos);
				if(data.articulos.length > 0)
					llenarTablaArticulos(data.articulos,form);
				else{
					$('.tablaArticulos tbody tr td').remove();
					$(".tablaArticulos tbody").append("<tr>"	
							+"<td colspan=3>No encontre coincidencias segun el criterio aplicado</td>"							
					+"</tr>");
					}	
				
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
		alert("No se recibio el parametro, porfavor recarga la pagina e intentalo de nuevo :( ");
	}
}

function traerAlamacenesPorSucursal(sucursalId){	
	if(sucursalId != ''){		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "obtenerAlmacenesPorSucursal.do",
			data : sucursalId+"",
			dataType : 'json',
			timeout : 100000,
			success : function(data) {						
				console.log(data.almacenes)
				llenarComboAlmacenes(data.almacenes);
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

function llenarTablaArticulos(articulos,form){
	var cont = 0;
	$('.tablaArticulos tbody tr td').remove();
	$.each(articulos, function(index, articulo) {
		$(".tablaArticulos tbody").append("<tr>"	
				+"<td>"+ ++cont +"</td>"
				+"<td>"+ articulo.itemId +"</td>"
				+"<td><a href='javascript:void(0);' onclick='elegirArticulo("+JSON.stringify(articulo)+","+form+");'>"+ articulo.description +"</a></td>"
		+"</tr>");
	
	});	// end for each
}

function elegirArticulo(articulo,form){
	if(verificarArticuloExistente(articulo.itemId)){
		$('#modalElegirArticulo').modal('hide');	
		var descripcion = articulo.description;
		var precio = parseFloat(articulo.salePrice);	
			$(".tableAddNote tbody").append("<tr>"	
					+"<td><span class='consecutivo'></span></td>"
					+"<td style='width: 7%;'><input type='text' class='form-control articuloId' name='details["+u_cont+"].item.itemIdForm' value="+articulo.itemId+" disabled></td>"
					+"<td style='width: 58%;'><input type='text' class='form-control itemDescription' name='details["+ u_cont +"].item.description' value="+descripcion+" id='itemDescription' disabled></td>"
					+"<td><input type='number' class='form-control amountItem' name='details["+ u_cont +"].item.amountEntry' value='"+(1)+"' id='amountItem'></td>"
	//				+"<td><input type='number' class='form-control itemPrice' name='items["+ u_cont +"].salePrice' value="+(precio*1)+" id='itemPrice'></td>"
	//				+"<td><input type='number' class='form-control totalItem' name='' id='totalItem' value="+(precio*1)+" disabled></td>"
					+"<td><button type='button' class='btnDelete'>Eliminar</button></td>"
			+"</tr>");
			u_cont++;
	//		total();	
			conteoFilasArticulos(form);
	}
	
}

function verificarArticuloExistente(articuloId){
	// recorrremos la tabla para validar sus valores
	var valid = true;
	  $(".tableAddNote tbody tr").each(function () {
          var itemId = $(this).find("td").eq(1).find(".articuloId").val();          
          if(itemId == articuloId) {
        	  valid = false;
          	alert('El articulo que deseas agregar, ya se encuentra en la tabla ');
          }
    });
	  return valid;
}

function conteoFilasArticulos(valor){
	var total=0;
	
	if(valor == '1'){
	  $(".tableAddNote tbody tr").each(function () {
		  
		var articuloId = $(this).find('td').eq(1).find(".articuloId").attr('name');
		$(this).find('td').eq(1).find(".articuloId").attr('name',articuloId.replace(/[^a-zA-Z_\W]+/g, total));
		  
		var itemDescription = $(this).find('td').eq(2).find(".itemDescription").attr('name');
		$(this).find('td').eq(2).find(".itemDescription").attr('name',itemDescription.replace(/[^a-zA-Z_\W]+/g, total));
		
		var amountItem = $(this).find('td').eq(3).find(".amountItem").attr('name');
		$(this).find('td').eq(3).find(".amountItem").attr('name',amountItem.replace(/[^a-zA-Z_\W]+/g, total));
		
//		var itemPrice = $(this).find('td').eq(4).find(".itemPrice").attr('name');
//		$(this).find('td').eq(4).find(".itemPrice").attr('name',itemPrice.replace(/[^a-zA-Z_\W]+/g, total));			
//	
		
		$(this).find("td").eq(0).find(".consecutivo").text(++total);
	  });
//	  $('#totalArticulos').text(total);
	}else{
		$(".tablaUpdateVentaArticulos tbody tr").each(function () {
			
			var articuloId = $(this).find('td').eq(1).find(".articuloId").attr('name');
			$(this).find('td').eq(1).find(".articuloId").attr('name',articuloId.replace(/[^a-zA-Z_\W]+/g, total));
			  
			var itemDescription = $(this).find('td').eq(2).find(".itemDescription").attr('name');
			$(this).find('td').eq(2).find(".itemDescription").attr('name',itemDescription.replace(/[^a-zA-Z_\W]+/g, total));
			
			var amountItem = $(this).find('td').eq(3).find(".amountItem").attr('name');
			$(this).find('td').eq(3).find(".amountItem").attr('name',amountItem.replace(/[^a-zA-Z_\W]+/g, total));
			
			var itemPrice = $(this).find('td').eq(4).find(".itemPrice").attr('name');
			$(this).find('td').eq(4).find(".itemPrice").attr('name',itemPrice.replace(/[^a-zA-Z_\W]+/g, total));			
		
			
			$(this).find("td").eq(0).find(".consecutivo").text(++total);
		});
	  $('#totalArticulosUpdate').text(total);
	}
  
	  
}


function llenarComboAlmacenes(almacenes){	
	
	$('#storeIdFilter')
    .find('option')
    .remove()
    .end()
    .append('<option value="0">- Seleccione -</option>')
    .val('');
	// llenamos el combo del filtro
	$.each(almacenes, function (i, almacen) {
	    $('#storeIdFilter').append($('<option>', { 
	        value: almacen.storeId,
	        text : almacen.description 
	    }));
	});	
}

function buscarArticulo(){
	$('#modalElegirArticulo').modal('show');
};







