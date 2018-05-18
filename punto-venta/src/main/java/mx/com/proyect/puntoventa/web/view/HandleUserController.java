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
public class HandleUserController {

	// inyectar dependencias de spring
	@Autowired
	AccountService accountService;

	// mostrar la vista principal
	@RequestMapping(value = "/handleUser.do", method = RequestMethod.GET)
	public String showHandleUser(HttpServletRequest request, Model model) {
		List<AccountDTO> listUser = accountService.getAllUser();
		model.addAttribute("listUser", listUser);
		model.addAttribute("account", new AccountDTO());
		return "handleUser";
	}

	// agregar usuario
	@RequestMapping(value = "/handleUser.do", params = "addUser")
	public String addUser(HttpServletRequest request, @ModelAttribute AccountDTO account, Model model) {
		accountService.addUser(account);		
		List<AccountDTO> listUser = accountService.getAllUser();		
		model.addAttribute("listUser", listUser);
		model.addAttribute("account", account);
		model.addAttribute("message","Exito al registrar el usuario: "+account.getEmail());		
		return "handleUser";
	}

	// actualizar usuario
	@RequestMapping(value = "/handleUser.do", params = "updateUser")
	public String updateUser(HttpServletRequest request, @ModelAttribute AccountDTO account, Model model) {
		
		model.addAttribute("message","Exito al actualizar el usuario: "+account.getEmail());	
		return "handleUser";
	}
	

}
