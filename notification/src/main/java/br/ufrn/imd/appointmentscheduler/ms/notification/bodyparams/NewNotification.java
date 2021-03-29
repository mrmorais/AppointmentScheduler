package br.ufrn.imd.appointmentscheduler.ms.notification.bodyparams;

public class NewNotification {
	private String title;
	private String message;
	private String url;
	private int userId;
	
	public String getTitle() {
		return title;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getUrl() {
		return url;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
