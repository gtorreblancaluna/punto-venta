package mx.com.proyect.puntoventa.web.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItemDTO implements Serializable {	
	
	private static final long serialVersionUID = 413296597983426254L;
	// id producto
	private String itemId;
	// fecha de alta
	private Timestamp date;
	// pojo almacen
	private StoreDTO storeDTO;
	// descripcion
	private String description;
	//unidad de medida
	private String unitMeasurement;
	// cantidad entrada
	private Float amountEntry;
	// cantidad de salida
	private Float amountOutput;
	// precio de venta
	private Float salePrice;
	// cantidad existente
	private Float stock;
	// bandera estatus
	private String fgStatus;
	// objeto color
	private ColorDTO color;
	
	
	
	public ColorDTO getColor() {
		return color;
	}
	public void setColor(ColorDTO color) {
		this.color = color;
	}
	public StoreDTO getStoreDTO() {
		return storeDTO;
	}
	public void setStoreDTO(StoreDTO storeDTO) {
		this.storeDTO = storeDTO;
	}
	public Float getStock() {
		return stock;
	}
	public void setStock(Float stock) {
		this.stock = stock;
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnitMeasurement() {
		return unitMeasurement;
	}
	public void setUnitMeasurement(String unitMeasurement) {
		this.unitMeasurement = unitMeasurement;
	}
	public Float getAmountEntry() {
		return amountEntry;
	}
	public void setAmountEntry(Float amountEntry) {
		this.amountEntry = amountEntry;
	}
	public Float getAmountOutput() {
		return amountOutput;
	}
	public void setAmountOutput(Float amountOutput) {
		this.amountOutput = amountOutput;
	}
	public Float getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}
	
	public String getFgStatus() {
		return fgStatus;
	}
	public void setFgStatus(String fgStatus) {
		this.fgStatus = fgStatus;
	}
	
	
	
	
}
