package mx.com.proyect.puntoventa.web.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mx.com.proyect.puntoventa.web.forms.FiltroAbonos;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.AbonoDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.OfficeService;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

/**
 * GTL - 2018.08.02
 * Controlador para generar reportes de ventas
 * 
 * */
@Controller
public class ReporteAbonosController {
	
	@Autowired
	private SaleNoteService saleNoteService;
		
		// vista principal
		@GetMapping(value = "reporteAbonos.do")
		public String principal( HttpServletRequest request,HttpServletResponse response, Model model) {		
							
			this.getModelAttributtes(model);
			return "reporteAbonos";
		}
		
		@PostMapping(value = "reporteAbonos.do", params = "filter")
		public String getSaleNoteByFilter(HttpServletRequest request, 
				@ModelAttribute ("filtroAbonos") FiltroAbonos filtroAbonos, Model model) {
			
			List<AbonoDTO> abonos = saleNoteService.obtenerAbonosPorFiltro(filtroAbonos);			
			//obtenemos el resultado y enviamos al JSP
			model.addAttribute("abonos", abonos);
			model.addAttribute("messageSucess","Total de registros encontrados: "+abonos.size());
			this.getModelAttributtes(model);
			return "reporteAbonos";
		}
		
		// obtener lo necesario para enviar al JSP
		public Model getModelAttributtes(Model model) {
			model.addAttribute("tipoAbonos", saleNoteService.obtenerTiposAbono());
			return model;
		}

}
