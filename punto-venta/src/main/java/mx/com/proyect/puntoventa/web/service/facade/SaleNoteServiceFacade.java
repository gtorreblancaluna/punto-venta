package mx.com.proyect.puntoventa.web.service.facade;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.service.InventoryService;

@Controller
public class SaleNoteServiceFacade {
	
	@Autowired
	InventoryService inventoryService;
	
	@RequestMapping(value = "/getItemById.do")
	@ResponseBody
	public String getItemById(@RequestBody String id) {
		ItemDTO item = inventoryService.findById(new Long (id));
		
		Map<String,Object> myMap = new HashMap<>();
	        myMap.put("itemId", item.getItemId());
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
