package mx.com.proyect.puntoventa.web.view;

import static mx.com.proyect.puntoventa.web.commons.ApplicationConstants.VENTA_ENTREGADO;
import static mx.com.proyect.puntoventa.web.commons.ApplicationConstants.VENTA_REGISTRADO;
import static mx.com.proyect.puntoventa.web.commons.ApplicationConstants.VENTA_CANCELADO;
import static mx.com.proyect.puntoventa.web.commons.ApplicationConstants.PUESTO_VENDEDOR;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import mx.com.proyect.puntoventa.web.forms.SaleForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.model.UserSession;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.AccountService;
import mx.com.proyect.puntoventa.web.service.ClientService;
import mx.com.proyect.puntoventa.web.service.ColorService;
import mx.com.proyect.puntoventa.web.service.InventoryService;
import mx.com.proyect.puntoventa.web.service.OfficeService;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

/* GTL 2018.05.21
 * Controlador para las notas de venta
 * 
 * */
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
	public String showSaleNote(HttpServletRequest request,  Model model) {
//		ModelAndView modelAndView = new ModelAndView("handleSaleNote");
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		if(userSession != null && userSession.getAccount() != null) {
			model.addAttribute("saleNoteForm", new SaleNoteForm());
			this.getModelAttributtes(model);
			// solo traer los articulos registrados en la sucursal que se logueo el usuario
			model.addAttribute("listItems", inventoryService.getAll(userSession.getAccount().getOffice().getOfficeId()));			
			return "handleSaleNote";
		}else {			
			model.addAttribute("messageError", "ERROR. No se encontro session, porfavor recarga la pagina y logueate correctamente ");
			return "handleSaleNote";		
		}
	}

	// agregar nota
	@PostMapping(value = "handleSaleNote.do", params = "add")
	public String addSaleNote(HttpServletRequest request, 
			@ModelAttribute ("saleNoteForm") SaleNoteForm saleNoteForm, Model model) {
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		if(userSession != null && userSession.getAccount() != null) {			
			// agregando pedido a la bd
			//2018.08.13  GTL agregamos el pedido en la sucursal donde se logueo el usuario
			saleNoteForm.setStoreId(userSession.getAccount().getOffice().getOfficeId()+"");
			saleNoteForm.getAbono().setUsuario(new AccountDTO());
			// asignamos el abono al usuario que esta logueado
			saleNoteForm.getAbono().getUsuario().setUserId(userSession.getAccount().getUserId());
			saleNoteService.add(saleNoteForm);		
			
			// se marco imprimir la nota despues de agregar los datos a bd
			if (saleNoteForm.isPrintSaleNote())
				model.addAttribute("printSaleId",saleNoteForm.getSaleId());
			// cargar datos al JSP
			this.getModelAttributtes(model);	
			// solo traer los articulos registrados en la sucursal que se logueo el usuario
			model.addAttribute("listItems", inventoryService.getAll(userSession.getAccount().getOffice().getOfficeId()));						
			model.addAttribute("messageSucess","Se agrego con exito la nota, total de articulos: "+saleNoteForm.getItems().size());
			model.addAttribute("saleNoteForm", new SaleNoteForm());
			return "handleSaleNoteSucess";
		
		}else {			
			model.addAttribute("messageError", "ERROR. No se encontro session, porfavor recarga la pagina y logueate correctamente ");
			return "handleSaleNote";		
		}
	}
	
	    // traer las notas por el filtro consultado
	@PostMapping(value = "handleSaleNote.do", params = "filter")
		public String getSaleNoteByFilter(HttpServletRequest request, 
				@ModelAttribute ("saleNoteFilter") SaleNoteFilter saleNoteFilter, Model model) {
			
			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");			
			if(userSession != null && userSession.getAccount() != null) {
				if(userSession.getAccount().getJob().getJobId().equals(PUESTO_VENDEDOR+"")) {
					// el usuario tiene puesto vendedor , filtraremos siempre en la sucursal donde esta logeado
					saleNoteFilter.setOfficeIdFilter(userSession.getAccount().getOffice().getOfficeId()+"");
				}
				List<ResultQuerySaleNote> listSaleNoteByFilter = saleNoteService.getByFilter(saleNoteFilter);			
				//obtenemos el resultado y enviamos al JSP
				model.addAttribute("listSaleNoteByFilter", listSaleNoteByFilter);
				model.addAttribute("messageSucess","Total de registros encontrados: "+listSaleNoteByFilter.size());
				this.getModelAttributtes(model);
				// solo traer los articulos registrados en la sucursal que se logueo el usuario
				model.addAttribute("listItems", inventoryService.getAll(userSession.getAccount().getOffice().getOfficeId()));				
				return "handleSaleNote";
			}else {			
				model.addAttribute("messageError", "ERROR. No se encontro session, porfavor recarga la pagina y logueate correctamente ");
				return "handleSaleNote";		
			}
		}
		
		
		// actualizar nota
	@PostMapping(value = "handleSaleNote.do", params = "update")
		public String updateSaleNote(HttpServletRequest request, 
				@ModelAttribute ("saleNoteForm") SaleNoteForm saleNoteForm, Model model) {
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		if(userSession != null && userSession.getAccount() != null) {	
			// actualizar pedido a la bd
			saleNoteService.update(saleNoteForm);	
			this.getModelAttributtes(model);
			// solo traer los articulos registrados en la sucursal que se logueo el usuario
			model.addAttribute("listItems", inventoryService.getAll(userSession.getAccount().getOffice().getOfficeId()));			
			model.addAttribute("messageSucess","Se actualizo con exito la nota, total de articulos: "+saleNoteForm.getItems().size());
			model.addAttribute("saleNoteForm", new SaleNoteForm());
			return "handleSaleNoteSucess";		
		}else {			
			model.addAttribute("messageError", "ERROR. No se encontro session, porfavor recarga la pagina y logueate correctamente ");
			return "handleSaleNote";		
		}
	
	}
	
	@PostMapping(value = "handleSaleNote.do", params = "change")
	public String changeStatus(HttpServletRequest request, 
			@ModelAttribute ("saleForm") SaleForm saleForm, Model model) {
		StringBuilder messageSucess = new StringBuilder();
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		if(userSession != null && userSession.getAccount() != null) {			
//		try {
			SaleNoteForm note = saleNoteService.getSaleNoteById(new Integer(saleForm.getSaleId()));		
			if(note == null) {
				model.addAttribute("messageError","No se encontro la operacion, recarga la pagina porfavor! ");
				return "handleSaleNote";
			}
			
			if(note.getStatus().getStatusId() == VENTA_REGISTRADO 
					&& Integer.parseInt(saleForm.getStatusId()) == VENTA_ENTREGADO) {
				/** si la venta es registrada y el estatus a cambiar es entregado entonces 
				 * procedemos a descontar del inventario 
				 * */
				saleNoteService.decreaseStockPerSale(note.getSaleDetail());
				messageSucess.append("Se descontaron "+note.getSaleDetail().size()+" articulos del inventario de manera exitosa ");
			}

			if(note.getStatus().getStatusId() == VENTA_ENTREGADO 
					&& Integer.parseInt(saleForm.getStatusId()) == VENTA_CANCELADO) {
				// la venta esta entregada y se cambia a cancelado
				saleNoteService.increaseStockPerSale(note.getSaleDetail());
				messageSucess.append("Se incrementarion "+note.getSaleDetail().size()+" articulos del inventario de manera exitosa ");
				
			}
			
			saleNoteService.changeStatus(new Integer(saleForm.getSaleId()), new Integer(saleForm.getStatusId()));
			
			this.getModelAttributtes(model);
			// solo traer los articulos registrados en la sucursal que se logueo el usuario
			model.addAttribute("listItems", inventoryService.getAll(userSession.getAccount().getOffice().getOfficeId()));
			
			messageSucess.append("| Se cambio el estatus de manera exitosa ");
			model.addAttribute("messageSucess",messageSucess.toString());
			return "handleSaleNote";
			
		}else {			
			model.addAttribute("messageError", "ERROR. No se encontro session, porfavor recarga la pagina y logueate correctamente ");
			return "handleSaleNote";		
		}
		
	}
	
	public Model getModelAttributtes(Model model) {		
//			model.addAttribute("listItems", inventoryService.getAll());
			model.addAttribute("listClients", clientService.getAll());		
			model.addAttribute("listOffices", officeService.getAll());
			model.addAttribute("listColors", colorService.getAll());		
			model.addAttribute("listUsers", accountService.getAllUser());
			model.addAttribute("listStatus", saleNoteService.getSalesStatus());	
			model.addAttribute("tipoAbonos", saleNoteService.obtenerTiposAbono());			
		return model;
	}

}
