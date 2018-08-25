package mx.com.proyect.puntoventa.web.forms;


import java.sql.Timestamp;
import java.util.List;

import mx.com.proyect.puntoventa.web.model.AbonoDTO;
import mx.com.proyect.puntoventa.web.model.AccountDTOclient;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
import mx.com.proyect.puntoventa.web.model.SaleStatusDTO;
/* GTL 2018.05.21 
 * POJO para llenar en la vista de NOTA DE VENTA
 * 
 * */ 
public class SaleNoteForm {
	
	private int saleId;
	private String userId;
	// id de la sucursal
	private String storeId;
	private String sellerId;
	private String dateSaleNote;
	private String fechaVencimientoCredito;
	// almacenara el detalle de la venta para mostrar en la vista
	private List<SaleDetailDTO> saleDetail;
	private List<ItemDTO> items;
	// fecha de registro
	private Timestamp dateTimestamp;
	// fecha de entrega
	private Timestamp dateDelivery;
	private String description;
	// estatus de venta
	private SaleStatusDTO status;	
	// desea imprimir la nota despues de agregar la venta
	private boolean printSaleNote;
	//2018.08.25 sera a credito
	private boolean credit;
	// pojo abono
	private AbonoDTO abono;	
	// lista abonos
	private List<AbonoDTO> abonos;	
	
	public List<AbonoDTO> getAbonos() {
		return abonos;
	}
	public void setAbonos(List<AbonoDTO> abonos) {
		this.abonos = abonos;
	}
	public String getFechaVencimientoCredito() {
		return fechaVencimientoCredito;
	}
	public void setFechaVencimientoCredito(String fechaVencimientoCredito) {
		this.fechaVencimientoCredito = fechaVencimientoCredito;
	}
	public boolean isCredit() {
		return credit;
	}
	public void setCredit(boolean credit) {
		this.credit = credit;
	}		
	public AbonoDTO getAbono() {
		return abono;
	}
	public void setAbono(AbonoDTO abono) {
		this.abono = abono;
	}
	private AccountDTOclient customer;	

	public AccountDTOclient getCustomer() {
		return customer;
	}
	public void setCustomer(AccountDTOclient customer) {
		this.customer = customer;
	}
	public SaleStatusDTO getStatus() {
		return status;
	}
	public void setStatus(SaleStatusDTO status) {
		this.status = status;
	}
	public boolean isPrintSaleNote() {
		return printSaleNote;
	}

	public void setPrintSaleNote(boolean printSaleNote) {
		this.printSaleNote = printSaleNote;
	}	

	public List<SaleDetailDTO> getSaleDetail() {
		return saleDetail;
	}

	public void setSaleDetail(List<SaleDetailDTO> saleDetail) {
		this.saleDetail = saleDetail;
	}

	public Timestamp getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(Timestamp dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Timestamp getDateTimestamp() {
		return dateTimestamp;
	}

	public void setDateTimestamp(Timestamp dateTimestamp) {
		this.dateTimestamp = dateTimestamp;
	}

	public String getDateSaleNote() {
		return dateSaleNote;
	}

	public void setDateSaleNote(String dateSaleNote) {
		this.dateSaleNote = dateSaleNote;	
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}
	

}
