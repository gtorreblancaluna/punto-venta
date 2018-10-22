package mx.com.proyect.puntoventa.web.service.facade;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.proyect.puntoventa.web.forms.CustomerFilter;
import mx.com.proyect.puntoventa.web.forms.SaleForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.AccountDTOclient;
import mx.com.proyect.puntoventa.web.model.ColorDTO;
import mx.com.proyect.puntoventa.web.model.DeliveryDTO;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
import mx.com.proyect.puntoventa.web.model.StoreDTO;
import mx.com.proyect.puntoventa.web.service.ClientService;
import mx.com.proyect.puntoventa.web.service.ColorService;
import mx.com.proyect.puntoventa.web.service.InventoryService;
import mx.com.proyect.puntoventa.web.service.ProviderService;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

@Controller
public class SaleNoteServiceFacade {
	
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private SaleNoteService saleNoteService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ProviderService providerService;
	
	
	@RequestMapping(value = "/getItemById.do")
	@ResponseBody
	public String getItemById(@RequestBody String id) {
			ItemDTO item = inventoryService.findById(new Long (id));
		
		
			Map<String,Object> myMap = new HashMap<>();
			
			if(item == null) {
				 myMap.put("itemId", 0);
			}else {
				 myMap.put("itemId", item.getItemId());
			}	       
	        ObjectMapper mapper = new ObjectMapper();
	        String json = "";
	        try {
	            json = mapper.writeValueAsString(myMap);
	        } catch (JsonProcessingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	       
		
		return json;

	}
	
		
	// obtener datos generales de la venta
	@RequestMapping(value = "/getSaleNoteById.do")
	@ResponseBody
//	public SaleNoteForm getSaleNoteById(@RequestBody String id) {	
		public String getSaleNoteById(@RequestBody String id) {
		
		SaleNoteForm note = saleNoteService.getSaleNoteById(new Integer(id));			
//		return note;	
		
		Map<String,Object> myMap = new HashMap<>();
		
		
		myMap.put("noteForm", note);		
			
			       
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(myMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	       
	
        return json;

	}
	
	
	@RequestMapping(value = "/addColor.do")
	@ResponseBody
		public String addColor(@RequestBody String description) {
		ColorDTO color = new ColorDTO();
		color.setDescription(description);
		color = colorService.add(color);		
		List<ColorDTO> colors = colorService.getAll();
		Map<String,Object> myMap = new HashMap<>();		
		myMap.put("message", "Se agrego con \u00E9xito el color: "+color.getDescription());	
		myMap.put("color", color);	
		myMap.put("colors", colors);	
			       
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(myMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	       
	
        return json;

	}
	
	/**
	 * 2018.08.01 GTL
	 * Metodo para cambiar el estatus a la venta	 * 
	 * */
	@RequestMapping(value = "/changeStatus.do")
	@ResponseBody
	public Map<String,Object> changeStatus(@RequestBody SaleForm saleNoteForm) {
		Map<String,Object> respuesta = new HashMap<>();
//		SaleNoteForm note = saleNoteService.getSaleNoteById(saleNoteForm.getSaleId());
//		
//		if(note == null) {
//			respuesta.put("success", false);
//			respuesta.put("messate", "No se encontro la operacion id: "+saleNoteForm.getSaleId());
//			return respuesta;
//		}
//		saleNoteService.changeStatus(saleNoteForm.getSaleId(),saleNoteForm.getStatus().getStatusId());
//		
		
		return null;
	}
	
	
	@RequestMapping(value = "/obtenerClientes.do")
	@ResponseBody
		public String obtenerClientes(@RequestBody String valor) {
		CustomerFilter customerFilter = new CustomerFilter();
		customerFilter.setFirstName(valor);
		List<AccountDTOclient> clientes = clientService.getCustomerByFilter(customerFilter);
		
		Map<String,Object> myMap = new HashMap<>();		
		
		myMap.put("clientes", clientes);	
			       
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(myMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	       
	
        return json;

	}
	
	
	@RequestMapping(value = "/eliminarAbono.do")
	@ResponseBody
		public String eliminarAbono(@RequestBody String abonoId) {
		
		saleNoteService.eliminarAbono(new Integer(abonoId));
		
		Map<String,Object> myMap = new HashMap<>();		
		myMap.put("mensaje", "Se elimino con \u00E9xito el abono");	
		ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(myMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	       
	
        return json;

	}
	
	@RequestMapping(value = "/obtenerAlmacenesPorSucursal.do")
	@ResponseBody
		public String obtenerAlmacenesPorSucursal(@RequestBody String sucursalId) {
		
		List<StoreDTO> almacenes = saleNoteService.obtenerAlmacenesPorSucursal(new Integer(sucursalId));
		
		Map<String,Object> myMap = new HashMap<>();		
		myMap.put("almacenes", almacenes);
		ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(myMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	       
	
        return json;

	}
	
	@RequestMapping(value = "/buscarArticulosPorDescripcion.do")
	@ResponseBody
		public String buscarArticulosPorDescripcion(@RequestBody String valor) {
		String[] parametros = valor.split("-");
		
		
		SaleNoteFilter filtro = new SaleNoteFilter();
		filtro.setDescriptionFilter(parametros[0]);
		filtro.setOfficeIdFilter(parametros[1]);
		filtro.setStoreIdFilter(parametros[2]);
		List<ItemDTO> articulos = inventoryService.getItemsByFilter(filtro);
		
		Map<String,Object> myMap = new HashMap<>();		
		myMap.put("articulos", articulos);
		ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(myMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	       
	
        return json;

	}
	
	// obtener datos generales de la venta
		@RequestMapping(value = "/obtenerEntregaPorId.do")
		@ResponseBody
//		public SaleNoteForm getSaleNoteById(@RequestBody String id) {	
			public String obtenerEntregaPorId(@RequestBody String id) {
			
			DeliveryDTO entrega = providerService.getDeliveryById(new Integer(id));			
//			return note;	
			
			Map<String,Object> myMap = new HashMap<>();
			
			
			myMap.put("entrega", entrega);		
				
				       
	        ObjectMapper mapper = new ObjectMapper();
	        String json = "";
	        try {
	            json = mapper.writeValueAsString(myMap);
	        } catch (JsonProcessingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	       
		
	        return json;

		}


}
