package mx.com.proyect.puntoventa.web.forms;

/** @author Gerardo Torreblanca Luna
 *  POJO para almacenar los parametros y aplicar el filtro en el query 
 * */
public class SaleNoteFilter {

	// folio - id nota
	private String saleIdFilter;
	// fecha inicial
	private String iniDateFilter;
	// fecha final
	private String endDateFilter;
	// descripcion
	private String descriptionFilter;
	// estatus
	private String statusFilter;
	// id sucursal
	private String officeIdFilter;
	// nombre del cliente
	private String customerNameFilter;
	// id usuario
	private String userId;	
	// id color
	private String colorIdFilter;
	// id almacen
	private String storeIdFilter;
	// es a credito
	private String credit;
	// id almacen
	private int tipoAbonoId;		
	
	public int getTipoAbonoId() {
		return tipoAbonoId;
	}
	public void setTipoAbonoId(int tipoAbonoId) {
		this.tipoAbonoId = tipoAbonoId;
	}	
	
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getSaleIdFilter() {
		return saleIdFilter;
	}
	public void setSaleIdFilter(String saleIdFilter) {
		this.saleIdFilter = saleIdFilter;
	}
	public String getStoreIdFilter() {
		return storeIdFilter;
	}
	public void setStoreIdFilter(String storeIdFilter) {
		this.storeIdFilter = storeIdFilter;
	}
	public String getColorIdFilter() {
		return colorIdFilter;
	}
	public void setColorIdFilter(String colorIdFilter) {
		this.colorIdFilter = colorIdFilter;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIniDateFilter() {
		return iniDateFilter;
	}
	public void setIniDateFilter(String iniDateFilter) {
		this.iniDateFilter = iniDateFilter;
	}
	public String getEndDateFilter() {
		return endDateFilter;
	}
	public void setEndDateFilter(String endDateFilter) {
		this.endDateFilter = endDateFilter;
	}
	public String getDescriptionFilter() {
		return descriptionFilter;
	}
	public void setDescriptionFilter(String descriptionFilter) {
		this.descriptionFilter = descriptionFilter;
	}
	public String getStatusFilter() {
		return statusFilter;
	}
	public void setStatusFilter(String statusFilter) {
		this.statusFilter = statusFilter;
	}
	public String getCustomerNameFilter() {
		return customerNameFilter;
	}
	public void setCustomerNameFilter(String customerNameFilter) {
		this.customerNameFilter = customerNameFilter;
	}
	public String getOfficeIdFilter() {
		return officeIdFilter;
	}
	public void setOfficeIdFilter(String officeIdFilter) {
		this.officeIdFilter = officeIdFilter;
	}

	
	
	
	
	
	
	
}
