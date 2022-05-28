package com.vrk.UseCaseAPI.entity;



public class request {

	private String message;
	private String phonenumber;
	private String scheduledTime;
	
	
	public request(String message, String phonenumber, String scheduledTime) {
      this.message = message;
      this.phonenumber = phonenumber;
      this.scheduledTime = scheduledTime;
  }
	
	
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(String scheduledTime) {
		
		this.scheduledTime = scheduledTime;
	}
	
	@Override
	public String toString() {
		return "request [message=" + message + ", phonenumber=" + phonenumber + ", scheduledTime=" + scheduledTime
				+ "]";
	}
}

