package mx.com.proyect.puntoventa.web.view;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import mx.com.proyect.puntoventa.web.forms.LoginForm;


@Controller
@SessionAttributes("userSession")
public class LogoutController {	
	
	@RequestMapping(value = "/logout.do")
	public String showLogin( HttpServletRequest request, Model model) {	
		request.getSession().setAttribute( "userSession", null );
	    request.getSession().invalidate();
		model.addAttribute("loginForm", new LoginForm());		
		return "login";		
	}

}
