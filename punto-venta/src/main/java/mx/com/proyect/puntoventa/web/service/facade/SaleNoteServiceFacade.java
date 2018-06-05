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

import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
import mx.com.proyect.puntoventa.web.service.InventoryService;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

@Controller
public class SaleNoteServiceFacade {
	
	@Autowired
	InventoryService inventoryService;
	@Autowired
	SaleNoteService saleNoteService;
	
	@RequestMapping(value = "/getItemById.do")
//	@ResponseBody
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
	
	@RequestMapping(value = "/getSaleNoteById.do")
	@ResponseBody
	public String getSaleNoteById(@RequestBody String id) {		
		
			SaleNoteForm note = saleNoteService.getSaleNoteById(new Integer(id));
			
			Map<String,Object> myMap = new HashMap<>();
			
			if(note == null) {
				 myMap.put("saleId", 0);
			}else {
				 myMap.put("saleId", note.getSaleId());
				 myMap.put("dateTimestamp", note.getDateTimestamp());
				 myMap.put("dateDelivery", note.getDateDelivery());
				 myMap.put("sellerId", note.getSellerId());
				 myMap.put("userId", note.getUserId());
				 myMap.put("storeId", note.getStoreId());
				 myMap.put("description", note.getDescription());
				 int cont=0;
				 for(SaleDetailDTO detail : note.getSaleDetail()) {					 
					 myMap.put("detail-saleDetailId-"+cont, detail.getSaleDetailId());
					 myMap.put("detail-saleId-"+cont, detail.getSaleId());
					 myMap.put("detail-itemId-"+cont, detail.getItemId());
					 myMap.put("detail-amount-"+cont, detail.getAmount());
					 cont++;
				 }
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

}
