package org.krishna.demo.exceptions;

public class EmployeeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2646423526481592059L;

	public EmployeeNotFoundException() {
		super();
	}
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
