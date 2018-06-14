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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mx.com.proyect.puntoventa.web.model.DeliveryDTO;
import mx.com.proyect.puntoventa.web.model.DeliveryDetailDTO;
import mx.com.proyect.puntoventa.web.service.ProviderService;

@Controller
public class GeneratePDFDeliveryController {

	@Autowired
	private ProviderService providerService;
	
	@RequestMapping(value = "/generate_pdf_delivery.do", method = RequestMethod.GET)
	public String printPdf(HttpServletRequest request,  HttpServletResponse response) throws IOException {
		
		String idOp = ( request.getParameter("idOp") != null ? request.getParameter("idOp") : null );
		
		if(idOp == null)
			throw new RuntimeException("No se encontro la operacion");
		
		DeliveryDTO delivery = providerService.getDeliveryById(new Integer(idOp));
		
		Document document = null;	   
	    PdfWriter writer = null;
		response.setContentType( "application/pdf" );	   
	    response.setHeader("Content-Disposition","inline; filename=\"" + "entrega_"+delivery.getDeliveryId()+".pdf\"");
	    OutputStream bao = response.getOutputStream(); 
	    
		try {
			document = new Document();			
			writer = PdfWriter.getInstance( document, bao );
			document.addAuthor(this.getClass().getName());
			document.addCreationDate();
			document.addProducer();
			document.addCreator(this.getClass().getName());
			document.addTitle("Nota de entrega");
			document.addKeywords("pdf, itext, Java");
			document.setPageSize( PageSize.LETTER);
			document.setMargins(60f, 60f, 20f, 20f);
			document.open();			
			document.newPage();	
			
			
//			document.add(new Paragraph("Nota venta No: "+saleNote.getSaleId()  ,new Font(Font.HELVETICA, 12, Font.NORMAL, new Color(80, 80, 80))));
			PdfPTable table = new PdfPTable(2);
			table.setTotalWidth(new float[]{ 160, 160 });
		    table.setLockedWidth(true);
		    int cellHeight = 17;
		    
		    // first row
	        PdfPCell cell = new PdfPCell(new Phrase("Nota de entrega No: "+delivery.getDeliveryId()));
	        cell.setFixedHeight(cellHeight);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(2);
	        table.addCell(cell);
	        // second row
	        cell = new PdfPCell(new Phrase("Cliente: ", new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase(delivery.getAccount().getName()+" "+delivery.getAccount().getFirstName(), new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        // third row
	        cell = new PdfPCell(new Phrase("Fecha registro: ", new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase(""+delivery.getDateRegister(), new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        // fourth row
	        cell = new PdfPCell(new Phrase("Fecha entrega: ", new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase(""+delivery.getDateDelivery(), new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        // fifth row
	        cell = new PdfPCell(new Phrase("Sucursal: ", new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase(delivery.getOffice().getName(), new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        // sixth row
	        cell = new PdfPCell(new Phrase("Descripci\u00F3n: ", new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase(delivery.getDescription(), new Font(Font.HELVETICA, 10, Font.NORMAL, new Color(80, 80, 80))));
	        cell.setFixedHeight(cellHeight);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        document.add(table);
	        
	        // Agregando tabla para los detalles
	        PdfPTable tableDetails = new PdfPTable(3);
	        tableDetails.setTotalWidth(new float[]{ 60, 120, 100 });
	        tableDetails.setLockedWidth(true);
		    int cellHeightDetails = 15;
		    // tabla para el detalle de la venta
		    // cantidad | descripcion | color | precio | subtotal
		    
	        PdfPCell cellDetails = new PdfPCell(new Phrase("Detalle entrega",new Font(Font.HELVETICA, 10, Font.NORMAL)));
	        cellDetails.setFixedHeight(cellHeightDetails);
	        cellDetails.setBorder(Rectangle.NO_BORDER);
	        cellDetails.setColspan(3);
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
	        
	        
	        for(DeliveryDetailDTO detail : delivery.getDetails()) {
	        	// agregar los articulos de entrega
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
		
	} // end print pdf
		
} //end controller
