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
	private Integer itemId;
	// cantidad -> cantidad de articulos en la tabla
	private Float amount;
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
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
