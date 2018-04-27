package mx.com.proyect.puntoventa.web.model;

public class AccountDTOclient {

	private String UserID;
	private String name;
	private String ap_ma;
	private String ap_pa;
	private String LocalPh;
	private String Celphone;
	private String Street;
	private String Colonia;
	private String CP;
	private String state;
	private String Municipio;
	private String Views;
	
	
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAp_ma() {
		return ap_ma;
	}
	public void setAp_ma(String ap_ma) {
		this.ap_ma = ap_ma;
	}
	public String getAp_pa() {
		return ap_pa;
	}
	public void setAp_pa(String ap_pa) {
		this.ap_pa = ap_pa;
	}
	public String getLocalPh() {
		return LocalPh;
	}
	public void setLocalPh(String localPh) {
		LocalPh = localPh;
	}
	public String getCelphone() {
		return Celphone;
	}
	public void setCelphone(String celphone) {
		Celphone = celphone;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getColonia() {
		return Colonia;
	}
	public void setColonia(String colonia) {
		Colonia = colonia;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMunicipio() {
		return Municipio;
	}
	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}
	public String getViews() {
		return Views;
	}
	public void setViews(String views) {
		Views = views;
	}
	@Override
	public String toString() {
		return "AccountDTOclient [UserID=" + UserID + ", name=" + name + ", ap_ma=" + ap_ma + ", ap_pa=" + ap_pa
				+ ", LocalPh=" + LocalPh + ", Celphone=" + Celphone + ", Street=" + Street + ", Colonia=" + Colonia
				+ ", CP=" + CP + ", state=" + state + ", Municipio=" + Municipio + ", Views=" + Views + "]";
	}
	
	


}
