package com.sweethome.booking.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message","statusCode"})
public class ErrorModel {
	
	private int statusCode;
    private String message;
    
    public ErrorModel() {
    	
	}
    
	public ErrorModel(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	   
}
