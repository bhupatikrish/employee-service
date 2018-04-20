package org.krishna.demo.model;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Metadata {
	private String serviceReferenceId; // service reference id
	private Integer status; //HTTP status
	private Integer code; // technical code
	private String message; // message explaining error
	private String error; // type of error
	private String exception; // exception class
	
	
	public Metadata() {
		super();
		this.serviceReferenceId = UUID.randomUUID().toString();
	}

	public void setValidationErrorOutcome() {
		this.status = HttpStatus.BAD_REQUEST.value();
		this.message = "Validation failed";
		this.code = 3000;
	}
	
	public void setErrorOutcome() {
		this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		this.message = "Internal server error";
		this.code = 5000;
	}

	public void setNotFoundOutcome() {
		this.status = HttpStatus.NOT_FOUND.value();
		this.message = "Not found";
		this.code = 4000;
	}
	
	public void setSuccessOutcome() {
		this.status = HttpStatus.OK.value();
		this.message = "Success";
		this.code = 0;
	}
		
	public String getServiceReferenceId() {
		return serviceReferenceId;
	}


	public void setServiceReferenceId(String serviceReferenceId) {
		this.serviceReferenceId = serviceReferenceId;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getException() {
		return exception;
	}


	public void setException(String exception) {
		this.exception = exception;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
