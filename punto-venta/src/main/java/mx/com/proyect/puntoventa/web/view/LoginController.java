package mx.com.proyect.puntoventa.web.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.model.UserSession;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.AccountService;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;


@Controller
public class LoginController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private SaleNoteService saleNoteService;
	
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String showLogin( HttpServletRequest request, Model model) {		
		model.addAttribute("loginForm", new LoginForm());		
		return "login";		
	}
	
	@RequestMapping(value = "/loginProcess.do", method = RequestMethod.POST)
	public String loginProcess(HttpSession session, HttpServletResponse response, @ModelAttribute LoginForm loginForm, Model model){
		AccountDTO a = accountService.validateUser(loginForm);				
		UserSession userSession = new UserSession(null);
		if(a != null) {	
			userSession.setAccount(a);
			session.setAttribute("userSession", userSession);
			model.addAttribute("userSession", userSession);
			
			// traemos los pedidos del dia			
			List<ResultQuerySaleNote> salesToday = saleNoteService.getSalesToday();
			model.addAttribute("salesToday", salesToday);
			return "bienvenida";
		}else{
			model.addAttribute("message","Usuario o contrase\u00F1a no coinciden");
			return "login";
		}
	}
	
	

}
