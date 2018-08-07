package mx.com.proyect.puntoventa.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
	
	
//	@GetMapping(value = "/login.do")
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView showLogin( HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("login");	
		HttpSession httpSession = request.getSession();
		UserSession userSession = (UserSession) httpSession.getAttribute("userSession");	
		modelAndView.addObject("loginForm", new LoginForm());
		
		if (request.getParameter("JSESSIONID") != null) {
	    Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
	    response.addCookie(userCookie);
		} else {
		    String sessionId = httpSession.getId();
		    Cookie userCookie = new Cookie("JSESSIONID", sessionId);
		    response.addCookie(userCookie);
		}
		
		httpSession.setAttribute("userSession", userSession);
		
		if(userSession != null && userSession.getAccount() != null)
			 modelAndView.setViewName("bienvenida");
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/loginProcess.do", method = RequestMethod.POST)
//	@PostMapping
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute LoginForm loginForm){
		
		HttpSession httpSession = request.getSession();
		UserSession userSession = (UserSession) httpSession.getAttribute("userSession");
		
		ModelAndView modelAndView = new ModelAndView("bienvenida");	
		
		AccountDTO account = accountService.validateUser(loginForm);				
		if(userSession==null)
			userSession = new UserSession(null);
		
		if(account == null) {
			modelAndView.addObject("message","Usuario o contrase\u00F1a no coinciden");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		if(account != null && !account.getJob().getJobId().equals("4")) {
			userSession.setAccount(account);
//			HttpSession session = request.getSession(true);
			httpSession.setAttribute("userSession", userSession);
//			modelAndView.addObject("userSession", userSession);
//			request.getSession().setAttribute("userSession", userSession);
//			model.addAttribute("userSession", userSession);
			
			// traemos ventas del dia			
			List<ResultQuerySaleNote> salesToday = saleNoteService.getSalesByParameter("today");
			modelAndView.addObject("salesToday", salesToday);
			// traer las ventas de la semana
			List<ResultQuerySaleNote> salesByWeek = saleNoteService.getSalesByParameter("week");
			modelAndView.addObject("salesByWeek", salesByWeek);
			// traer las ventas del mes
			List<ResultQuerySaleNote> salesByMonth = saleNoteService.getSalesByParameter("month");
			modelAndView.addObject("salesByMonth", salesByMonth);
			
			// traer los articulos vendidos por dia, semana y mes
			Map<String,Object> result = saleNoteService.getItemsSold();
			List<ResultQueryItemsSold> itemsByDay = (List<ResultQueryItemsSold>) result.get("itemsByDay");
			List<ResultQueryItemsSold> itemsByWeek = (List<ResultQueryItemsSold>) result.get("itemsByWeek");
			List<ResultQueryItemsSold> itemsByMonth = (List<ResultQueryItemsSold>) result.get("itemsByMonth");
			
			modelAndView.addObject("itemsByDay", itemsByDay);
			modelAndView.addObject("itemsByWeek", itemsByWeek);
			modelAndView.addObject("itemsByMonth", itemsByMonth);
			
			return modelAndView;
		}else if(account != null && account.getJob().getJobId().equals("4")){
			// El usuario es un proveedor lo dirigimos a la pagina de proveedores
			userSession.setAccount(account);
			httpSession.setAttribute("userSession", userSession);
			modelAndView.setViewName("handleDelivery");
			return modelAndView;
		}
		return null;
	}
	
	

}
