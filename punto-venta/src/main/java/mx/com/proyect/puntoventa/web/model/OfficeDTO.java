package mx.com.proyect.puntoventa.web.model;

import java.io.Serializable;

/**
 * GTL 2018.05.22
 * POJO para las sucursales
 * */

public class OfficeDTO implements Serializable {	
	
	private static final long serialVersionUID = 413296597983426255L;
	private Integer officeId;
	//nombre
	private String name;
	//calle
	private String street;
	//cp
	private String cp;
	//colonia
	private String colony;
	//municipio
	private String municipality;
	// estado
	private String state;
	// telefono fijo
	private String tel1;
	// telefono secundario
	private String tel2;
	
	public Integer getOfficeId() {
		return officeId;
	}
	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getColony() {
		return colony;
	}
	public void setColony(String colony) {
		this.colony = colony;
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
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	
	
}
