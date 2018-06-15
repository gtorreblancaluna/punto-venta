package mx.com.proyect.puntoventa.web.view;

/**
 * @author Gerardo Torreblanca
 * 2018.06.14
 * Controlador que maneja las peticiones de la vista ENTREGAS por parte del proveedor
 *  
 * */

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.model.AccountDTOclient;
import mx.com.proyect.puntoventa.web.model.ColorDTO;
import mx.com.proyect.puntoventa.web.model.DeliveryDTO;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.OfficeDTO;
import mx.com.proyect.puntoventa.web.model.ProviderDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQueryDelivery;
import mx.com.proyect.puntoventa.web.service.ColorService;
import mx.com.proyect.puntoventa.web.service.InventoryService;
import mx.com.proyect.puntoventa.web.service.OfficeService;
import mx.com.proyect.puntoventa.web.service.ProviderService;

@Controller
public class HandleDeliveryController {
	
	@Autowired
	ProviderService providerService;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	OfficeService officeService;
	@Autowired
	ColorService colorService;
	
	// mostrando pantalla inicial
	@RequestMapping(value = "/handleDelivery.do" , method = RequestMethod.GET)
	public String showhandleProvider(HttpServletRequest request , org.springframework.ui.Model model){
		// enviando los articulos al JSP
		List<ItemDTO> listItems = inventoryService.getAll();	
		model.addAttribute("listItems", listItems);
		// enviando las sucursales al JSP
		List<OfficeDTO> listOffices = officeService.getAll();
		model.addAttribute("listOffices", listOffices);
		// enviando los colores al JSP
		List<ColorDTO> listColors = colorService.getAll();
		model.addAttribute("listColors", listColors);		
		
		model.addAttribute("deliveryForm", new  DeliveryDTO());
		return "handleDelivery";		
		
	}
	
	// agregar entrega
		@RequestMapping(value = "handleDelivery.do", params = "add")
		public String addSaleNote(HttpServletRequest request, 
				@ModelAttribute ("deliveryForm") DeliveryDTO deliveryForm, Model model) {
			AccountDTO account = null;
			account = (AccountDTO) WebUtils.getSessionAttribute(request, "userSession");
			if(account == null) {
				account = new AccountDTO();
				account.setUserId("1");
			}
			deliveryForm.setAccount(account);
			deliveryForm.setStatus("1");
			providerService.addDelivery(deliveryForm);
			model.addAttribute("messageSucess","Se agrego con exito la entrega, total de articulos: "+deliveryForm.getDetails().size());
			
			// enviando los articulos al JSP
			List<ItemDTO> listItems = inventoryService.getAll();	
			model.addAttribute("listItems", listItems);
			// enviando las sucursales al JSP
			List<OfficeDTO> listOffices = officeService.getAll();
			model.addAttribute("listOffices", listOffices);
			// enviando los colores al JSP
			List<ColorDTO> listColors = colorService.getAll();
			model.addAttribute("listColors", listColors);		
			
			model.addAttribute("deliveryForm", new  DeliveryDTO());
			
			return "handleDelivery";		
		}
		
		 // traer las entregas por el filtro consultado
		@RequestMapping(value = "handleDelivery.do", params = "filter")
		public String getSaleNoteByFilter(HttpServletRequest request, 
				@ModelAttribute ("saleNoteFilter") SaleNoteFilter saleNoteFilter, Model model) {
			
			AccountDTO account = null;
			account = (AccountDTO) WebUtils.getSessionAttribute(request, "userSession");
			if(account == null) {
				account = new AccountDTO();
				account.setUserId("1");
			}
			// Solo traera las entregas del usuario logueado
			saleNoteFilter.setUserId(account.getUserId());
			List<ResultQueryDelivery> listDelivery = providerService.getByFilter(saleNoteFilter);
			model.addAttribute("listDelivery", listDelivery);
			
			// enviando los articulos al JSP
			List<ItemDTO> listItems = inventoryService.getAll();	
			model.addAttribute("listItems", listItems);
			// enviando las sucursales al JSP
			List<OfficeDTO> listOffices = officeService.getAll();
			model.addAttribute("listOffices", listOffices);
			// enviando los colores al JSP
			List<ColorDTO> listColors = colorService.getAll();
			model.addAttribute("listColors", listColors);		
			
			
			return "handleDelivery";
		}

}
