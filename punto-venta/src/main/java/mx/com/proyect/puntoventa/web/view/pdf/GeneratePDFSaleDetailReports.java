package mx.com.proyect.puntoventa.web.view.pdf;

/** 2018.08.16
 * @author Gerardo Torreblanca Luna
 * Controller para imprimir reporte detallado de nota venta
 * 
 **/

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

import static mx.com.proyect.puntoventa.web.commons.ApplicationConstants.MASK_DATE_FORMAT;

@Controller
public class GeneratePDFSaleDetailReports {

	@Autowired
	private SaleNoteService saleNoteService;
	private static final Locale locale = new Locale( "es", "MX" );
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat( MASK_DATE_FORMAT, locale );
	
		@GetMapping(value = "/generate_pdf_detail_sale_reports.do")
		public String printPdf(HttpServletRequest request,  HttpServletResponse response) throws IOException {
			
			Date today = new Date();
			SaleNoteFilter filtro = new SaleNoteFilter();
			// Recibiendo parametros de la vista y armando filtro
			filtro.setSaleIdFilter( request.getParameter("idFolio") != null ? request.getParameter("idFolio") : null );
			filtro.setIniDateFilter( request.getParameter("fechaInicial") != null ? request.getParameter("fechaInicial") : null );
			filtro.setEndDateFilter( request.getParameter("fechaFinal") != null ? request.getParameter("fechaFinal") : null );
			filtro.setDescriptionFilter( request.getParameter("descripcion") != null ? request.getParameter("descripcion") : null );
			filtro.setOfficeIdFilter(request.getParameter("sucursal") != null ? request.getParameter("sucursal") : null );
			filtro.setCustomerNameFilter(request.getParameter("nombreCliente") != null ? request.getParameter("nombreCliente") : null );
			filtro.setStatusFilter( request.getParameter("idEstatus") != null ? request.getParameter("idEstatus") : null );
				
			List<ResultQuerySaleNote> listSaleNoteByFilter = saleNoteService.getByFilter(filtro);
			
			for(ResultQuerySaleNote result : listSaleNoteByFilter) {
				result.setTotalAmount(saleNoteService.getTotalSaleById(result.getSaleId()));
			}
			
			
			Document document = null;	   
		    PdfWriter writer = null;
			response.setContentType( "application/pdf" );	   
		    response.setHeader("Content-Disposition","inline; filename=\"" + "reporte_venta.pdf\"");
		    OutputStream bao = response.getOutputStream(); 
		    
			try {
				document = new Document();			
				writer = PdfWriter.getInstance( document, bao );
				document.addAuthor(this.getClass().getName());
				document.addCreationDate();
				document.addProducer();
				document.addCreator(this.getClass().getName());
				document.addTitle("Reporte nota de venta");
				document.addKeywords("pdf, itext, Java");
				document.setPageSize( PageSize.LETTER.rotate());
				document.setMargins(60f, 60f, 20f, 20f);
				document.open();			
				document.newPage();	
				
				
				// Tabla Inicial para el encabezado del documento
				PdfPTable headerInfoTable = new PdfPTable(3);
				headerInfoTable.setTotalWidth(new float[]{ 160, 160, 160 });
				headerInfoTable.setLockedWidth(true);
				headerInfoTable.setWidthPercentage(100f);
			    int cellHeightInfoTable = 16;
			    int fontSizeNormal = 8; //tamaño de fuente normal
			    int fontSizeSmall = 7; // tamaño de fuenta pequeña
			    
			    
			    PdfPCell cell = new PdfPCell(new Phrase("Fecha: "+ simpleDateFormat.format( today ).replaceAll( " 0", " " ),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cell.setFixedHeight(cellHeightInfoTable);
		        cell.setColspan(3);
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        headerInfoTable.addCell(cell);	
		        
			    // Informacion del filtro
			    cell = new PdfPCell(new Phrase("ESTE REPORTE DETALLADO SE GENERO CON LOS SIGUIENTES CRITERIOS DE B\u00DASQUEDA: ",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cell.setFixedHeight(cellHeightInfoTable);
		        cell.setColspan(3);
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        headerInfoTable.addCell(cell);
		        
		        // filtro aplicado por folio
		        if(filtro.getSaleIdFilter() != null) {
			        cell = new PdfPCell(new Phrase("Folio: "+filtro.getSaleIdFilter(),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
			        cell.setFixedHeight(cellHeightInfoTable);
			        cell.setColspan(3);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        headerInfoTable.addCell(cell);	
		        }
		        
		     // filtro aplicado por fechas
		        if(filtro.getIniDateFilter() != null && filtro.getEndDateFilter() != null) {
			        cell = new PdfPCell(new Phrase("Entre fechas: "+filtro.getIniDateFilter() +" y "+filtro.getEndDateFilter(),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
			        cell.setFixedHeight(cellHeightInfoTable);
			        cell.setColspan(3);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        headerInfoTable.addCell(cell);	
		        }
		        
		     // filtro aplicado por descripcion
		        if(filtro.getDescriptionFilter() != null) {
			        cell = new PdfPCell(new Phrase("Descripci\u00F3n: "+filtro.getDescriptionFilter(),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
			        cell.setFixedHeight(cellHeightInfoTable);
			        cell.setColspan(3);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        headerInfoTable.addCell(cell);	
		        }
		        
		     // filtro aplicado por sucursal
		        if(filtro.getOfficeIdFilter() != null) {
			        cell = new PdfPCell(new Phrase("Id sucursal: "+filtro.getOfficeIdFilter(),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
			        cell.setFixedHeight(cellHeightInfoTable);
			        cell.setColspan(3);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        headerInfoTable.addCell(cell);	
		        }
		        
		     // filtro aplicado por nombre del cliente
		        if(filtro.getCustomerNameFilter() != null) {
			        cell = new PdfPCell(new Phrase("Nombre cliente: "+filtro.getCustomerNameFilter(),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
			        cell.setFixedHeight(cellHeightInfoTable);
			        cell.setColspan(3);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        headerInfoTable.addCell(cell);	
		        }
		     // filtro aplicado por estatus de la nota
		        if(filtro.getStatusFilter() != null) {
			        cell = new PdfPCell(new Phrase("Id status: "+filtro.getStatusFilter(),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
			        cell.setFixedHeight(cellHeightInfoTable);
			        cell.setColspan(3);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        headerInfoTable.addCell(cell);	
		        }
		        
		        document.add(new Phrase("\n"));  // salto de linea 		        
		        document.add(headerInfoTable);
		      
		        // salto de linea
		        document.add(new Phrase("\n"));
		        // Agregando tabla para los detalles
		        PdfPTable tableDetails = new PdfPTable(8);
		        tableDetails.setTotalWidth(new float[] {40,100,140,100,90,140,50,70});
		        tableDetails.setLockedWidth(true);
		        
		        PdfPTable tableItemsDetails = new PdfPTable(4);
		        tableItemsDetails.setTotalWidth(new float[] {40,100,100,100});
		        tableItemsDetails.setLockedWidth(true);
		        
			    int cellHeightDetails = 13;
		        
		    	// cabecera detalles		        
	 	        PdfPCell cellDetails = new PdfPCell(new Phrase("Folio",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);//		        
		        tableDetails.addCell(cellDetails);
        
		        cellDetails = new PdfPCell(new Phrase("Fecha Registro",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
     
		        cellDetails = new PdfPCell(new Phrase("Fecha Entrega",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		        
		        cellDetails = new PdfPCell(new Phrase("Descripcion",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		        
		        cellDetails = new PdfPCell(new Phrase("Sucursal",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		        
		        cellDetails = new PdfPCell(new Phrase("Cliente",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		        
		        cellDetails = new PdfPCell(new Phrase("Estatus",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		        
		        cellDetails = new PdfPCell(new Phrase("Total venta",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		        
		        float total = 0f;
		        for(ResultQuerySaleNote result : listSaleNoteByFilter) {
		        	//1
		        	cellDetails = new PdfPCell(new Phrase(result.getSaleId()+"",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		        	cellDetails.setBorder(0);
		        	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	       
		 	        tableDetails.addCell(cellDetails);
		 	        // 2
		 	        cellDetails = new PdfPCell(new Phrase(result.getSaleDate()+"",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setBorder(0);
		        	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        cellDetails.setFixedHeight(cellHeightDetails);		 	       
		 	        tableDetails.addCell(cellDetails);
		 	        // 3
		 	        cellDetails = new PdfPCell(new Phrase(result.getDateDelivery()+"",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
   		 	        cellDetails.setBorder(0);
		        	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		 	        cellDetails.setFixedHeight(cellHeightDetails);		 	      
		 	        tableDetails.addCell(cellDetails);		 	        
		 	        // 4
		 	        cellDetails = new PdfPCell(new Phrase(result.getDescription(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        cellDetails.setBorder(Rectangle.NO_BORDER);
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	        // 5
		 	        cellDetails = new PdfPCell(new Phrase(result.getNameOffice(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        tableDetails.addCell(cellDetails);
		 	        // 6
		 	        cellDetails = new PdfPCell(new Phrase(result.getNameCustomer(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        tableDetails.addCell(cellDetails);
		 	        // 7
		 	        cellDetails = new PdfPCell(new Phrase(result.getStatus().getDescription(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        tableDetails.addCell(cellDetails);	
		 	        // 8
		 	        cellDetails = new PdfPCell(new Phrase(result.getTotalAmount()+"",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        tableDetails.addCell(cellDetails);	
		 	        
		 	        
		 	        	// desplegar el detalle de cada nota		 	        
		 	           for(SaleDetailDTO detail: result.getSaleDetail()) {
		 	        	   
		 	        	   // 1
		 	        	   	cellDetails = new PdfPCell(new Phrase("",new Font(Font.ITALIC, fontSizeSmall, Font.ITALIC)));
		 	        	   	cellDetails.setBorder(0);
		 	        	   	cellDetails.setFixedHeight(cellHeightDetails);//		        
		 	        	    tableDetails.addCell(cellDetails);	
		 	        	   	// 2
		 	        	   	cellDetails = new PdfPCell(new Phrase(detail.getAmount()+"",new Font(Font.ITALIC, fontSizeSmall, Font.ITALIC)));
		 	        	   	cellDetails.setBorder(0);
		 	        	   	cellDetails.setFixedHeight(cellHeightDetails);//		        
		 	        	    tableDetails.addCell(cellDetails);	
				 	        // 3
		 	        	   cellDetails = new PdfPCell(new Phrase(detail.getItem().getDescription(),new Font(Font.ITALIC, fontSizeSmall, Font.ITALIC, new Color(80, 80, 80))));
		 	        	   cellDetails.setFixedHeight(cellHeightDetails);
		 	        	   cellDetails.setBorder(0);
		 	        	   cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        	   tableDetails.addCell(cellDetails);	
				 	        // 4
		 	        	    cellDetails = new PdfPCell(new Phrase("",new Font(Font.ITALIC, fontSizeSmall, Font.ITALIC, new Color(80, 80, 80))));
				 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        	    cellDetails.setBorder(0);
		 	        	  	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        	  	tableDetails.addCell(cellDetails);
		 	        	  	
//				 	        cellDetails = new PdfPCell(new Phrase(detail.getColor().getDescription(),new Font(Font.ITALIC, fontSizeSmall, Font.ITALIC, new Color(80, 80, 80))));
//				 	        cellDetails.setFixedHeight(cellHeightDetails);
//		 	        	    cellDetails.setBorder(0);
//		 	        	  	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
//		 	        	  	tableDetails.addCell(cellDetails);
				 	        
				 	        //5			 	        
		 	        	  	cellDetails = new PdfPCell(new Phrase(detail.getItem().getSalePrice()+"",new Font(Font.ITALIC, fontSizeSmall, Font.ITALIC, new Color(80, 80, 80))));
		 	        	  	cellDetails.setFixedHeight(cellHeightDetails);
		 	        	  	cellDetails.setBorder(0);
		 	        	  	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        	  	tableDetails.addCell(cellDetails);
		 	        	  	
		 	        	 //6			 	        
		 	        	  	cellDetails = new PdfPCell(new Phrase("",new Font(Font.ITALIC, fontSizeSmall, Font.ITALIC, new Color(80, 80, 80))));
		 	        	  	cellDetails.setFixedHeight(cellHeightDetails);
		 	        	  	cellDetails.setBorder(0);
		 	        	  	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        	  	tableDetails.addCell(cellDetails);
		 	        	  	
		 	        	 //7				 	        
		 	        	  	cellDetails = new PdfPCell(new Phrase("",new Font(Font.ITALIC, fontSizeSmall, Font.ITALIC, new Color(80, 80, 80))));
		 	        	  	cellDetails.setFixedHeight(cellHeightDetails);
		 	        	  	cellDetails.setBorder(0);
		 	        	  	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        	  	tableDetails.addCell(cellDetails);
		 	        	  	
		 	        	 //8				 	        
		 	        	  	cellDetails = new PdfPCell(new Phrase("",new Font(Font.ITALIC, fontSizeSmall, Font.ITALIC, new Color(80, 80, 80))));
		 	        	  	cellDetails.setFixedHeight(cellHeightDetails);
		 	        	  	cellDetails.setBorder(0);
		 	        	  	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        	  	tableDetails.addCell(cellDetails);
		 	        	  	
		 	        	
			 	    	   
			 	       } // end for, detail items
		 	        
		 	       total += result.getTotalAmount() == null ? 0f : result.getTotalAmount();
		        	
		        }
		        
		        // agregamos el total
		        cellDetails = new PdfPCell(new Phrase("Total: ",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL)));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	        cellDetails.setBorder(0);
	 	        cellDetails.setColspan(7);
	 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	 	        tableDetails.addCell(cellDetails);		        
		     
		        cellDetails = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(total),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        cellDetails.setBorder(0);
		        cellDetails.setColspan(1);	        
		        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		        tableDetails.addCell(cellDetails);
		        
		        document.add(tableDetails);
		        
				
				((ByteArrayOutputStream) bao).writeTo(bao);
				} catch (DocumentException e) {					
					throw new RuntimeException(e);
				} catch (IOException e) {				
					throw new RuntimeException(e);
				} finally {				
					if( document != null && document.isOpen() ) {
				        document.close();
				    }
					if( writer != null ) {
				        writer.close();
				    }
					bao.flush();
					bao.close();			
				}
				
				return null;
			
		}
		
} //end controller
