package mx.com.proyect.puntoventa.web.model;
/**
 * POJO para alamacenar valores de estatus de venta
 * GTL 2018.07.27 
 * 
 * */
public class SaleStatusDTO {
	
	private int statusId;
	private String description;
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
