package mx.com.proyect.puntoventa.web.model;
// 2018.05.18 GTL, POJO ALMACEN

public class StoreDTO {
	// id almacen
	private Integer storeId;
	// descripcion
	private String description;
	// estatus
	private String fgStatus;
	
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFgStatus() {
		return fgStatus;
	}
	public void setFgStatus(String fgStatus) {
		this.fgStatus = fgStatus;
	}
	
	
}
