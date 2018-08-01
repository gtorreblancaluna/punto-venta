package mx.com.proyect.puntoventa.web.view;

import java.util.List;


/* GTL 2018.05.21
 * Controlador para las notas de venta
 * 
 * */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import mx.com.proyect.puntoventa.web.forms.SaleForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.model.AccountDTOclient;
import mx.com.proyect.puntoventa.web.model.ColorDTO;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.OfficeDTO;
import mx.com.proyect.puntoventa.web.model.UserSession;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.AccountService;
import mx.com.proyect.puntoventa.web.service.ClientService;
import mx.com.proyect.puntoventa.web.service.ColorService;
import mx.com.proyect.puntoventa.web.service.InventoryService;
import mx.com.proyect.puntoventa.web.service.OfficeService;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;


@Controller
public class HandleSaleNoteController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private SaleNoteService saleNoteService;

	// vista principal
	@GetMapping(value = "handleSaleNote.do")
	public ModelAndView showSaleNote( HttpServletRequest request,HttpServletResponse response) {		
		ModelAndView modelAndView = new ModelAndView("handleSaleNote");
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		// traemos los productos del almacen
		List<ItemDTO> listItems = inventoryService.getAll();
		List<AccountDTOclient> listClients = clientService.getAll();
		List<OfficeDTO> listOffices = officeService.getAll();
		List<ColorDTO> listColors = colorService.getAll();
		List<AccountDTO> listUsers = accountService.getAllUser();	
		
		
		modelAndView.addObject("listStatus", saleNoteService.getSalesStatus());
		modelAndView.addObject("listUsers", listUsers);
		modelAndView.addObject("listColors", listColors);
		modelAndView.addObject("listClients", listClients);
		modelAndView.addObject("saleNoteForm", new SaleNoteForm());
		modelAndView.addObject("listItems", listItems);
		modelAndView.addObject("listOffices", listOffices);
		
		return modelAndView;
	}

	// agregar nota
	@PostMapping(value = "handleSaleNote.do", params = "add")
	public String addSaleNote(HttpServletRequest request, 
			@ModelAttribute ("saleNoteForm") SaleNoteForm saleNoteForm, Model model) {		
		// agregando pedido a la bd
		saleNoteService.add(saleNoteForm);		
		
		// se marco imprimir la nota despues de agregar los datos a bd
		if (saleNoteForm.isPrintSaleNote())
			model.addAttribute("printSaleId",saleNoteForm.getSaleId());
		// cargar datos al JSP
		List<AccountDTOclient> listClients = clientService.getAll();
		model.addAttribute("listClients", listClients);
		List<OfficeDTO> listOffices = officeService.getAll();
		model.addAttribute("listOffices", listOffices);		
		// traemos los productos del almacen
		List<ItemDTO> listItems = inventoryService.getAll();
		model.addAttribute("listItems", listItems);
		List<ColorDTO> listColors = colorService.getAll();
		model.addAttribute("listColors", listColors);		
		List<AccountDTO> listUsers = accountService.getAllUser();
		model.addAttribute("listUsers", listUsers);		
		model.addAttribute("listStatus", saleNoteService.getSalesStatus());
		
		model.addAttribute("messageSucess","Se agrego con exito la nota, total de articulos: "+saleNoteForm.getItems().size());
		model.addAttribute("saleNoteForm", new SaleNoteForm());
		return "handleSaleNote";
	}
	
	    // traer las notas por el filtro consultado
	@PostMapping(value = "handleSaleNote.do", params = "filter")
		public String getSaleNoteByFilter(HttpServletRequest request, 
				@ModelAttribute ("saleNoteFilter") SaleNoteFilter saleNoteFilter, Model model) {
			
			List<ResultQuerySaleNote> listSaleNoteByFilter = saleNoteService.getByFilter(saleNoteFilter);			
			//obtenemos el resultado y enviamos al JSP
			model.addAttribute("listSaleNoteByFilter", listSaleNoteByFilter);
			model.addAttribute("messageSucess","Total de registros encontrados: "+listSaleNoteByFilter.size());
			
			
			// cargar datos al JSP
			List<AccountDTOclient> listClients = clientService.getAll();
			model.addAttribute("listClients", listClients);
			List<OfficeDTO> listOffices = officeService.getAll();
			model.addAttribute("listOffices", listOffices);		
			// traemos los productos del almacen
			List<ItemDTO> listItems = inventoryService.getAll();
			model.addAttribute("listItems", listItems);
			List<ColorDTO> listColors = colorService.getAll();
			model.addAttribute("listColors", listColors);		
			List<AccountDTO> listUsers = accountService.getAllUser();
			model.addAttribute("listUsers", listUsers);		
			// fin cargar datos al JSP
			model.addAttribute("listStatus", saleNoteService.getSalesStatus());
			
			return "handleSaleNote";
		}
		
		
		// actualizar nota
	@PostMapping(value = "handleSaleNote.do", params = "update")
		public String updateSaleNote(HttpServletRequest request, 
				@ModelAttribute ("saleNoteForm") SaleNoteForm saleNoteForm, Model model) {
			
			// actualizar pedido a la bd
			saleNoteService.update(saleNoteForm);		
			
			// cargar datos al JSP
			List<AccountDTOclient> listClients = clientService.getAll();
			model.addAttribute("listClients", listClients);
			List<OfficeDTO> listOffices = officeService.getAll();
			model.addAttribute("listOffices", listOffices);		
			// traemos los productos del almacen
			List<ItemDTO> listItems = inventoryService.getAll();
			model.addAttribute("listItems", listItems);
			List<ColorDTO> listColors = colorService.getAll();
			model.addAttribute("listColors", listColors);		
			List<AccountDTO> listUsers = accountService.getAllUser();
			model.addAttribute("listUsers", listUsers);		
			model.addAttribute("listStatus", saleNoteService.getSalesStatus());
			
			model.addAttribute("messageSucess","Se actualizo con exito la nota, total de articulos: "+saleNoteForm.getItems().size());
			model.addAttribute("saleNoteForm", new SaleNoteForm());
			return "handleSaleNote";
		}
	
	
	
	@PostMapping(value = "handleSaleNote.do", params = "change")
	public String changeStatus(HttpServletRequest request, 
			@ModelAttribute ("saleForm") SaleForm saleForm, Model model) {
		
		try {
			SaleNoteForm note = saleNoteService.getSaleNoteById(new Integer(saleForm.getSaleId()));
		
			if(note == null) {
				model.addAttribute("messageError","No se encontro la operacion, recarga la pagina porfavor! ");
				return "handleSaleNote";
			}
			saleNoteService.changeStatus(new Integer(saleForm.getSaleId()), new Integer(saleForm.getStatusId()));
			
			// cargar datos al JSP
			List<AccountDTOclient> listClients = clientService.getAll();
			model.addAttribute("listClients", listClients);
			List<OfficeDTO> listOffices = officeService.getAll();
			model.addAttribute("listOffices", listOffices);		
			// traemos los productos del almacen
			List<ItemDTO> listItems = inventoryService.getAll();
			model.addAttribute("listItems", listItems);
			List<ColorDTO> listColors = colorService.getAll();
			model.addAttribute("listColors", listColors);		
			List<AccountDTO> listUsers = accountService.getAllUser();
			model.addAttribute("listUsers", listUsers);		
			model.addAttribute("listStatus", saleNoteService.getSalesStatus());
			model.addAttribute("messageSucess","Se cambio el status de manera exitosa");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		
		return "handleSaleNote";
		
	}

}
