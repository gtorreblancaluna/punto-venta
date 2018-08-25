package mx.com.proyect.puntoventa.web.resultsQuerys;

import java.sql.Timestamp;
import java.util.List;

import mx.com.proyect.puntoventa.web.model.AbonoDTO;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
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
	// total de la venta
	private Float totalAmount;
	// status
	private SaleStatusDTO status;
	// abonos 
	private List<AbonoDTO> abonos;
	
	/** datos para mostrar en el modulo de cobranza */
	// fecha vencimiento
	private Timestamp fechaVencimientoCredito;
	private float totalAbonos;	
	private float resta;
	// detalle de la venta
	private List<SaleDetailDTO> saleDetail;	
	
	
	
	public float getTotalAbonos() {
		return totalAbonos;
	}
	public void setTotalAbonos(float totalAbonos) {
		this.totalAbonos = totalAbonos;
	}
	public float getResta() {
		return resta;
	}
	public void setResta(float resta) {
		this.resta = resta;
	}
	
	public Timestamp getFechaVencimientoCredito() {
		return fechaVencimientoCredito;
	}
	public void setFechaVencimientoCredito(Timestamp fechaVencimientoCredito) {
		this.fechaVencimientoCredito = fechaVencimientoCredito;
	}
	public List<AbonoDTO> getAbonos() {
		return abonos;
	}
	public void setAbonos(List<AbonoDTO> abonos) {
		this.abonos = abonos;
	}
	
	public List<SaleDetailDTO> getSaleDetail() {
		return saleDetail;
	}
	public void setSaleDetail(List<SaleDetailDTO> saleDetail) {
		this.saleDetail = saleDetail;
	}
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
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
