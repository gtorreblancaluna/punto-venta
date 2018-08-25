package mx.com.proyect.puntoventa.web.view;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.AbonoDTO;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.model.UserSession;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

/* GTL 2018.05.21
 * Controlador para las notas de venta
 * 
 * */

@Controller
public class CobranzaController {

	@Autowired
	private SaleNoteService saleNoteService;

	// vista principal
	@GetMapping(value = "cobranza.do")
	public String vistaPrincipal(HttpServletRequest request,  Model model) {		
		this.getModelAttributtes(model);
		return "cobranza";
		
	}
	
	// busqueda por filtro
	@PostMapping(value = "cobranza.do", params = "filter")
	public String aplicarFiltro(HttpServletRequest request,  
			@ModelAttribute ("saleNoteFilter") SaleNoteFilter saleNoteFilter, Model model) {		
		// traeremos las notas que tengan bandera de credito
		List<ResultQuerySaleNote> ventas = saleNoteService.getByFilter(saleNoteFilter);
		for(ResultQuerySaleNote res : ventas) {
			res.setTotalAmount(saleNoteService.getTotalSaleById(res.getSaleId()));
			res.setResta(res.getTotalAmount() - res.getTotalAbonos());
		}
		model.addAttribute("messageView","Se obtuvieron "+ventas.size()+" resultados");
		model.addAttribute("ventas",ventas);
		this.getModelAttributtes(model);
		return "cobranza";
		
	}
	
	// agregar abono
		@PostMapping(value = "cobranza.do", params = "agregarAbono")
		public String agregarAbono(HttpServletRequest request,  
				@ModelAttribute ("abono") AbonoDTO abono, Model model) {	
			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			
			if(userSession != null && userSession.getAccount() != null) {	
			abono.setUsuario(new AccountDTO());
			abono.getUsuario().setUserId(userSession.getAccount().getUserId());
			saleNoteService.agregarAbono(abono);
			model.addAttribute("messageView","Se agrego con exito el abono por: "+abono.getCantidadAbono());
			this.getModelAttributtes(model);
			return "cobranza";
			}else {			
				model.addAttribute("messageError", "ERROR. No se encontro session, porfavor recarga la pagina y logueate correctamente ");
				return "cobranza";		
			}
			
		}
		
	
	public Model getModelAttributtes(Model model) {		
			model.addAttribute("tiposAbonos", saleNoteService.obtenerTiposAbono());
			model.addAttribute("listStatus", saleNoteService.getSalesStatus());	
		return model;
	}

}
