package mx.com.proyect.puntoventa.web.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AccountDTOclient implements Serializable {	
	
	private static final long serialVersionUID = 413296597983426252L;

	private String userId;
	private String name;
	private String firstName;
	private String secondName;
	private String tel1;
	private String tel2;
	private String street;
	private String delegation;
	private String colony;
	private String cp;
	private String state;
	private String municipality;
	private String city;
	private String email;
	private Timestamp addDate;	
	
	public Timestamp getAddDate() {
		return addDate;
	}
	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDelegation() {
		return delegation;
	}
	public void setDelegation(String delegation) {
		this.delegation = delegation;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "AccountDTOclient [userId=" + userId + ", name=" + name + ", firstName=" + firstName + ", secondName="
				+ secondName + ", tel1=" + tel1 + ", tel2=" + tel2 + ", street=" + street + ", delegation=" + delegation
				+ ", colony=" + colony + ", cp=" + cp + ", state=" + state + ", municipality=" + municipality
				+ ", city=" + city + "]";
	}
	
	
	
	
	
	


}
