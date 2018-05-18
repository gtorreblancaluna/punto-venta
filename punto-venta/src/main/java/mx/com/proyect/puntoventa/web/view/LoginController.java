package mx.com.proyect.puntoventa.web.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;

@Controller
public class LoginController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String showLogin( HttpServletRequest request, Model model) {		
		model.addAttribute("loginForm", new LoginForm());		
		return "login";		
	}
	
	@RequestMapping(value = "/loginProcess.do", method = RequestMethod.POST)
	public String loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute LoginForm loginForm, Model model){
		AccountDTO a = accountService.validateUser(loginForm);				
		if(a != null) {		
			request.getSession().setAttribute("accountSession", a);		
			return "bienvenida";
		}else{
			model.addAttribute("message","Usuario o contrase\u00F1a no coinciden");
			return "login";
		}
	}

}
