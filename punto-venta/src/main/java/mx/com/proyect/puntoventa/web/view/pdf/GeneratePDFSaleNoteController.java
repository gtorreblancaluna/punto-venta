package mx.com.proyect.puntoventa.web.view.pdf;

/** 2018.06.08 
 * @author Gerardo Torreblanca Luna
 * Controller para imprimir la nota de venta
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
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
import mx.com.proyect.puntoventa.web.model.SaleNoteDTO;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

@Controller
public class GeneratePDFSaleNoteController {

	@Autowired
	private SaleNoteService saleNoteService;
	
	@RequestMapping(value = "/generate_pdf_sale_note.do", method = RequestMethod.GET)
	public String printPdf(HttpServletRequest request,  HttpServletResponse response) throws IOException {
		
		String idOp = ( request.getParameter("idOp") != null ? request.getParameter("idOp") : null );
		
		if(idOp == null)
			throw new RuntimeException("No se encontro la operacion");
		
		SaleNoteDTO saleNote = saleNoteService.getSaleById(new Integer(idOp));
		
		Document document = null;	   
	    PdfWriter writer = null;
		response.setContentType( "application/pdf" );	   
	    response.setHeader("Content-Disposition","inline; filename=\"" + "nota_venta_"+saleNote.getSaleId()+".pdf\"");
	    OutputStream bao = response.getOutputStream(); 
	    
		try {
			document = new Document();			
			writer = PdfWriter.getInstance( document, bao );
			document.addAuthor(this.getClass().getName());
			document.addCreationDate();
			document.addProducer();
			document.addCreator(this.getClass().getName());
			document.addTitle("Nota de venta");
			document.addKeywords("pdf, itext, Java");
			document.setPageSize( PageSize.LETTER);
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
	        document.add(new Phrase("\n"));
	        document.add(new Phrase("\n"));
	        
	        document.add(new Phrase("\n"));
     
	        document.add(new Phrase("                                                                                             "
	        		+ "                                                                                                                             "+saleNote.getSaleId(),new Font(Font.HELVETICA,cellHeightInfoTable,Font.BOLD)));

	        
			
	        
	       //INFORMACION DEL CLIENTE
	   
	        document.add(new Phrase("\n"));  // salto de linea
	        
			PdfPTable table = new PdfPTable(3);
		
			table.setTotalWidth(new float[]{ 200, 160, 160 });
		    table.setLockedWidth(true);
		    int cellHeight = 14;
		    int cellHi=13;
		    
		    Date dateDelivery = new Date(saleNote.getDeliveryDate().getTime()); 
		    DateFormat formatoFecha;		    
		    formatoFecha = DateFormat.getDateInstance(DateFormat.FULL);

		    document.add(new Phrase("  \n"));
		    document.add(new Phrase("Toluca, Mex, a "+formatoFecha.format(dateDelivery), new Font(Font.HELVETICA,14, Font.NORMAL)));
	        String addressClient = saleNote.getClient().getStreet()+" "+saleNote.getClient().getColony()+" "+saleNote.getClient().getDelegation()+" "+saleNote.getClient().getCp();
	        document.add(new Phrase(" \n"+addressClient, new Font(Font.HELVETICA,12,Font.BOLD)));
		document.add(new Phrase("  \n "));
//	        cell = new PdfPCell(new Phrase(" ", new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL)));
//	        cell.setFixedHeight(cellHeight);
//	        cell.setVerticalAlignment(Element.ALIGN_CENTER);
//	        cell.setBorder(Rectangle.NO_BORDER);
//	        cell.setColspan(3);
//	        table.addCell(cell);	        
//		    
//	        // second row
//	        cell = new PdfPCell(new Phrase(" ", new Font(Font.HELVETICA, cellHeightInfoTable, Font.NORMAL)));
//	        cell.setFixedHeight(cellHeight);
//	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//	        cell.setBorder(Rectangle.NO_BORDER);
//	        cell.setColspan(3);
//	        table.addCell(cell);	         
//	        
//	        //cliente string concatenacion de variables 
//	        String addressClient = saleNote.getClient().getStreet()+" "+saleNote.getClient().getColony()+" "+saleNote.getClient().getDelegation()+" "+saleNote.getClient().getCp();
//	        
	        
//	        // third row
//	        cell = new PdfPCell(new Phrase("Direcci\u00F3n: "+addressClient, new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL)));
//	        cell.setFixedHeight(cellHeight);
//	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//	        cell.setBorder(Rectangle.NO_BORDER);
//	        cell.setColspan(3);
	         
//
//	        cell = new PdfPCell(new Phrase("Descripci\u00F3n: "+saleNote.getDescription(), new Font(Font.HELVETICA, cellHeightInfoTable, Font.NORMAL)));
//	        cell.setFixedHeight(cellHeight);
//	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//	        cell.setBorder(Rectangle.NO_BORDER);
//	        table.addCell(cell);	       
//	        
//	        // FIN INFORMACION DEL CLIENTE
//	    
	        document.add(table);
	      
	        // salto de linea
	        document.add(new Phrase("\n"));
	        // Agregando tabla para los detalles
	        PdfPTable tableDetails = new PdfPTable(5);
//	        tableDetails.setTotalWidth(new float[]{ 40, 120, 100, 100, 100 });
	        tableDetails.setTotalWidth(new float[] {50,110,140,160,100});
	        tableDetails.setLockedWidth(true);
		    int cellHeightDetails = 13;
		    // tabla para el detalle de la venta
		    // cantidad | descripcion | color | precio | subtotal   
	        
	    	// cabecera detalles
	        PdfPCell cellDetails = new PdfPCell(new Phrase("",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
 	        cellDetails.setFixedHeight(cellHeightDetails);
 	        
 	        tableDetails.addCell(cellDetails);
 	        
 	        cellDetails = new PdfPCell(new Phrase("",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        
	        tableDetails.addCell(cellDetails);
	        
	        cellDetails = new PdfPCell(new Phrase("",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        tableDetails.addCell(cellDetails);
	        
	        cellDetails = new PdfPCell(new Phrase("",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        tableDetails.addCell(cellDetails);
	        
	        cellDetails = new PdfPCell(new Phrase("",new Font(Font.HELVETICA, fontSizeNormal, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        
	        tableDetails.addCell(cellDetails);
	        Float total = 0f;
	        for(SaleDetailDTO detail : saleNote.getSaleDetails()) {
	        	// agregar los articulos de la venta
	        	// cantidad
	        	cellDetails = new PdfPCell(new Phrase(detail.getAmount()+"",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	       
	 	        tableDetails.addCell(cellDetails);
	 	        // descripcion
	 	        cellDetails = new PdfPCell(new Phrase(detail.getItem().getDescription(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	       
	 	        tableDetails.addCell(cellDetails);
	 	        //color
	 	        cellDetails = new PdfPCell(new Phrase(detail.getColor().getDescription(),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	      
	 	        tableDetails.addCell(cellDetails);
	 	        //precio
	 	        cellDetails = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(detail.getItem().getSalePrice()),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
//	 	        cellDetails.setBorder(Rectangle.NO_BORDER);
	 	        tableDetails.addCell(cellDetails);
	 	        //subtotal
	 	        String subtotal = NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(detail.getItem().getSalePrice()*detail.getAmount());
	 	        cellDetails = new PdfPCell(new Phrase(subtotal,new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
//	 	        cellDetails.setBorder(Rectangle.NO_BORDER);
	 	        tableDetails.addCell(cellDetails);
	 	        
	 	        total += new Float ((detail.getItem().getSalePrice()*detail.getAmount()));
	 	        
	        	
	        }
	        
	        // total a pagar
	        cellDetails = new PdfPCell(new Phrase("Total a pagar: ",new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL)));
 	        cellDetails.setFixedHeight(cellHeightDetails);
// 	        cellDetails.setBorder(Rectangle.TOP);
 	        cellDetails.setColspan(4);
 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
 	        tableDetails.addCell(cellDetails);
 	        
 	        cellDetails = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(total),new Font(Font.HELVETICA, fontSizeSmall, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
//	        cellDetails.setBorder(Rectangle.TOP);
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
		
	} // end print pdf
		
} //end controller
