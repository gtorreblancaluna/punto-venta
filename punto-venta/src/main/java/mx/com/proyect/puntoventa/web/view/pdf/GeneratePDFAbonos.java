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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.proyect.puntoventa.web.forms.FiltroAbonos;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.AbonoDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

import static mx.com.proyect.puntoventa.web.commons.ApplicationConstants.MASK_DATE_FORMAT;

@Controller
public class GeneratePDFAbonos {

	@Autowired
	private SaleNoteService saleNoteService;
	private static final Locale locale = new Locale( "es", "MX" );
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat( MASK_DATE_FORMAT, locale );
	
		@RequestMapping(value = "/reporte_abonos_pdf.do", method = RequestMethod.GET)
		public String printPdf(HttpServletRequest request,  HttpServletResponse response) throws IOException {
			
			Date today = new Date();
			FiltroAbonos filtro = new FiltroAbonos();
			// Recibiendo parametros de la vista y armando filtro
			filtro.setFechaInicial( request.getParameter("fechaInicial") != null ? request.getParameter("fechaInicial") : null );
			filtro.setFechaFinal( request.getParameter("fechaFinal") != null ? request.getParameter("fechaFinal") : null );
			filtro.setTipoAbonoId( request.getParameter("tipoAbonoId") != null ? new Integer(request.getParameter("tipoAbonoId")) : null );
			filtro.setNombreCliente( request.getParameter("nombreCliente") != null ? request.getParameter("nombreCliente") : null );
			filtro.setStatusFilter( request.getParameter("idEstatus") != null ? request.getParameter("idEstatus") : null );
			
			List<AbonoDTO> abonos = saleNoteService.obtenerAbonosPorFiltro(filtro);					
			
			Document document = null;
		    PdfWriter writer = null;
			response.setContentType( "application/pdf" );	   
		    response.setHeader("Content-Disposition","inline; filename=\"" + "reporte_abonos.pdf\"");
		    OutputStream bao = response.getOutputStream(); 
		    
			try {
				document = new Document();			
				writer = PdfWriter.getInstance( document, bao );
				document.addAuthor(this.getClass().getName());
				document.addCreationDate();
				document.addProducer();
				document.addCreator(this.getClass().getName());
				document.addTitle("Reporte abonos");
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
			    cell = new PdfPCell(new Phrase("ESTE REPORTE SE GENERO CON LOS SIGUIENTES CRITERIOS DE B\u00DASQUEDA: ",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cell.setFixedHeight(cellHeightInfoTable);
		        cell.setColspan(3);
		        cell.setBorder(Rectangle.NO_BORDER);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        headerInfoTable.addCell(cell);
		        
		      
		     // filtro aplicado por fechas
		        if(filtro.getFechaInicial() != null && filtro.getFechaFinal() != null) {
			        cell = new PdfPCell(new Phrase("Entre fechas: "+filtro.getFechaInicial() +" y "+filtro.getFechaFinal(),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
			        cell.setFixedHeight(cellHeightInfoTable);
			        cell.setColspan(3);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        headerInfoTable.addCell(cell);	
		        }
		        
		     // filtro aplicado por descripcion
		        if(filtro.getTipoAbonoId() != 0) {
		        	String tipoAbono = abonos != null ? abonos.get(0).getTipoAbono().getDescripcion().toUpperCase() : "";
//		        	cell = new PdfPCell(new Phrase("Tipo de bono: "+filtro.getTipoAbonoId(),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        	cell = new PdfPCell(new Phrase("Tipo de pago: "+tipoAbono,new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
			        cell.setFixedHeight(cellHeightInfoTable);
			        cell.setColspan(3);
			        cell.setBorder(Rectangle.NO_BORDER);
			        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        headerInfoTable.addCell(cell);	
		        }	
		     // filtro aplicado por tipo de estado de la venta
		        if(filtro.getStatusFilter() != null && !filtro.getStatusFilter().equals("")) {		
		        	cell = new PdfPCell(new Phrase("Estado de la venta es: "+filtro.getStatusFilter(),new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
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
		        PdfPTable tableDetails = new PdfPTable(6);
		        tableDetails.setTotalWidth(new float[] {40,100,40,100,140,140});
		        tableDetails.setLockedWidth(true);
		        
			    int cellHeightDetails = 13;
		        
		    	// cabecera detalles		        
	 	        PdfPCell cellDetails = new PdfPCell(new Phrase("Id",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);//		        
		        tableDetails.addCell(cellDetails);
        
		        cellDetails = new PdfPCell(new Phrase("Descripci\u00E9n nota",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
     
		        cellDetails = new PdfPCell(new Phrase("Cantidad",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		        
		        cellDetails = new PdfPCell(new Phrase("Tipo de pago",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		        
		        cellDetails = new PdfPCell(new Phrase("Nota de pago",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		        
		        cellDetails = new PdfPCell(new Phrase("Cliente",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
		        cellDetails.setFixedHeight(cellHeightDetails);
		        tableDetails.addCell(cellDetails);
		     
		        float total = 0f;
		        for(AbonoDTO abono : abonos) {
		        	cellDetails = new PdfPCell(new Phrase(abono.getAbonoId()+"",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		        	cellDetails.setBorder(0);
		        	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	       
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	        cellDetails = new PdfPCell(new Phrase(abono.getSaleNote().getDescription(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setBorder(0);
		        	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        cellDetails.setFixedHeight(cellHeightDetails);		 	       
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	        cellDetails = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abono.getCantidadAbono()),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
   		 	        cellDetails.setBorder(0);
		        	cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 	        cellDetails.setFixedHeight(cellHeightDetails);		 	      
		 	        tableDetails.addCell(cellDetails);		 	        
		 	      
		 	        cellDetails = new PdfPCell(new Phrase(abono.getTipoAbono().getDescripcion(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        cellDetails.setBorder(Rectangle.NO_BORDER);
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	        
		 	        cellDetails = new PdfPCell(new Phrase(abono.getDescripcion(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        tableDetails.addCell(cellDetails);
		 	        
		 	       cellDetails = new PdfPCell(new Phrase(abono.getCliente().getName()+" "+abono.getCliente().getFirstName(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
		 	        cellDetails.setFixedHeight(cellHeightDetails);
		 	        cellDetails.setBorder(0);
		 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		 	        tableDetails.addCell(cellDetails);
		 	        		 	        
		 	       total += abono.getCantidadAbono();
		        	
		        }
		        
		        // agregamos el total
		        cellDetails = new PdfPCell(new Phrase("Total: ",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL)));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	        cellDetails.setBorder(0);
	 	        cellDetails.setColspan(5);
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
