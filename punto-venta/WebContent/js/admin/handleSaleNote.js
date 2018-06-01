$( document ).ready(function() {
	
		
	$(".table tbody").on('keypress', function(e){
		 var code = (e.keyCode ? e.keyCode : e.which);
	        if(code==13)
	        	event.preventDefault();  
	 });
	
	// 2018.05.22 GTL funcion para calcular el total a pagar por articulo
	$(".table tbody").on('change','#amountItem', function(){	
		var amount = $(this).val();	
		var $tr = $(this).closest('tr');		
		var price = $tr.find('#itemPrice').val();
		$tr.find('#totalItem').val(amount*price);
		total();	
	});
	
	function total(){
		var total=0;
		  $("table tbody tr").each(function () {
            stotal = $(this).find("td").eq(7).find(".totalItem").val();
            if(stotal != undefined && stotal != "")
          	  total += parseFloat(stotal);
        })
      $('#totalPagar').html(new Intl.NumberFormat('es-MX').format(total));
	}
		
	// 2018.05.22 GTL Funcion para colocar los demas valores en los inputs del articulo
	$(".table tbody").on('change','.selItems', function(){	
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
	$(".table tbody").on('click','.btnDelete', function(){
		if(confirm("\u00BFEliminar fila?"))
			$(this).closest('tr').remove();		
	});		
	document.getElementById("dateForm").valueAsDate = new Date()	
	
	// funcion para buscar por id el articulo
	$(".table tbody").on('keypress','#txtFindItemById', function(e){
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
	$("form").submit(function (e) {
		return validateForm();	
	});
	
	function validateForm(){
		 var msgError="";
		    var valid=true;
		    var count=0;
			
			var date = $('#dateForm').val();
			var userId = $('.userId').val();
			var sellerId = $('.sellerId').val();
			var storeId = $('#storeId').val();
			
			if(date === '' || userId === '0' || sellerId === '0' || storeId === '0'){
				valid=false;
				msgError += ++count + ". Faltan valores para agregar a la venta\n";
			}else{		
			// recorrremos la tabla para validar sus valores
			  $("table tbody tr").each(function () {
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
		    	valid = confirm("Confirma para continuar");
		    	
		    }
	}// end valid form
	
	
});