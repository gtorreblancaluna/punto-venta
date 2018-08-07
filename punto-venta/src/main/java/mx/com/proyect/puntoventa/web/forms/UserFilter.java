package mx.com.proyect.puntoventa.web.forms;

/**
 * @author Gerardo Torreblanca Luna
 * 2018.08.07 POJO para aplicar filtro a usuarios
 * */

public class UserFilter {
	
	// nombre del usuario
	private String nameFilter;
	// apellido paterno del usuario
	private String firstNameFilter;
	// apellido materno del usuario
	private String lastNameFilter;
	// email del usuario
	private String emailFilter;
	// puesto
	private String jobIdFilter;
	// por sucursal
	private String officeIdFilter;
	
	public String getNameFilter() {
		return nameFilter;
	}
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}
	public String getFirstNameFilter() {
		return firstNameFilter;
	}
	public void setFirstNameFilter(String firstNameFilter) {
		this.firstNameFilter = firstNameFilter;
	}
	public String getLastNameFilter() {
		return lastNameFilter;
	}
	public void setLastNameFilter(String lastNameFilter) {
		this.lastNameFilter = lastNameFilter;
	}
	public String getEmailFilter() {
		return emailFilter;
	}
	public void setEmailFilter(String emailFilter) {
		this.emailFilter = emailFilter;
	}
	public String getJobIdFilter() {
		return jobIdFilter;
	}
	public void setJobIdFilter(String jobIdFilter) {
		this.jobIdFilter = jobIdFilter;
	}
	public String getOfficeIdFilter() {
		return officeIdFilter;
	}
	public void setOfficeIdFilter(String officeIdFilter) {
		this.officeIdFilter = officeIdFilter;
	}
	
	

}
