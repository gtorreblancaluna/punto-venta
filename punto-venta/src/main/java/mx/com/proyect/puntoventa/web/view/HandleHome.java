package mx.com.proyect.puntoventa.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQueryItemsSold;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

import org.springframework.ui.Model;


@Controller
public class HandleHome {	
	
	@Autowired
	SaleNoteService saleNoteService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String showBienvenida(HttpServletRequest request, Model model) {
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
//		Map<String,Object> result = saleNoteService.getItemsSold();
//		List<ResultQueryItemsSold> itemsByDay = (List<ResultQueryItemsSold>) result.get("itemsByDay");
//		List<ResultQueryItemsSold> itemsByWeek = (List<ResultQueryItemsSold>) result.get("itemsByWeek");
//		List<ResultQueryItemsSold> itemsByMonth = (List<ResultQueryItemsSold>) result.get("itemsByMonth");
		
//		model.addAttribute("itemsByDay", itemsByDay);
//		model.addAttribute("itemsByWeek", itemsByWeek);
//		model.addAttribute("itemsByMonth", itemsByMonth);
		
		return"bienvenida";
	}
}
