package mx.com.proyect.puntoventa.web.forms;

import java.util.List;

import mx.com.proyect.puntoventa.web.model.ItemDTO;
/* GTL 2018.05.21 
 * POJO para llenar en la vista de NOTA DE VENTA
 * 
 * */ 
public class SaleNoteForm {
	
	private String userId;
	private List<ItemDTO> items;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

}
