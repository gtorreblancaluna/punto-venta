package mx.com.proyect.puntoventa.web.model;
// 2018.05.18 GTL, POJO ALMACEN

public class WarehouseDTO {
	// id almacen
	private Integer warehouseId;
	// descripcion
	private String description;
	// estatus
	private String fgStatus;
	
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
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
