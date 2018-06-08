package mx.com.proyect.puntoventa.web.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.proyect.puntoventa.web.model.ProviderDTO;
import mx.com.proyect.puntoventa.web.service.ProviderService;

@Controller
public class HandleProviderController {
	
	//inyectar dependencias de spring 
	@Autowired
	ProviderService providerService;
	
	@RequestMapping(value = "/handleProvider.do" , method = RequestMethod.GET)
	public String showhandleProvider(HttpServletRequest request , org.springframework.ui.Model model){
//		List<ProviderDTO> listProviders = providerService.getAll();
//		model.addAttribute("listProviders",listProviders);
//		
//		//traer lista de provedores
//		
//		//retornar hacia el jsp para interactuar con el 
//		model.addAttribute("provider", new  ProviderDTO());
		return "handleprovider";
	}

}
