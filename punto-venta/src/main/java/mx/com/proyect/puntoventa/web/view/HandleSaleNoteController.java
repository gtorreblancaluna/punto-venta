package mx.com.proyect.puntoventa.web.view;


import java.util.List;

/* GTL 2018.05.21
 * Controlador para las notas de venta
 * 
 * */
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;
import mx.com.proyect.puntoventa.web.service.InventoryService;

@Controller

public class HandleSaleNoteController {

	@Autowired
	AccountService accountService;
	@Autowired
	InventoryService inventoryService;

	// vista principal
	@RequestMapping(value = "handleSaleNote.do", method = RequestMethod.GET)
	public String showAddPV(HttpServletRequest request, Model model) {
		// traemos los productos del almacen
		List<ItemDTO> listItems = inventoryService.getAll();		
		
		model.addAttribute("saleNoteForm", new SaleNoteForm());		
		model.addAttribute("listItems", listItems);	
		return "handleSaleNote";
	}

}
