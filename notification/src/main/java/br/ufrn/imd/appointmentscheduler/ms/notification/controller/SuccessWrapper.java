package br.ufrn.imd.appointmentscheduler.ms.notification.controller;

public class SuccessWrapper {
	private boolean success;
	
	SuccessWrapper() {
		this.success = true;
	}
	public boolean getSuccess() {
		return this.success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
