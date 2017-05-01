package org.restmessenger.ranjan.RestMessenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	private int errorCode;
	private String documnetation;
	
	
	public ErrorMessage(){
		
	}
	
	public ErrorMessage(String errorMessage, int errorCode, String documnetation) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documnetation = documnetation;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumnetation() {
		return documnetation;
	}
	public void setDocumnetation(String documnetation) {
		this.documnetation = documnetation;
	}
	
	
}
