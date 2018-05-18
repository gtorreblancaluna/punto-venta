package mx.com.proyect.puntoventa.web.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.sql.Timestamp;

public class AccountDTO {
	
	
	private String userId;
	private String email;
	private String name;
	private String firstName;
	private String secondName;
	private String password;

	//fecha de alta
	private Timestamp addDate;
	private String admin;

	// fecha en que se registro
	private Timestamp feAlta;
	// bandera para saber si es admin
	private String fgAdmin;
	// bandera para saber si esta activo
	private String fgActivo;
	
	public String getFgActivo() {
		return fgActivo;
	}
	public void setFgActivo(String fgActivo) {
		this.fgActivo = fgActivo;
	}
	public Timestamp getFeAlta() {
		return feAlta;
	}
	public void setFeAlta(Timestamp feAlta) {
		this.feAlta = feAlta;
	}
	

	public Timestamp getAddDate() {
		return addDate;
	}
	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	
	
	public String getFgAdmin() {
		return fgAdmin;
	}
	public void setFgAdmin(String fgAdmin) {
		this.fgAdmin = fgAdmin;
	}
	@Override
	public String toString() {
		return "AccountDTO [userId=" + userId + ", email=" + email + ", name=" + name + ", firstName=" + firstName
				+ ", secondName=" + secondName + ", password=" + password + ", fgAdmin=" + fgAdmin + ", addDate="
				+ addDate + ", admin=" + admin + "]";
	}
	
	
	

}
