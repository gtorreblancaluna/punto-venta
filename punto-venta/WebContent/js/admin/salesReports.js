function generatePdf(){
	var $form = $('#salesReportsFilter');
	
	// variables del filtro
	var folio = $form.find('#saleIdFilter').val();
	var fechaInicial = $form.find('#iniDateFilter').val();
	var fechaFinal = $form.find('#endDateFilter').val();
	var descripcion = $form.find('#descriptionFilter').val();
	var sucursal = $form.find('#officeIdFilter').val();
	var nombreCliente = $form.find('#customerNameFilter').val();
	var idEstatus = $form.find('#statusFilter').val();
	
	// armado de parametros a enviar
	var url = "generate_pdf_sale_reports.do";
	var parametros = "";
	var valid = false;
	var cont = 0;
	if(folio != ''){
		// si es por folio, solo se enviara el folio
		parametros += "?idFolio="+folio+"";
		valid=true;
	}else{
		if(fechaInicial != '' && fechaFinal != '' ){
			cont++;
			if(cont == 1)
				parametros += "?fechaInicial="+fechaInicial+"&fechaFinal="+fechaFinal+"";
			else
				parametros += "&fechaInicial="+fechaInicial+"&fechaFinal="+fechaFinal+"";
			valid=true;
		}
		if(descripcion != ''){
			cont++;
			if(cont == 1)
				parametros += "?descripcion="+descripcion+"";
			else
				parametros += "&descripcion="+descripcion+"";
			valid=true;
		}
		if(sucursal != '0'){
			cont++;
			if(cont == 1)
				parametros += "?sucursal="+sucursal+"";
			else
				parametros += "&sucursal="+sucursal+"";
			valid=true;
		}
		if(nombreCliente != ''){
			cont++;
			if(cont == 1)
				parametros += "?nombreCliente="+nombreCliente+"";
			else
				parametros += "&nombreCliente="+nombreCliente+"";
			valid=true;
		}
		if(idEstatus != '0'){
			cont++;
			if(cont == 1)
				parametros += "?idEstatus="+idEstatus+"";
			else
				parametros += "&idEstatus="+idEstatus+"";
			valid=true;
		}
	}
		
	if(valid)
		window.open("generate_pdf_sale_reports.do"+parametros+"", "Nota venta", "width=500,height=300");
	else
		alert('Ingresa parametros para generar el reporte PDF ')
}