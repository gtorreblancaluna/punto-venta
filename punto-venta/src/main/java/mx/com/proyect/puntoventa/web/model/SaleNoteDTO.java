package mx.com.proyect.puntoventa.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 2018.06.08
 * 
 * GTL contendra los valores de la tabla c_venta
 * */

public class SaleNoteDTO implements Serializable {	
	
	private static final long serialVersionUID = 413296597983426259L;

	// id venta
	private String saleId;
	// fecha de registr
	private Timestamp registerDate;
	// fecha de entrega
	private Timestamp deliveryDate;
	// datos cliente
	private AccountDTOclient client;
	// datos del usuario
	private AccountDTO account;
	// datos de la sucursal
	private StoreDTO store;
	// descripcion
	private String description;
	// status
	private SaleStatusDTO status;
	// detalle de la venta	
	private List<SaleDetailDTO> saleDetails;
	
	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public Timestamp getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public AccountDTOclient getClient() {
		return client;
	}

	public void setClient(AccountDTOclient client) {
		this.client = client;
	}

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public StoreDTO getStore() {
		return store;
	}

	public void setStore(StoreDTO store) {
		this.store = store;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SaleDetailDTO> getSaleDetails() {
		return saleDetails;
	}

	public void setSaleDetails(List<SaleDetailDTO> saleDetails) {
		this.saleDetails = saleDetails;
	}

	public SaleStatusDTO getStatus() {
		return status;
	}

	public void setStatus(SaleStatusDTO status) {
		this.status = status;
	}
	
	
	
}
