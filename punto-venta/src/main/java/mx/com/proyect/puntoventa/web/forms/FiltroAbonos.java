package mx.com.proyect.puntoventa.web.forms;

/** @author Gerardo Torreblanca Luna
 *  POJO para almacenar los parametros y aplicar el filtro en el query 
 * */
public class FiltroAbonos {
	
	private int abonoId;
	private String fechaInicial;
	private String fechaFinal;
	private int tipoAbonoId;
	private String nombreCliente;
	// estatus
	private String statusFilter;
	
	public String getStatusFilter() {
		return statusFilter;
	}
	public void setStatusFilter(String statusFilter) {
		this.statusFilter = statusFilter;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public int getAbonoId() {
		return abonoId;
	}
	public void setAbonoId(int abonoId) {
		this.abonoId = abonoId;
	}
	public String getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public int getTipoAbonoId() {
		return tipoAbonoId;
	}
	public void setTipoAbonoId(int tipoAbonoId) {
		this.tipoAbonoId = tipoAbonoId;
	}
	
	
	
}
