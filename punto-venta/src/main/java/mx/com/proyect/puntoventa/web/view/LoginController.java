package mx.com.proyect.puntoventa.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.model.UserSession;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQueryItemsSold;
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/loginProcess.do", method = RequestMethod.POST)
	public String loginProcess(HttpSession session, HttpServletResponse response, @ModelAttribute LoginForm loginForm, Model model){
		AccountDTO a = accountService.validateUser(loginForm);				
		UserSession userSession = new UserSession(null);
		if(a != null) {	
			userSession.setAccount(a);
			session.setAttribute("userSession", userSession);
			model.addAttribute("userSession", userSession);
			
			// traemos ventas del dia			
			List<ResultQuerySaleNote> salesToday = saleNoteService.getSalesByParameter("today");
			model.addAttribute("salesToday", salesToday);
			// traer las ventas de la semana
			List<ResultQuerySaleNote> salesByWeek = saleNoteService.getSalesByParameter("week");
			model.addAttribute("salesByWeek", salesByWeek);
			// traer las ventas del mes
			List<ResultQuerySaleNote> salesByMonth = saleNoteService.getSalesByParameter("month");
			model.addAttribute("salesByMonth", salesByMonth);
			
			// traer los articulos vendidos por dia, semana y mes
			Map<String,Object> result = saleNoteService.getItemsSold();
			List<ResultQueryItemsSold> itemsByDay = (List<ResultQueryItemsSold>) result.get("itemsByDay");
			List<ResultQueryItemsSold> itemsByWeek = (List<ResultQueryItemsSold>) result.get("itemsByWeek");
			List<ResultQueryItemsSold> itemsByMonth = (List<ResultQueryItemsSold>) result.get("itemsByMonth");
			
			model.addAttribute("itemsByDay", itemsByDay);
			model.addAttribute("itemsByWeek", itemsByWeek);
			model.addAttribute("itemsByMonth", itemsByMonth);
			
			return "bienvenida";
		}else{
			model.addAttribute("message","Usuario o contrase\u00F1a no coinciden");
			return "login";
		}
	}
	
	

}
