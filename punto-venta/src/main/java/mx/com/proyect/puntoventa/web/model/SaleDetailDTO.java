package mx.com.proyect.puntoventa.web.model;

import java.io.Serializable;

/* GTL 2018.06.05
 * 
 * POJO para almacenar el detalle de la venta, tabla -> k_detalle_venta
 * 
 * */

public class SaleDetailDTO implements Serializable {	
	
	private static final long serialVersionUID = 413296597983426299L;
	// cl_detalle_venta -> id de la tabla
	private Integer saleDetailId;
	// cl_venta -> id de la venta
	private Integer saleId;
	// cl_articulo -> id del articulo
	private ItemDTO item;
	// cantidad -> cantidad de articulos en la tabla
	private Float amount;
	// cl_color -> id color
	private ColorDTO color;
	
	public Integer getSaleDetailId() {
		return saleDetailId;
	}
	public void setSaleDetailId(Integer saleDetailId) {
		this.saleDetailId = saleDetailId;
	}
	public Integer getSaleId() {
		return saleId;
	}
	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}
	public ItemDTO getItem() {
		return item;
	}
	public void setItem(ItemDTO item) {
		this.item = item;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public ColorDTO getColor() {
		return color;
	}
	public void setColor(ColorDTO color) {
		this.color = color;
	}
	
	
	
	
	
	
}
