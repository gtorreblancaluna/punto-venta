package mx.com.proyect.puntoventa.web.model;

import java.io.Serializable;

public class ColorDTO implements Serializable {	
	
	private static final long serialVersionUID = 413296597983426253L;
	private Integer colorId;
	private String description;
	private String status;
	
	public Integer getColorId() {
		return colorId;
	}
	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
