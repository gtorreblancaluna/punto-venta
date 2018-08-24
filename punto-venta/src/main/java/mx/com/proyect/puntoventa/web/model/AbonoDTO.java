package mx.com.proyect.puntoventa.web.model;

public class AbonoDTO {
	
	private int abonoId;
	private SaleNoteDTO saleNote;
	private TipoAbonoDTO tipoAbono;
	private float cantidadAbono;
	private String descripcion;
	private String estado;
	private AccountDTO usuario;	
	
	public AccountDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(AccountDTO usuario) {
		this.usuario = usuario;
	}
	public int getAbonoId() {
		return abonoId;
	}
	public void setAbonoId(int abonoId) {
		this.abonoId = abonoId;
	}
	public SaleNoteDTO getSaleNote() {
		return saleNote;
	}
	public void setSaleNote(SaleNoteDTO saleNote) {
		this.saleNote = saleNote;
	}
	public TipoAbonoDTO getTipoAbono() {
		return tipoAbono;
	}
	public void setTipoAbono(TipoAbonoDTO tipoAbono) {
		this.tipoAbono = tipoAbono;
	}
	public float getCantidadAbono() {
		return cantidadAbono;
	}
	public void setCantidadAbono(float cantidadAbono) {
		this.cantidadAbono = cantidadAbono;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
