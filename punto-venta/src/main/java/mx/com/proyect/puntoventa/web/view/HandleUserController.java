package mx.com.proyect.puntoventa.web.view;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;
@Controller
public class HandleUserController {

	//inyectar dependencias de spring
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/showUser.do", method = RequestMethod.GET)
	public String showHandleUser( HttpServletRequest request, Model model) {		
		model.addAttribute("account", new AccountDTO());
		return "handleUser";
	}
	@RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
	public String addUser( HttpServletRequest request,@ModelAttribute AccountDTO account, Model model) {	
		System.out.println("se a recibido account: "+account);
		accountService.addUser(account);
		model.addAttribute("account",account);
		return "handleUserSucess";
	}	
}
