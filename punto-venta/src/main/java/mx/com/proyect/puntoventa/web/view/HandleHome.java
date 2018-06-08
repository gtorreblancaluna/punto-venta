package mx.com.proyect.puntoventa.web.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;

import org.springframework.ui.Model;


@Controller
public class HandleHome {

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/welcome.do", method = RequestMethod.GET)
	public String showBienvenida(HttpServletRequest request, Model model) {
		return"bienvenida";
	}
}
