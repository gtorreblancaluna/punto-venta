package mx.com.proyect.puntoventa.web.model;

public class AccountDTO {
	
	
	private String userId;
	private String email;
	private String name;
	private String firstName;
	private String secondName;
	private String password;
	
	
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
	
	@Override
	public String toString() {
		return "AccountDTO [userId=" + userId + ", email=" + email + ", name=" + name + ", firstName=" + firstName
				+ ", secondName=" + secondName + ", password=" + password + "]";
	}

}
