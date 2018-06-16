package mx.com.proyect.puntoventa.web.view.pdf;

/** 2018.06.08 
 * @author Gerardo Torreblanca Luna
 * Controller para imprimir la nota de venta
 * 
 **/
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
//import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
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
		    int cellHeightInfoTable = 15;
		    
		    PdfPCell cell = new PdfPCell(new Phrase("",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeightInfoTable);
	        cell.setColspan(2);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        headerInfoTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("FOLIO: "+saleNote.getSaleId() ,new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeightInfoTable);
	        cell.setColspan(1);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        headerInfoTable.addCell(cell);
		    
		    cell = new PdfPCell(new Phrase("M U E B L E S   F L O R I D A ",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeightInfoTable);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        headerInfoTable.addCell(cell);        
		    
	        cell = new PdfPCell(new Phrase("SALA A PRECIO DE FABRICA",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeightInfoTable);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        headerInfoTable.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("MA DE JESUS ALMARAZ LOPEZ",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeightInfoTable);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        headerInfoTable.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("RFC: AALJ560807265  AALJ560807MMCLPS02",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeightInfoTable);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        headerInfoTable.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("PASEO TOLLOCAN NO.147 COL.UNIVERSIDAD TOLUCA, MEX.",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeightInfoTable);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        headerInfoTable.addCell(cell);	       
	      	        
	        cell = new PdfPCell(new Phrase("Tels. 318-02-57. 318-02-58 Y 212-18-44" ,new Font(Font.HELVETICA, 9, Font.NORMAL)));
	        cell.setFixedHeight(cellHeightInfoTable);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        headerInfoTable.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("www.mueblesflorida.com.mx   -  floridamuebleria@yahoo.com.mx" ,new Font(Font.HELVETICA, 7, Font.NORMAL)));
	        cell.setFixedHeight(cellHeightInfoTable);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        headerInfoTable.addCell(cell);
	        document.add(headerInfoTable);
	        
	       //INFORMACION DEL CLIENTE
	     // salto de linea
	        document.add(new Phrase("\n"));
	        
			PdfPTable table = new PdfPTable(3);
			table.setTotalWidth(new float[]{ 80, 160, 160 });
		    table.setLockedWidth(true);
		    int cellHeight = 15;
		    
		    Date dateDelivery = new Date(saleNote.getDeliveryDate().getTime()); 
		    DateFormat formatoFecha;		    
		    formatoFecha = DateFormat.getDateInstance(DateFormat.FULL);

	        cell = new PdfPCell(new Phrase("Toluca, Mex, fecha entrega: "+formatoFecha.format(dateDelivery), new Font(Font.HELVETICA, 8, Font.NORMAL)));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        table.addCell(cell);	        
		    
	        // second row
	        cell = new PdfPCell(new Phrase("Se\u00F1or: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(1);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(saleNote.getClient().getName()+" "+saleNote.getClient().getFirstName(), new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(2);
	        table.addCell(cell);   
	        
	        // third row
	        cell = new PdfPCell(new Phrase("Direcci\u00F3n: ", new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        String addressClient = saleNote.getClient().getStreet()+" "+saleNote.getClient().getColony()+" "+saleNote.getClient().getDelegation()+" "+saleNote.getClient().getCp();
	        
	        cell = new PdfPCell(new Phrase(addressClient, new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);

	        cell = new PdfPCell(new Phrase("Descripci\u00F3n: ", new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase(saleNote.getDescription(), new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	    
	        document.add(table);
	      
	        // salto de linea
	        document.add(new Phrase("\n"));
	        // Agregando tabla para los detalles
	        PdfPTable tableDetails = new PdfPTable(5);
	        tableDetails.setTotalWidth(new float[]{ 60, 120, 100, 100, 100 });
	        tableDetails.setLockedWidth(true);
		    int cellHeightDetails = 15;
		    // tabla para el detalle de la venta
		    // cantidad | descripcion | color | precio | subtotal
		    
	        PdfPCell cellDetails = new PdfPCell(new Phrase(" DETALLE DE VENTA ",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cellDetails.setFixedHeight(25);
	        cellDetails.setBorder(Rectangle.NO_BORDER);
	        cellDetails.setColspan(5);
	        cellDetails.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tableDetails.addCell(cellDetails);	 
	        
	    	// cabecera detalles
	       	cellDetails = new PdfPCell(new Phrase("Cantidad",new Font(Font.HELVETICA, 10, Font.NORMAL)));
 	        cellDetails.setFixedHeight(cellHeightDetails);
 	        cellDetails.setBorder(Rectangle.NO_BORDER);
 	        tableDetails.addCell(cellDetails);
 	        
 	        cellDetails = new PdfPCell(new Phrase("Descripci\u00F3n",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        cellDetails.setBorder(Rectangle.NO_BORDER);
	        tableDetails.addCell(cellDetails);
	        
	        cellDetails = new PdfPCell(new Phrase("Color",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        cellDetails.setBorder(Rectangle.NO_BORDER);
	        tableDetails.addCell(cellDetails);
	        
	        cellDetails = new PdfPCell(new Phrase("Precio",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        cellDetails.setBorder(Rectangle.NO_BORDER);
	        tableDetails.addCell(cellDetails);
	        
	        cellDetails = new PdfPCell(new Phrase("Subtotal",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        cellDetails.setBorder(Rectangle.NO_BORDER);
	        tableDetails.addCell(cellDetails);
	        Float total = 0f;
	        for(SaleDetailDTO detail : saleNote.getSaleDetails()) {
	        	// agregar los articulos de la venta
	        	// cantidad
	        	cellDetails = new PdfPCell(new Phrase(detail.getAmount()+"",new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	        cellDetails.setBorder(Rectangle.NO_BORDER);
	 	        tableDetails.addCell(cellDetails);
	 	        // descripcion
	 	        cellDetails = new PdfPCell(new Phrase(detail.getItem().getDescription(),new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	        cellDetails.setBorder(Rectangle.NO_BORDER);
	 	        tableDetails.addCell(cellDetails);
	 	        //color
	 	        cellDetails = new PdfPCell(new Phrase(detail.getColor().getDescription(),new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	        cellDetails.setBorder(Rectangle.NO_BORDER);
	 	        tableDetails.addCell(cellDetails);
	 	        //precio
	 	        cellDetails = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(detail.getItem().getSalePrice()),new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	 	        cellDetails.setBorder(Rectangle.NO_BORDER);
	 	        tableDetails.addCell(cellDetails);
	 	        //subtotal
	 	        String subtotal = NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(detail.getItem().getSalePrice()*detail.getAmount());
	 	        cellDetails = new PdfPCell(new Phrase(subtotal,new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	 	        cellDetails.setFixedHeight(cellHeightDetails);
	 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	 	        cellDetails.setBorder(Rectangle.NO_BORDER);
	 	        tableDetails.addCell(cellDetails);
	 	        
	 	        total += new Float ((detail.getItem().getSalePrice()*detail.getAmount()));
	 	        
	        	
	        }
	        
	        // total a pagar
	        cellDetails = new PdfPCell(new Phrase("Total a pagar: ",new Font(Font.HELVETICA, 10, Font.NORMAL)));
 	        cellDetails.setFixedHeight(cellHeightDetails);
 	        cellDetails.setBorder(Rectangle.TOP);
 	        cellDetails.setColspan(4);
 	        cellDetails.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
 	        tableDetails.addCell(cellDetails);
 	        
 	        cellDetails = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(total),new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        cellDetails.setBorder(Rectangle.TOP);
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
