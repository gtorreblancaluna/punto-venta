package mx.com.proyect.puntoventa.web.view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.UserSession;

@Controller
public class LogoutController {	
	
	@RequestMapping(value = "/logout.do")
	public String showLogin( HttpServletRequest request, Model model,HttpServletResponse response) {	
		
		HttpSession httpSession = request.getSession();
		UserSession userSession = (UserSession) httpSession.getAttribute("userSession");
		
		if (request.getParameter("JSESSIONID") != null) {
	    Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
	    response.addCookie(userCookie);
		} else {
		    String sessionId = httpSession.getId();
		    Cookie userCookie = new Cookie("JSESSIONID", sessionId);
		    response.addCookie(userCookie);
		}
	
		if(userSession!=null) {
			httpSession.setAttribute( "userSession", null );
//		    request.getSession().invalidate();
			model.addAttribute("loginForm", new LoginForm());
		}
		httpSession.setAttribute("userSession", new UserSession(null));
		return "login";		
	}

}
