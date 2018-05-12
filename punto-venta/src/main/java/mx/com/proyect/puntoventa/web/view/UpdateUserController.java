package mx.com.proyect.puntoventa.web.view;
import java.util.List;

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
public class UpdateUserController {

	//inyectar dependencias de spring
	@Autowired
	AccountService accountService;	
	
	//mostrar la vista principal
		@RequestMapping(value = "/showUserUpdate.do", method = RequestMethod.GET)
		public String showHandleUser( HttpServletRequest request, Model model) {		
			model.addAttribute("account", new AccountDTO());
			List<AccountDTO> listUser = accountService.getAllUser();
			model.addAttribute("listUser",listUser);
			return "updateUser";
		}
	
	
	// actualizar usuario
	@RequestMapping(value = "/updateUser.do", method = RequestMethod.POST)
	public String updateUser( HttpServletRequest request,@ModelAttribute AccountDTO account, Model model) {	
		
		
		model.addAttribute("account",account);
		return "handleUserSucess";
		
	
	}	
}
