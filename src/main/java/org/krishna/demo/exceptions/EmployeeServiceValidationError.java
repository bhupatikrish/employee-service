package org.krishna.demo.exceptions;

public class EmployeeServiceValidationError extends RuntimeException{

	private static final long serialVersionUID = -4253424589515457563L;

	public EmployeeServiceValidationError() {
		super();
	}

	public EmployeeServiceValidationError(String message) {
		super(message);
	}
	
	public EmployeeServiceValidationError(String message, Throwable cause) {
		super(message, cause);
	}
	
}
