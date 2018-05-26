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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.AccountDTOclient;
import mx.com.proyect.puntoventa.web.model.ColorDTO;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.OfficeDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;
import mx.com.proyect.puntoventa.web.service.ClientService;
import mx.com.proyect.puntoventa.web.service.ColorService;
import mx.com.proyect.puntoventa.web.service.InventoryService;
import mx.com.proyect.puntoventa.web.service.OfficeService;

@Controller

public class HandleSaleNoteController {

	@Autowired
	AccountService accountService;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	ClientService clientService;
	@Autowired
	OfficeService officeService;
	@Autowired
	ColorService colorService;

	// vista principal
	@RequestMapping(value = "handleSaleNote.do", method = RequestMethod.GET)
	public String showSaleNote(HttpServletRequest request, Model model) {
		// traemos los productos del almacen
		List<ItemDTO> listItems = inventoryService.getAll();
		List<AccountDTOclient> listClients = clientService.getAll();
		List<OfficeDTO> listOffices = officeService.getAll();
		List<ColorDTO> listColors = colorService.getAll();
		

		model.addAttribute("listColors", listColors);
		model.addAttribute("listClients", listClients);
		model.addAttribute("saleNoteForm", new SaleNoteForm());
		model.addAttribute("listItems", listItems);
		model.addAttribute("listOffices", listOffices);
		return "handleSaleNote";
	}

	// agregar nota
	@RequestMapping(value = "handleSaleNote.do", params = "add")
	public String addSaleNote(HttpServletRequest request, 
			@ModelAttribute ("saleNoteForm") SaleNoteForm saleNoteForm, Model model) {
		List<AccountDTOclient> listClients = clientService.getAll();
		List<OfficeDTO> listOffices = officeService.getAll();
		
		// traemos los productos del almacen
		List<ItemDTO> listItems = inventoryService.getAll();
		List<ColorDTO> listColors = colorService.getAll();
		

		model.addAttribute("listColors", listColors);

		model.addAttribute("saleNoteForm", new SaleNoteForm());
		model.addAttribute("listItems", listItems);
		model.addAttribute("listClients", listClients);
		model.addAttribute("listOffices", listOffices);
		return "handleSaleNote";
	}

}
