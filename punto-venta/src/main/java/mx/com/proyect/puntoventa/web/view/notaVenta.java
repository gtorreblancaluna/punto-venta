package mx.com.proyect.puntoventa.web.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.proyect.puntoventa.web.model.PVUpdate;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;
@Controller

public class notaVenta {

@Autowired
AccountService accountService;

					@RequestMapping(value="showPV.do",method=RequestMethod.GET)
					public String showAddPV(HttpServletRequest request,Model model) {
					model.addAttribute("PV",new PVUpdate());
					return "addPV";
			}


	
}
