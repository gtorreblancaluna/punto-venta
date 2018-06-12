package mx.com.proyect.puntoventa.web.model;

public class ProviderDTO {
	private int providerId;
	private String nProvider;
	private String street;
	private String colony;
	private String municipality;
	private int CP;
	private int tel1;
	private int tel2;
	private int fg_status;
	
	
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getnProvider() {
		return nProvider;
	}
	public void setnProvider(String nProvider) {
		this.nProvider = nProvider;
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
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public int getCP() {
		return CP;
	}
	public void setCP(int cP) {
		CP = cP;
	}
	public int getTel1() {
		return tel1;
	}
	public void setTel1(int tel1) {
		this.tel1 = tel1;
	}
	public int getTel2() {
		return tel2;
	}
	public void setTel2(int tel2) {
		this.tel2 = tel2;
	}
	public int getFg_status() {
		return fg_status;
	}
	public void setFg_status(int fg_status) {
		this.fg_status = fg_status;
	}
	@Override
	public String toString() {
		return "ProviderDTO [providerId=" + providerId + ", nProvider=" + nProvider + ", street=" + street + ", colony="
				+ colony + ", municipality=" + municipality + ", CP=" + CP + ", tel1=" + tel1 + ", tel2=" + tel2
				+ ", fg_status=" + fg_status + "]";
	}
	
	
	
}
