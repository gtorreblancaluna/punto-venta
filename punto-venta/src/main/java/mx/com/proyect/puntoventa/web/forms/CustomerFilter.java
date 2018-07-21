package mx.com.proyect.puntoventa.web.forms;
/**
 * GTL - 2018.07.21
 * POJO para aplicar busqueda de clientes por filtro
 * */
public class CustomerFilter {
	
	// nombre del cliente
	private String name;
	// apellido paterno cliente
	private String firstName;
	// apellido materno clienteo
	private String lastName;
	// email del cliente
	private String email;
	
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
	
	
	
}
