package mx.com.proyect.puntoventa.web.view.pdf;

/** 2018.08.01
 * @author Gerardo Torreblanca Luna
 * Controller para imprimir reporte de nota venta
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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
import mx.com.proyect.puntoventa.web.model.SaleNoteDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

@Controller
public class GeneratePDFSaleReports {

	@Autowired
	private SaleNoteService saleNoteService;
	
		@RequestMapping(value = "/generate_pdf_sale_reports.do", method = RequestMethod.GET)
		public String printPdf(HttpServletRequest request,  HttpServletResponse response) throws IOException {
			SaleNoteFilter filtro = new SaleNoteFilter();
			// Recibiendo parametros de la vista y armando filtro
			filtro.setSaleIdFilter( request.getParameter("folio") != null ? request.getParameter("folio") : null );
			filtro.setIniDateFilter( request.getParameter("fechaInicial") != null ? request.getParameter("fechaInicial") : null );
			filtro.setEndDateFilter( request.getParameter("fechaFinal") != null ? request.getParameter("fechaFinal") : null );
			filtro.setDescriptionFilter( request.getParameter("descripcion") != null ? request.getParameter("descripcion") : null );
			filtro.setOfficeIdFilter(request.getParameter("sucursal") != null ? request.getParameter("sucursal") : null );
			filtro.setCustomerNameFilter(request.getParameter("nombreCliente") != null ? request.getParameter("nombreCliente") : null );
			filtro.setStatusFilter( request.getParameter("estatus") != null ? request.getParameter("estatus") : null );
				
			List<ResultQuerySaleNote> listSaleNoteByFilter = saleNoteService.getByFilter(filtro);
			
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
				
				
				// Informacion de la empresa
				PdfPTable headerInfoTable = new PdfPTable(3);
				headerInfoTable.setTotalWidth(new float[]{ 160, 160, 160 });
				headerInfoTable.setLockedWidth(true);
				headerInfoTable.setWidthPercentage(100f);
			    int cellHeightInfoTable = 16;
			    int fontSizeNormal = 9; //tamaño de fuente normal
			    int fontSizeSmall = 8; // tamaño de fuenta pequeña
			    
			    PdfPCell cell = new PdfPCell(new Phrase("",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cell.setFixedHeight(cellHeightInfoTable);
		        cell.setColspan(2);
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        headerInfoTable.addCell(cell);		        
		        document.add(new Phrase("\n"));  // salto de linea 
		        		        
				PdfPTable table = new PdfPTable(3);
			
				table.setTotalWidth(new float[]{ 200, 160, 160 });
			    table.setLockedWidth(true);
			    int cellHeight = 14;
			    int cellHi=13;				   		
	//	    
		        document.add(table);
		      
		        // salto de linea
		        document.add(new Phrase("\n"));
		        // Agregando tabla para los detalles
		        PdfPTable tableDetails = new PdfPTable(7);
		        tableDetails.setTotalWidth(new float[] {50,110,110,160,100,150,100});
		        tableDetails.setLockedWidth(true);
		        
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
		        
		    
		        for(ResultQuerySaleNote result : listSaleNoteByFilter) {		       
		        	
		        	cellDetails = new PdfPCell(new Phrase(result.getSaleId()+"",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		        	cellDetails.setBorder(0);
		        	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	       
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	        cellDetails = new PdfPCell(new Phrase(result.getSaleDate()+"",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setBorder(0);
		        	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        cellDetails.setFixedHeight(cellHeightDetails);		 	       
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	        cellDetails = new PdfPCell(new Phrase(result.getDateDelivery()+"",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
   		 	        cellDetails.setBorder(0);
		        	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	      
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	      
		 	        cellDetails = new PdfPCell(new Phrase(result.getDescription(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        cellDetails.setBorder(Rectangle.NO_BORDER);
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	        
		 	        cellDetails = new PdfPCell(new Phrase(result.getNameOffice(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	        cellDetails = new PdfPCell(new Phrase(result.getNameCustomer(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	        cellDetails = new PdfPCell(new Phrase(result.getStatus().getDescription(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        tableDetails.addCell(cellDetails);		 	        
		        	
		        }
		        
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
