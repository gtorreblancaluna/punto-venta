package mx.com.proyect.puntoventa.web.forms;

/** @author Gerardo Torreblanca Luna
 *  POJO para almacenar los parametros y aplicar el filtro en el query 
 * */
public class SaleNoteFilter {

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
