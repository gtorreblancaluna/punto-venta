package mx.com.proyect.puntoventa.web.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import mx.com.proyect.puntoventa.web.forms.CustomerFilter;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.UserFilter;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.model.AccountDTOclient;
import mx.com.proyect.puntoventa.web.model.JobDTO;
import mx.com.proyect.puntoventa.web.model.OfficeDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;
import mx.com.proyect.puntoventa.web.service.OfficeService;

@Controller
public class HandleUserController {

	// inyectar dependencias de spring
	@Autowired
	AccountService accountService;
	@Autowired
	OfficeService officeService;

	// mostrar la vista principal
	@RequestMapping(value = "/handleUser.do", method = RequestMethod.GET)
	public String showHandleUser(HttpServletRequest request, Model model) {		
		
		model.addAttribute(this.getModelAttributtes(model));		
		model.addAttribute("account", new AccountDTO());
		return "handleUser";
	}

	// agregar usuario
	@RequestMapping(value = "/handleUser.do", params = "addUser")
	public String addUser(HttpServletRequest request, @ModelAttribute AccountDTO account, Model model) {
		accountService.addUser(account);
	
		model.addAttribute("account", account);	
		model.addAttribute(this.getModelAttributtes(model));
		model.addAttribute("messageView", "Exito al registrar el usuario: " + account.getEmail());
		return "handleUser";
	}

	// actualizar usuario
	@RequestMapping(value = "/handleUser.do", params = "updateUser")
	public String updateUser(HttpServletRequest request, @ModelAttribute AccountDTO account, Model model) {
		accountService.updateUser(account);		
		
		model.addAttribute(this.getModelAttributtes(model));
		model.addAttribute("messageView", "Exito al actualizar el usuario: " + account.getEmail());
		return "handleUser";
	}

	// eliminar usuario
	@SuppressWarnings("unused")
	@RequestMapping(value = "/handleUser.do", params = "deleteUser")
	public String deleteUser(HttpServletRequest request, @RequestParam("userId") String userId, Model model) {
		AccountDTO acc = null;
		String message=null;
		String messageError=null;
		
		if (userId == null || userId.equals("") ) {
			messageError = "No se recibio el parametro, por favor recarga la pagina";			
		}else {
			acc = accountService.findById(new Long (userId));
			if(acc==null) {
				messageError= "No se encontro el usuairo con id: "+userId;				
			}else {
				accountService.deleteUser(acc);				
				message= "Exito al eliminar el usuario: " + acc.getEmail();				
			}
		}
		
		if(message!=null)
			model.addAttribute("messageView",message);
		
		if(messageError!=null)
			model.addAttribute("messageError",messageError);


		model.addAttribute(this.getModelAttributtes(model));
		
		return "handleUser";
	}
	
	// busqueda por filtro
		@RequestMapping(value = "/handleUser.do", params = "filter")
		public String showUsersByFilter( HttpServletRequest request,@ModelAttribute UserFilter userFilter, Model model) {		
			List<AccountDTO> listUser = accountService.getUserByFilter(userFilter);
				model.addAttribute("messageView","Se obtuvieron "+listUser.size()+ " resultados");
			
				model.addAttribute("listUser",listUser);
				model.addAttribute(this.getModelAttributtes(model));
				
			return "handleUser";
		}
		
		public Model getModelAttributtes(Model model) {			
			model.addAttribute("listJobs", accountService.getAllJobs());
			model.addAttribute("listOffices", officeService.getAll());
			return model;
		}

}
