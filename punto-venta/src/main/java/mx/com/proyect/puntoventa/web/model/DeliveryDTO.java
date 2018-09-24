package mx.com.proyect.puntoventa.web.model;

/**
 * @author Gerardo Torreblanca
 * 2018.06.14 
 * POJO que almacena informacion de una entrega
 * @table c_entrega
 * 
 * 
 * */

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class DeliveryDTO implements Serializable {	
	
	private static final long serialVersionUID = 414296597983426254L;
	
	// cl_entrega
	private Integer deliveryId;
	// fe_registro
	private Timestamp dateRegister;
	//  fe_entrega
	private Timestamp dateDelivery;
	// cl_usuario
	private AccountDTO account;
	// cl_sucursal
	private OfficeDTO office;
	// cl_estatus_entrega
	private String status;
	// ds_descripcion
	private String description;
	// este valor lo utilizamos solo para recibir la fecha desde el JSP
	private String formatedDateDelivery;
	// detalle de la entrega
	private List<DeliveryDetailDTO> details;
	// cl_almacen
	private StoreDTO store;
	
	
	
	public StoreDTO getStore() {
		return store;
	}
	public void setStore(StoreDTO store) {
		this.store = store;
	}
	public List<DeliveryDetailDTO> getDetails() {
		return details;
	}
	public void setDetails(List<DeliveryDetailDTO> details) {
		this.details = details;
	}
	public Integer getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}
	public Timestamp getDateRegister() {
		return dateRegister;
	}
	public void setDateRegister(Timestamp dateRegister) {
		this.dateRegister = dateRegister;
	}
	public Timestamp getDateDelivery() {
		return dateDelivery;
	}
	public void setDateDelivery(Timestamp dateDelivery) {
		this.dateDelivery = dateDelivery;
	}
	
	public AccountDTO getAccount() {
		return account;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	public OfficeDTO getOffice() {
		return office;
	}
	public void setOffice(OfficeDTO office) {
		this.office = office;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFormatedDateDelivery() {
		return formatedDateDelivery;
	}
	public void setFormatedDateDelivery(String formatedDateDelivery) {
		this.formatedDateDelivery = formatedDateDelivery;
	}
	
	
	
	

}
