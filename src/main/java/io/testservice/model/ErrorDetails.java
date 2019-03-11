package io.testservice.model;

public class ErrorDetails
{
	public ErrorDetails(String message) {
		super();
		this.message = message;
	}

	String message ;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
