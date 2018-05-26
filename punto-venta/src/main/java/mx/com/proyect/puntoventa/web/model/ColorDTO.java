package mx.com.proyect.puntoventa.web.model;

import java.io.Serializable;

public class ColorDTO implements Serializable {	
	
	private static final long serialVersionUID = 413296597983426253L;
	private float colorId;
	private String description;
	private String status;
	public float getColorId() {
		return colorId;
	}
	public void setColorId(float colorId) {
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
	@Override
	public String toString() {
		return "ColorDTO [colorId=" + colorId + ", description=" + description + ", status=" + status + "]";
	}
	
	
	
	
}
