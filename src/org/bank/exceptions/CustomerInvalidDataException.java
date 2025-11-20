package org.bank.exceptions;

public class CustomerInvalidDataException extends RuntimeException {
	
	private String message;

	
	public CustomerInvalidDataException() {
		super();
	}

	public CustomerInvalidDataException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CustomerInvalidDataException [message=" + message + "]";
	}
	
	

}
