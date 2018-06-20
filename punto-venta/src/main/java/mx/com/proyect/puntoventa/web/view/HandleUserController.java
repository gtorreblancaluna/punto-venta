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

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
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
		
		
		List<AccountDTO> listUser = accountService.getAllUser();		
		model.addAttribute("listUser", listUser);
		List<JobDTO> listJobs = accountService.getAllJobs();
		model.addAttribute("listJobs", listJobs);
		List<OfficeDTO> listOffices = officeService.getAll();
		model.addAttribute("listOffices", listOffices);
		
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
		List<JobDTO> listJobs = accountService.getAllJobs();
		model.addAttribute("listJobs", listJobs);
		List<OfficeDTO> listOffices = officeService.getAll();
		model.addAttribute("listOffices", listOffices);
		model.addAttribute("message", "Exito al registrar el usuario: " + account.getEmail());
		return "handleUser";
	}

	// actualizar usuario
	@RequestMapping(value = "/handleUser.do", params = "updateUser")
	public String updateUser(HttpServletRequest request, @ModelAttribute AccountDTO account, Model model) {
		accountService.updateUser(account);		
		List<AccountDTO> listUser = accountService.getAllUser();
		model.addAttribute("listUser", listUser);
		List<JobDTO> listJobs = accountService.getAllJobs();
		model.addAttribute("listJobs", listJobs);
		List<OfficeDTO> listOffices = officeService.getAll();
		model.addAttribute("listOffices", listOffices);
		model.addAttribute("message", "Exito al actualizar el usuario: " + account.getEmail());
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
			model.addAttribute("message",message);
		
		if(messageError!=null)
			model.addAttribute("messageError",messageError);

		List<AccountDTO> listUser = accountService.getAllUser();
		model.addAttribute("listUser", listUser);
		List<JobDTO> listJobs = accountService.getAllJobs();
		model.addAttribute("listJobs", listJobs);
		List<OfficeDTO> listOffices = officeService.getAll();
		model.addAttribute("listOffices", listOffices);
		return "handleUser";
	}

}
