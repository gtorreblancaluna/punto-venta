package mx.com.proyect.puntoventa.web.view;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import mx.com.proyect.puntoventa.web.model.ProviderDTO;
import mx.com.proyect.puntoventa.web.service.ProviderService;
/**
 * 2018.07.27 GTL Los proveedores se van a manejar en el modulo de usuarios solo con el perfil de proovedores
 * */
@Controller
public class HandleProviderController {
	 
	@Autowired
	private ProviderService providerService;
	
	@GetMapping(value = "/handleProvider.do")
	public String show(HttpServletRequest request , Model model){
//		List<ProviderDTO> listProviders = providerService.getAll();
//		model.addAttribute("listProviders",listProviders);
		model.addAttribute("provider", new  ProviderDTO());
		return "handleprovider";
	}
	
	@PostMapping(value = "/handleProvider.do", params = "add")
	public String add(HttpServletRequest request , Model model, @ModelAttribute ProviderDTO providerDTO){
		providerService.add(providerDTO);
		return "handleprovider";
	}

}
