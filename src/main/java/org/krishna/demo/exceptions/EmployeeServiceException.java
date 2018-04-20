package org.krishna.demo.exceptions;

public class EmployeeServiceException extends RuntimeException{

	private static final long serialVersionUID = -944031448540315493L;
	
	
	public EmployeeServiceException() {
		super();
	}

	public EmployeeServiceException(String message) {
		super(message);
	}
	
	public EmployeeServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
