package mx.com.proyect.puntoventa.web.model;

import java.io.Serializable;

public class JobDTO implements Serializable {	
	
	private static final long serialVersionUID = 413296597983426451L;
	
	private String jobId;
	private String description;
	private String status;
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
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
