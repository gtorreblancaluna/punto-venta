$( document ).ready(function() {
	
	// validando formulario
	$('form[name="addForm"]').submit(function (e) {
		return validateFormAdd();	
	});
	
	function validateFormAdd(){
		var valid=true;	    
	    var $form = $('#addForm');
	    var idAlmacen = $form.find('#selStoreFilter').val();
	    var idColor = $form.find('#selColorId').val();
	    var descripcion = $form.find('#description').val();
	    var cantEntrada = $form.find('#amountEntry').val();
	    var cantSalida = $form.find('#amountOutput').val();
	    var precioVenta = $form.find('#salePrice').val();
	    var stock = $form.find('#stock').val();
	    
	    if(idAlmacen == '0' || idColor == '0' || descripcion == '' || canEntrada == ''
	    	|| cantSalida == '' || precioVenta == '' || stock == ''
	    ){
	    	alert("Faltan valores para agregar el articulo ");
	    	valid = false
	    }
	    
	    return valid;
		
	}
	
}); // end document ready