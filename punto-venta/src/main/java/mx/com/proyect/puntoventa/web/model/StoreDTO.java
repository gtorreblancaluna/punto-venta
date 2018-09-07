package mx.com.proyect.puntoventa.web.model;
// 2018.05.18 GTL, POJO ALMACEN

import java.io.Serializable;

public class StoreDTO implements Serializable {	
	
	private static final long serialVersionUID = 413296597983426256L;
	// id almacen
	private Integer storeId;
	// descripcion
	private String description;
	// estatus
	private String fgStatus;
	// sucursal
	private OfficeDTO sucursal;
	
	public OfficeDTO getSucursal() {
		return sucursal;
	}
	public void setSucursal(OfficeDTO sucursal) {
		this.sucursal = sucursal;
	}
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
