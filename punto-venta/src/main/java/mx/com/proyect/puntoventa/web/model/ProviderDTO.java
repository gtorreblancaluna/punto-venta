package mx.com.proyect.puntoventa.web.model;

/**
 * @author Gerardo Torreblanca
 * 2018.06.14
 * POJO que contendra resultados del query a la tabla c_proveedor
 * Almacena datos de los proveedores
 * */

import java.sql.Timestamp;

public class ProviderDTO {
	
	private int providerId;
	private String name;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Timestamp registerDate;
	private String tel_fijo;
	private String tel_movil;
	private String street;
	private String colony;
	private String cp;
	private String municipality;
	private String state;
	private String status;
	
	
	public Timestamp getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTel_fijo() {
		return tel_fijo;
	}
	public void setTel_fijo(String tel_fijo) {
		this.tel_fijo = tel_fijo;
	}
	public String getTel_movil() {
		return tel_movil;
	}
	public void setTel_movil(String tel_movil) {
		this.tel_movil = tel_movil;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getColony() {
		return colony;
	}
	public void setColony(String colony) {
		this.colony = colony;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
