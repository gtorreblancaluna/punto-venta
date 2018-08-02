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
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.OfficeService;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

/**
 * GTL - 2018.08.02
 * Controlador para generar reportes de ventas
 * 
 * */
@Controller
public class SalesReportsController {
	
	@Autowired
	private SaleNoteService saleNoteService;
	@Autowired
	private OfficeService officeService;
	
		// vista principal
		@GetMapping(value = "salesReports.do")
		public String getIniMapping( HttpServletRequest request,HttpServletResponse response, Model model) {		
							
			this.getModelAttributtes(model);
			return "salesReportsView";
		}
		
		@PostMapping(value = "salesReports.do", params = "filter")
		public String getSaleNoteByFilter(HttpServletRequest request, 
				@ModelAttribute ("saleNoteFilter") SaleNoteFilter saleNoteFilter, Model model) {
			
			List<ResultQuerySaleNote> listSaleNoteByFilter = saleNoteService.getByFilter(saleNoteFilter);			
			//obtenemos el resultado y enviamos al JSP
			model.addAttribute("listSaleNoteByFilter", listSaleNoteByFilter);
			model.addAttribute("messageSucess","Total de registros encontrados: "+listSaleNoteByFilter.size());
			this.getModelAttributtes(model);
			return "salesReportsView";
		}
		
		// obtener lo necesario para enviar al JSP
		public Model getModelAttributtes(Model model) {
			model.addAttribute("listOffices", officeService.getAll());
			model.addAttribute("listStatus", saleNoteService.getSalesStatus());
			model.addAttribute("saleNoteFilter", new SaleNoteFilter());	
			return model;
		}

}
