package entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Url {
	
	
	private String id;
	private String fullUrl;
	private LocalDateTime expiresTime;
	
	
	
	
	public Url() {
		
	}




	public Url(String id, String fullUrl, java.sql.Date expiresDate) {
		this.id = id;
		this.fullUrl = fullUrl;
		this.expiresTime = expiresDate.toLocalDate().atTime(LocalTime.MAX);
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getFullUrl() {
		return fullUrl;
	}




	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}




	public LocalDateTime getExpiresTime() {
		return expiresTime;
	}




	public void setExpiresTime(LocalDateTime expiresTime) {
		this.expiresTime = expiresTime;
	}
	
	
	

}
