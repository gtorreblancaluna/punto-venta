package mx.com.proyect.puntoventa.web.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.ColorDTO;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.StoreDTO;
import mx.com.proyect.puntoventa.web.service.ColorService;
import mx.com.proyect.puntoventa.web.service.InventoryService;
import mx.com.proyect.puntoventa.web.service.OfficeService;

@Controller
public class HandleInventoryController {

	// inyectar dependencias de spring
		@Autowired
		private InventoryService inventoryService;
		@Autowired
		private ColorService colorService;
		@Autowired
		private OfficeService officeService;

		// mostrar la vista principal
		@RequestMapping(value = "/handleInventory.do", method = RequestMethod.GET)
		public String showhandleInventory(HttpServletRequest request, Model model) {
//			List<ItemDTO> listItems = inventoryService.getAll();
//			model.addAttribute("listItems", listItems);	
			
			// traer la lista de almacenes
			List<StoreDTO> listStore = inventoryService.getAllStore();
			model.addAttribute("listStore", listStore);	
			List<ColorDTO> listColors = colorService.getAll();
			model.addAttribute("listColors", listColors);
			model.addAttribute("listOffices", officeService.getAll());
			
			model.addAttribute("item", new ItemDTO());
			return "handleInventory";
		}

		// agregar 
		@RequestMapping(value = "/handleInventory.do", params = "add")
		public String add(HttpServletRequest request, @ModelAttribute ItemDTO item, Model model) {
			inventoryService.add(item);			
			List<ItemDTO> listItems = inventoryService.getAll();
			model.addAttribute("listItems", listItems);
			model.addAttribute("item", new ItemDTO());
			model.addAttribute("message", "Exito al registrar el producto: " + item.getDescription());
			List<ColorDTO> listColors = colorService.getAll();
			model.addAttribute("listColors", listColors);
			model.addAttribute("listOffices", officeService.getAll());
			return "handleInventory";
		}

		// actualizar 
		@RequestMapping(value = "/handleInventory.do", params = "update")
		public String update(HttpServletRequest request, @ModelAttribute ItemDTO item, Model model) {
			inventoryService.update(item);		
			List<ItemDTO> listItems = inventoryService.getAll();
			model.addAttribute("listItems", listItems);
			// traer la lista de almacenes
			List<StoreDTO> listStore = inventoryService.getAllStore();
			model.addAttribute("listStore", listStore);	
			List<ColorDTO> listColors = colorService.getAll();
			model.addAttribute("listColors", listColors);
			model.addAttribute("listOffices", officeService.getAll());
			model.addAttribute("message", "Exito al actualizar el producto: " + item.getDescription());
			return "handleInventory";
		}

		// eliminar 
		@SuppressWarnings("unused")
		@RequestMapping(value = "/handleInventory.do", params = "delete")
		public String delete(HttpServletRequest request, @RequestParam("itemId") String itemId, Model model) {
			ItemDTO item = null;
			String message=null;
			String messageError=null;
			
			if (itemId == null || itemId.equals("") ) {
				messageError = "No se recibio el parametro, por favor recarga la pagina";			
			}else {
				item = inventoryService.findById(new Long (itemId));
				if(item==null) {
					messageError= "No se encontro el producto con id: "+itemId;				
				}else {
					inventoryService.delete(item);				
					message= "Exito al eliminar el producto: " + item.getDescription();				
				}
			}
			
			if(message!=null)
				model.addAttribute("message",message);
			
			if(messageError!=null)
				model.addAttribute("messageError",messageError);

//			List<ItemDTO> listItem = inventoryService.getAll();
//			model.addAttribute("listItem", listItem);
			// traer la lista de almacenes
			List<StoreDTO> listStore = inventoryService.getAllStore();
			model.addAttribute("listStore", listStore);	
			List<ColorDTO> listColors = colorService.getAll();
			model.addAttribute("listColors", listColors);
			// sucursales
			model.addAttribute("listOffices", officeService.getAll());
						
			return "handleInventory";
		}
		
		@PostMapping(value = "handleInventory.do", params = "filter")
		public String getSaleNoteByFilter(HttpServletRequest request, 
				@ModelAttribute ("saleNoteFilter") SaleNoteFilter saleNoteFilter, Model model) {
			
			model.addAttribute("listItems", inventoryService.getItemsByFilter(saleNoteFilter));
			model.addAttribute("listStore", inventoryService.getAllStore());			
			model.addAttribute("listColors", colorService.getAll());
			// sucursales
			model.addAttribute("listOffices", officeService.getAll());
			return "handleInventory";
		}
	
}
