package mx.com.proyect.puntoventa.web.resultsQuerys;

import java.sql.Timestamp;

import mx.com.proyect.puntoventa.web.model.SaleStatusDTO;

/** GTL 2018.06.05
 *  POJO que contendra los resultados del query para las notas
 * 
 * */

public class ResultQuerySaleNote {
	
	// cl_venta - id de la venta
	private Integer saleId;
	// fe_registro - fecha en la que se registro el pedido
	private Timestamp saleDate;
	// fe_entrega - fecha en la que se entragara el pedido
	private Timestamp dateDelivery;
	// ds_descripcion - descripcion de la nota
	private String description;
	// nombre_cliente
	private String nameCustomer;
	// nombre_sucursal
	private String nameOffice;
	// nombre_usuario
	private String nameUser;
	// status
	private SaleStatusDTO status;	
	
	public SaleStatusDTO getStatus() {
		return status;
	}
	public void setStatus(SaleStatusDTO status) {
		this.status = status;
	}
	public Integer getSaleId() {
		return saleId;
	}
	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}
	public Timestamp getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Timestamp saleDate) {
		this.saleDate = saleDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNameCustomer() {
		return nameCustomer;
	}
	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}
	public String getNameOffice() {
		return nameOffice;
	}
	public void setNameOffice(String nameOffice) {
		this.nameOffice = nameOffice;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public Timestamp getDateDelivery() {
		return dateDelivery;
	}
	public void setDateDelivery(Timestamp dateDelivery) {
		this.dateDelivery = dateDelivery;
	}
	
	
	

}
