package mx.com.proyect.puntoventa.web.view;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.proyect.puntoventa.web.forms.CustomerFilter;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.model.AccountDTOclient;
import mx.com.proyect.puntoventa.web.service.AccountService;
import mx.com.proyect.puntoventa.web.service.ClientService;

@Controller
public class HandleCustomerController {
	
	@Autowired
	private ClientService clientService;

	
	@RequestMapping(value = "/handleCustomer.do", method = RequestMethod.GET)
	public String showAddClient( HttpServletRequest request, Model model) {		
		model.addAttribute("clientDTO", new AccountDTOclient());
		return "handleCustomer";
	}
	
	@RequestMapping(value = "/handleCustomer.do", params = "add")
	public String addCustomer( HttpServletRequest request,@ModelAttribute AccountDTOclient clientDTO, Model model) {		
		if(clientService.insertClient(clientDTO))
			model.addAttribute("messageView","Se agrego con exito el Cliente: "+clientDTO.getName()+" "+clientDTO.getFirstName());
		return "handleCustomer";
	}
	
	// busqueda por filtro
	@RequestMapping(value = "/handleCustomer.do", params = "filter")
	public String showCustomerByFilter( HttpServletRequest request,@ModelAttribute CustomerFilter customerFilter, Model model) {		
		List<AccountDTOclient> customersByFilter = clientService.getCustomerByFilter(customerFilter);
			model.addAttribute("messageView","Se obtuvieron "+customersByFilter.size()+ " resultados");
		
			model.addAttribute("customersByFilter",customersByFilter);
			
		return "handleCustomer";
	}
	
	
	

	
}
