package mx.com.proyect.puntoventa.web.view;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.proyect.puntoventa.web.model.AccountDTOclient;
import mx.com.proyect.puntoventa.web.service.AccountService;

@Controller

public class AddClient {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/showAddClnt.do", method = RequestMethod.GET)
	public String showAddClient( HttpServletRequest request, Model model) {		
		model.addAttribute("Client", new AccountDTOclient());
		return "addClients";
	}
	
	

	
}
