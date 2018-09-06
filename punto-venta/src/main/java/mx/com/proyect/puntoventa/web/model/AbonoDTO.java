package mx.com.proyect.puntoventa.web.model;

import static mx.com.proyect.puntoventa.web.commons.ApplicationConstants.MASK_DATE_FORMAT;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AbonoDTO {
	private static final Locale locale = new Locale( "es", "MX" );
	private static final SimpleDateFormat formatoFecha = new SimpleDateFormat( MASK_DATE_FORMAT, locale );
	
	private int abonoId;
	private SaleNoteDTO saleNote;
	private TipoAbonoDTO tipoAbono;
	private float cantidadAbono;
	private String descripcion;
	private String estado;
	private AccountDTO usuario;	
	private Timestamp feRegistro;
	private String feRegistroFormat;	
	private AccountDTOclient cliente;	
	
	public AccountDTOclient getCliente() {
		return cliente;
	}
	public void setCliente(AccountDTOclient cliente) {
		this.cliente = cliente;
	}
	public String getFeRegistroFormat() {
		return feRegistroFormat;
	}
	public void setFeRegistroFormat(String feRegistroFormat) {
		this.feRegistroFormat = feRegistroFormat;
	}
	public Timestamp getFeRegistro() {
		return feRegistro;
	}
	public void setFeRegistro(Timestamp feRegistro) {
		this.feRegistro = feRegistro;
		this.feRegistroFormat = formatoFecha.format(feRegistro);
	}
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
