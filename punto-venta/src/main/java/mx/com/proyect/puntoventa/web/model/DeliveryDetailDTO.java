package mx.com.proyect.puntoventa.web.model;

/**
 * @author Gerardo Torreblanca
 * 2018.06.14 
 * POJO que almacena informacion detalle de entrega
 * @table k_detalle_entrega
 * 
 * 
 * */

import java.io.Serializable;
import java.sql.Timestamp;

public class DeliveryDetailDTO implements Serializable {	
	
	private static final long serialVersionUID = 414296597983426254L;
	
	// cl_detalle_entrega
	private Integer deliveryDetailId;
	// cl_entrega - id de la tabla c_entrega
	private Integer deliveryId;
	// cl_articulo
	private ItemDTO item;
	// cl_color
	private ColorDTO color;
	// cantidad
	private Float amount;
	public Integer getDeliveryDetailId() {
		return deliveryDetailId;
	}
	public void setDeliveryDetailId(Integer deliveryDetailId) {
		this.deliveryDetailId = deliveryDetailId;
	}
	public Integer getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}
	public ItemDTO getItem() {
		return item;
	}
	public void setItem(ItemDTO item) {
		this.item = item;
	}
	public ColorDTO getColor() {
		return color;
	}
	public void setColor(ColorDTO color) {
		this.color = color;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
		

}
